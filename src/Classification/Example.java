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
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.featureselection.ranking.RecursiveFeatureEliminationSVM;
import net.sf.javaml.tools.data.FileHandler;

/**
 *
 * @author GAURAV KUMAR
 */
public class Example {
    
    
    
    
    public static void main(String args[]) throws IOException
    {
      Dataset data = FileHandler.loadDataset(new File("KeyStrokeData.txt"), 8, ",");
   Classifier knn = new KNearestNeighbors(5);
   knn.buildClassifier(data);
   Dataset dataForClassification = FileHandler.loadDataset(new File("iris.txt"), 8, ",");
 
   int count =0;
  Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(knn, dataForClassification);
   for(Object o:pm.keySet())
   {
    System.out.println(o+": "+pm.get(o)+" :"+pm.get(o).getPrecision() );
    count++;
   }
   System.out.println(count);
   
   
   RecursiveFeatureEliminationSVM svmrfe = new RecursiveFeatureEliminationSVM(0.2);
/* Apply the algorithm to the data set */
svmrfe.build(data);
/* Print out the rank of each attribute */
for (int i = 0; i < svmrfe.noAttributes(); i++)
    System.out.println(svmrfe.rank(i));
    }

    
    
}