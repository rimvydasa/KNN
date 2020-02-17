package lt.rimvydasa;


public class ClassifyByFormula {


    public double Formula1(KNN_Features knn_features,KNN_Features knn_new) {
       double distance;
       distance = Math.sqrt((Math.pow(knn_features.getX()-knn_new.getX(),2))+
               (Math.pow(knn_features.getY() - knn_new.getY(),2)));
        return distance;
    }
}
