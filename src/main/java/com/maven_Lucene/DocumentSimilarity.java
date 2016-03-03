package com.maven_Lucene;

import java.net.*;
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;



class DocumentSimilarity
{

 @SuppressWarnings("deprecation")
  public static double calculateSim (String text1,String text2) throws IOException
  {
    
    String urlToCall = "http://www.scurtu.it/apis/documentSimilarity";
    String content = "doc1=" + URLEncoder.encode(text1) +
		      "&doc2=" + URLEncoder.encode(text2);
    
    
    
    URL url = new URL(urlToCall); 
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();                       
    connection.setDoOutput(true); 
    connection.setDoInput (true);
    connection.setUseCaches (false);        
    connection.setInstanceFollowRedirects(false); 
    connection.setRequestMethod("POST"); 
    connection.setRequestProperty("Content-Type", "text/plain"); 
    connection.setRequestProperty("charset", "utf-8");
    connection.connect();
    
    
    DataOutputStream output = new DataOutputStream(connection.getOutputStream());
    output.writeBytes(content);
    output.flush();
    output.close();
    
    StringBuilder strBuffer = new StringBuilder();
    String str = null;
    DataInputStream input = new DataInputStream (connection.getInputStream());
            while (null != ((str = input.readLine()))) {
                strBuffer.append(str);
      }
      
    Object obj=JSONValue.parse(strBuffer.toString());
    JSONObject jsonObj = (JSONObject)obj; 
    String[] result=jsonObj.toString().split(",");
    String[] score=result[0].split(":");
    double sim = 0;
    try {
    sim = Double.parseDouble(score[1].trim());
    } catch (NumberFormatException nfe) {
        System.out.println("NumberFormatException: " + nfe.getMessage());
     }
    return sim;
    
 }
}
