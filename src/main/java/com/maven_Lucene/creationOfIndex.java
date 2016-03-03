package com.maven_Lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class creationOfIndex {
	public static void main(String[] args) throws IOException 
	{
		
		FieldType ft=new FieldType();
		ft.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
	   
		File index=new File("/Users/wangzehui/Desktop/Semantic Web Tech/DBIndex");
		Directory directory=FSDirectory.open(index.toPath());
		IndexWriterConfig iwc=new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer=null;
		try {
			writer=new IndexWriter(directory,iwc);
		InputStreamReader isr=null;
		File f=new File("/Users/wangzehui/Desktop/Semantic Web Tech/DBData");
		for(File file:f.listFiles())
		{
			if(file.getName().equals(".DS_Store"))
			{
				System.out.println("hey");
			}
			else{
				String fileName=file.getPath();
				System.out.println(file.getName());
				String content=readFile(fileName);
				System.out.println(content);

				String[] split=content.split(";;");
				System.out.println(split.length);
				String directors=split[0];
				String actors=split[1];
				String description=split[2];
				String filmType=split[3];
				//System.out.println("filmType:"+filmType+" description:"+description);
				Document doc=new Document();
				doc.add(new Field("Content",new FileReader(file),ft));
				doc.add(new TextField("filmType",filmType,Field.Store.YES));
				doc.add(new TextField("description",description,Field.Store.YES));
				doc.add(new TextField("actors",actors,Field.Store.YES));
				doc.add(new TextField("directors",directors,Field.Store.YES));
				doc.add(new StringField("Name",file.getName().substring(0,file.getName().length()-4),Field.Store.YES));
				writer.addDocument(doc);
				System.out.println(doc.get("Name"));
			}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(writer!=null)
			{
				writer.close();
			}
		}
	}
	private static String readFile( String fileName ) throws IOException {
		List<String> lines=new ArrayList<String>();  
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));  
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
		return content;
	}
}
