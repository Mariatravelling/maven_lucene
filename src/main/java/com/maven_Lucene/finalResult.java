package com.maven_Lucene;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;

public class finalResult {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//searchEngineer.searching("content");
		//recommendation adveriser=new recommendation();
		try{
			File index=new File("/Users/wangzehui/Desktop/Semantic Web Tech/DBIndex");
			Directory directory=FSDirectory.open(index.toPath());
			IndexReader reader= DirectoryReader.open(directory);
			IndexSearcher searcher=new IndexSearcher(reader);
			QueryBuilder builder = new QueryBuilder(new StandardAnalyzer());
			   Query b = builder.createPhraseQuery("Content","Quentin Tarantino");
			   TopDocs tds=searcher.search(b, 100);
				ScoreDoc[] sds=tds.scoreDocs;
				System.out.println(sds.length);
				//Map<String,Double> similarity = new HashMap();
				for(ScoreDoc sd:sds)
				{
					Document d=searcher.doc(sd.doc);
				    String type=d.get("filmType");
				    //System.out.println(type);
		 
					//System.out.println(d.get("description"));
					//double sim=DocumentSimilarity.calculateSim("Ronin is a 1998 American spy thriller action film directed by John Frankenheimer and starring Robert De Niro, Jean Reno, Natascha McElhone, Stellan Skarsgård, Sean Bean, and Jonathan Pryce. Written by J.D. Zeik and David Mamet, the film is about two of several former special forces and intelligence agents who team up to steal a mysterious, heavily guarded case while navigating a maze of shifting loyalties and alliances. The film is noted for its car chases through Nice and Paris.", d.get("description"));
					//similarity.put(d.get("Name"), sim);
					/*if(sim!=0)
					{
						System.out.println(d.get("Name"));
						System.out.println(sim);
					}*/
				}
				//sortValue(similarity);
		}catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
