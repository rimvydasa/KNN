package lt.rimvydasa;


import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.List;

public class KNN_main {

    public static void main(String[] args) throws Throwable {

        Scanner scanner = new Scanner(System.in);

        List<KNN_Features> list = new ArrayList<KNN_Features>();

        int knn1 = 0,knn2 = 0,knn3 = 0;
        int x = 3;
        int y = 2;
        System.out.println("Choose \n 1 - From database \n 2 - From file");
        y = scanner.nextInt();
        switch (y) {
            case 1:
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clustering?autoReconnect=true&useSSL=false","root","mysql");
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM mokymo_imtis");

                while(rs.next()) {
                    KNN_Features knn = new KNN_Features(rs.getInt(2), rs.getInt(3),rs.getString(4));
                    list.add(knn);
                }
                con.close();
                System.out.println(list);

                break;
            case 2:
                System.out.println("How many neighbors want to use? P.S could only use 3");
                x = scanner.nextInt();
                BufferedReader bufferedReade = new BufferedReader(new FileReader("failas.txt"));
                String line = "";
                list = new ArrayList<KNN_Features>();

                while (( line = bufferedReade.readLine() ) != null) {
                    String k[] = line.split(",");
                    KNN_Features knn_features = new KNN_Features(Integer.parseInt(k[0]), Integer.parseInt(k[1]), k[2]);
                    list.add(knn_features);
                }
                System.out.println("\n" + list);
            switch (x) {

                case 1:
                    System.out.println("First neighbor ");
                    knn1 = scanner.nextInt();
                    break;
                case 2:
                    System.out.println("First neighbor ");
                    knn1 = scanner.nextInt();
                    System.out.println("Second neighbor ");
                    knn2 = scanner.nextInt();
                    break;
                case 3:
                    System.out.println("First neighbor ");
                    knn1 = scanner.nextInt();
                    System.out.println("Second neighbor ");
                    knn2 = scanner.nextInt();
                    System.out.println("Third neighbor ");
                    knn3 = scanner.nextInt();
            }
            default:break;

        }
            List<Integer> knnInput = new ArrayList<>();
            knnInput.add(knn1);
            knnInput.add(knn2);
            knnInput.add(knn3);

        System.out.println("Input - 1 number/value ");
        int input = scanner.nextInt();
        System.out.println("Second  - 2 number/value ");
        int input1 = scanner.nextInt();

        ClassifyByFormula classify = new ClassifyByFormula();
        KNN_Features knn_new = new KNN_Features(input,input1);

        double min = 99;
        String className = "";
        for (int i=0 ; i < 3; i++){
            if (classify.Formula1(list.get(knnInput.get(i)),knn_new) < min){
                min = classify.Formula1(list.get(knnInput.get(i)),knn_new);
                className = list.get(knnInput.get(i)).getClassname();
            }
        }
        knn_new.setClassname(className);
        System.out.println(knn_new + "Answer");

    }
}

