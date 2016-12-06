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
import java.util.Random;
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

/*
Some Links 
http://www.listendata.com/2014/11/random-forest-with-r.htm
https://sourceforge.net/p/java-ml/java-ml-code/ci/fdb1474c38f714a6ea113d8179368ad1ebe1ab8e/tree/net/sf/javaml/classification/tree/RandomForest.java#l38


*/
public class RandomForestClassifier {
    
    static int random = 100;
    static Random rg=new Random(random);
    
    // If You Want this to Write it in file set this to True
    static boolean wrtieInfile = false;
    
     public static double BuildRandomForest(String Train,String test,int featues,int treeCount) throws IOException
    {
        RandomForest rn = new RandomForest(treeCount, true, featues, rg);
         //This Will load the Data Set from the Train Dataset 
        // featues is no of feature vector excluding class level 
        // Comma seperated features it represents
        Dataset data = FileHandler.loadDataset(new File(Train), featues, ",");
        
        // Here it will built the Classifer as Randrom Forest
        rn.buildClassifier(data);
        
        // Testing data to find out the Accracy or labels
        Dataset dataForClassification = FileHandler.loadDataset(new File(test), featues, ",");
        
         /*
         A PerformanceMeasure is a wrapper around the values for the true positives, 
        true negatives, false positives and false negatives. This class also provides
        a number of convenience method to calculate number of aggregate measures like 
        accuracy, f-score, recall, precision, sensitivity, specificity,
        */
        Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(rn, dataForClassification);
        
       // Accuracy
       System.out.println("-----------------------------------------------------");
       for(Object o:pm.keySet())
       {
            System.out.println(o+": Accracuy "+pm.get(o).getAccuracy());
            System.out.println(": Precision "+pm.get(o).getPrecision());
            System.out.println(": Fmeasure "+pm.get(o).getFMeasure());
            System.out.println(": Recall "+pm.get(o).getRecall());
       }
       
       //Write Onliy if We have permmission to write it into the file
       //means if we set the witeInfile to true the the output labels will be put into the file
       if(wrtieInfile)
      WriteINFile(dataForClassification,test+"Out.txt",rn);
       
        
     return 0;   
    }
    // This is used to Write the Predicted Class Labels In the File 
    public static void WriteINFile(Dataset Testfile,String file,RandomForest rn) throws IOException
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
        RandomForestClassifier.wrtieInfile  = false;  // Enable writing in the File output labels
        /* Arguments : 1 Train File
                       2. Test File
                       3. No of Feature Vector
                       4. No of Trees Count
        */
                                 //Arguments : - Test DataSet , Train Dataset , no of vector,treecount
        RandomForestClassifier.BuildRandomForest("KeyStrokeData.txt", "iris.txt", 8, 10);
    }
}
