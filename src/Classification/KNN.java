/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

/**
 *
 * @author GAURAV KUMAR
 */
public class KNN {
 
    /*
    
    This Function Will Built KNN Classifier using Java-ML
    Arguments : - Train is the Train data file
                  Test is the Test Data file
                  k is no of nearest neighbour
                  features is the no of featues in the dataset
    */
    public static double BuildKNN(String Train,String test,int featues,int k) throws IOException
    {
        double [] results = new double[4]; // IS used to Store Accracy precion f1 scrore etc
        //This Will load the Data Set from the Train Dataset 
        // featues is no of feature vector excluding class level 
        // Comma seperated features it represents
        Dataset data = FileHandler.loadDataset(new File(Train), featues, ",");
        
        // Here k = will indicate that no of nearest Neighbour
        Classifier knn = new KNearestNeighbors(k);
        
        //Here We fit our Training data in the Model for Classification
        knn.buildClassifier(data);
        
        // Testing data to find out the Accracy or labels
        Dataset dataForClassification = FileHandler.loadDataset(new File(test), featues, ",");
        
       /*
         A PerformanceMeasure is a wrapper around the values for the true positives, 
        true negatives, false positives and false negatives. This class also provides
        a number of convenience method to calculate number of aggregate measures like 
        accuracy, f-score, recall, precision, sensitivity, specificity,
        */
        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(knn, dataForClassification);
        
       // Accuracy
       System.out.println("-----------------------------------------------------");
       for(Object o:pm.keySet())
       {
            System.out.println(o+": Accracuy "+pm.get(o).getAccuracy());
            System.out.println(": Precision "+pm.get(o).getPrecision());
            System.out.println(": Fmeasure "+pm.get(o).getFMeasure());
            System.out.println(": Recall "+pm.get(o).getRecall());
       }
       
       int i=0;
       //results[i++] = pm.get(o).getAccuracy();
       
       
        return 0;
    }
    
   public static void main(String args[]) throws IOException
   {
       KNN.BuildKNN("KeyStrokeData.txt", "iris.txt", 8, 10);
   }
}
