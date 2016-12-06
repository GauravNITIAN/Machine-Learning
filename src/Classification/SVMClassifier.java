/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classification;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import libsvm.LibSVM;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.classification.tree.RandomForest;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

/**
 *
 * @author GAURAV KUMAR
 */
public class SVMClassifier {
    
    
    /*
    This is a SVM Classifer
    Some Links : -
    https://sourceforge.net/p/java-ml/java-ml-code/ci/30237db0cb0457fe2629004ac91ac4cd2768d8ea/tree/src/tutorials/classification/TutorialLibSVM.java#l33
    
    */
     static boolean wrtieInfile = false;
     
     
    public static double BuildSVM(String Train,String test,int features) throws IOException
    {
        
        /* Load a data set  with comma seperated*/
        Dataset data = FileHandler.loadDataset(new File(Train), features, ",");
         
       // Contruct a LibSVM classifier with default settings.
        Classifier svm = new LibSVM();
        
        // Train the Model
         svm.buildClassifier(data);
         
         //Load the dataset for Testing here
         Dataset dataForClassification = FileHandler.loadDataset(new File(test), features, ",");
        
          /*
         A PerformanceMeasure is a wrapper around the values for the true positives, 
        true negatives, false positives and false negatives. This class also provides
        a number of convenience method to calculate number of aggregate measures like 
        accuracy, f-score, recall, precision, sensitivity, specificity,
        */
        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(svm, dataForClassification);
        
       // Accuracy
       System.out.println("-----------------------------------------------------");
       for(Object o:pm.keySet())
       {
            System.out.println(o+": Accracuy "+pm.get(o).getAccuracy());
            System.out.println(": Precision "+pm.get(o).getPrecision());
            System.out.println(": Fmeasure "+pm.get(o).getFMeasure());
            System.out.println(": Recall "+pm.get(o).getRecall());
       }
         
         if(wrtieInfile)
      WriteINFile(dataForClassification,test+"Out.txt",svm);
        
        
     return 0;   
    }
    
    public static void WriteINFile(Dataset Testfile,String file,Classifier rn) throws IOException
    {
        BufferedWriter out =new BufferedWriter(new FileWriter(file, wrtieInfile));
        for(int i=0;i<Testfile.size();i++)
        {
            
        // Instance is the input vector that may be traindata or testdata depending what you 
         // have supplied for testing dataset
            // Here it will get the instance and classify the output label that will be stored in a
       Instance instance = Testfile.instance(i);
       String a =(String) rn.classify(instance);
       
       // Writing it int the file
       out.write(a);
       out.newLine();
    }
        
        //close the buffer otherwise it will not work
        out.close();
    }
    
   public static void main(String args[]) throws IOException
   {
    SVMClassifier.BuildSVM("KeyStrokeData.txt", "iris.txt", 8);
   }
}
