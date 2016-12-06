/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PreProcessing;

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
public class TrimFromEnd {
   
    /*
    This Function Will Return the Trimmed File after trimimg this will write it in to another
    file.
    */
  public static String Trim(String file) throws FileNotFoundException, IOException
  {
      BufferedReader buffer = new BufferedReader(new FileReader(file));
      BufferedWriter out = new  BufferedWriter(new FileWriter(file+"T.txt", true));
      String line ;
      
      //Read the FIle and Write it another file
      while((line=buffer.readLine())!=null)
      {
          line = line.trim();
          
          out.write(line);
          out.newLine();
      }
      out.close();
      buffer.close();
   return file+"T.txt";           
  }
  
  
 public static void main(String args[]) throws IOException
 {
     TrimFromEnd.Trim("iris.txt");
 }
}
