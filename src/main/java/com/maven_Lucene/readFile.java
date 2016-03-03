package com.maven_Lucene;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class readFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		List<String> lines=new ArrayList<String>();  
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("/Users/wangzehui/Desktop/Semantic Web Tech/DBData/test1.txt")));  
		String line = null;  
		while ((line = br.readLine()) != null) {  
		      lines.add(line);  
		}  
		String content="";
		for(int i=0;i<lines.size();i++)
		{
			content=content+lines.get(i);
		}
		System.out.println(content);
		br.close(); 
	}

}
