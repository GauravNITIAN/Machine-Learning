/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FeatureSelection;

import java.io.File;
import java.io.IOException;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.featureselection.ranking.RecursiveFeatureEliminationSVM;
import net.sf.javaml.featureselection.scoring.GainRatio;
import net.sf.javaml.tools.data.FileHandler;

/**
 *
 * @author GAURAV KUMAR
 */

/*
This Class is Used to Rank the Feature How important the feature
Here the Feature higher will be its Rank
The Will be used to give higher weigtage in comaparsion to others
The file must be comma separated
*/
public class FeatureRanking {
    
    
    /*
    
    This Method Return the Rank of each Featues Lower the rank higher
    the Feature will be importance will be
    
    Argument : - 1) file contains the feature Vector
                 2) features is the no of features in each sample
    */
    public static int[] RankFeature(String file,int features) throws IOException
    {
        int rank[] =new int[features]; // For Storing the rank of each features
        Dataset data = FileHandler.loadDataset(new File(file), features, ",");
      /* Create a feature ranking algorithm */
       RecursiveFeatureEliminationSVM svmrfe = new RecursiveFeatureEliminationSVM(0.2);
      /* Apply the algorithm to the data set */
       svmrfe.build(data);
    /* Print out the rank of each attribute */
       for (int i = 0; i < svmrfe.noAttributes(); i++)
        rank[i] = svmrfe.rank(i);
        return rank;
    }
    
    /*
    
    This Method Return the Score of each Featues Higher the Score Better 
    the Feature will be.
    
    Argument : - 1) file contains the feature Vector
                 2) features is the no of features in each sample
    */
    public static double[] ScoreFeature(String file,int features) throws IOException
    {
        double score[] = new double[features];
        /* Load the iris data set */
       Dataset data = FileHandler.loadDataset(new File(file), features, ",");
       /* Create a feature scoring algorithm */
       GainRatio ga = new GainRatio();
       /* Apply the algorithm to the data set */
       ga.build(data);
     /* Print out the score of each attribute */
      for (int i = 0; i < ga.noAttributes(); i++)
        score[i] = ga.score(i);

return score;
    }
    
    public static void main(String args[]) throws IOException
    {
      double ar[] = FeatureRanking. ScoreFeature("iris.txt", 8);
      
      for(int i=0;i<ar.length;i++)
          System.out.println(ar[i]);
    }
    
}
