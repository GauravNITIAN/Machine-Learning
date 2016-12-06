/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PreProcessing;

import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author GAURAV KUMAR
 */
public class Kaggle_Competion {
    
    
    public static String preprocessing(String file) throws FileNotFoundException, IOException
    {
         BufferedReader buffer = new BufferedReader(new FileReader(file));
      BufferedWriter out = new  BufferedWriter(new FileWriter(file+"T.txt", true));
      String line ;
      
      //Read the FIle and Write it another file
      while((line=buffer.readLine())!=null)
      {
          String str[] = line.split(",");
          
          String write = str[0];
          
          for(int i=1;i<str.length;i++)
          {
              int value = 0;
              if(str[i].length() == 1 && str[i].charAt(0) >=65 && str[i].charAt(0)<=90)
              {
                  value = str[i].charAt(0);
                  write =write+","+value;
              }
              else if(str[i].length() == 2 && str[i].charAt(0) >=65 && str[i].charAt(0)<=90)
              {
                  value = str[i].charAt(0)+str[i].charAt(1);
                  write =write+","+value;
              }
              else
              {
                  write =write+","+ str[i];
              }
          }
          out.write(write);
          out.newLine();
      }
      out.close();
      buffer.close();
   return file+"T.txt";  
    }
    
    
    
  public static void  main(String args[]) throws IOException
  {
      Kaggle_Competion.preprocessing("kaggle.txt");
  }
}
