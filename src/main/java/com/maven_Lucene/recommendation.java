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

public class recommendation {
	
	public void adversierFilm(String filmType,String description) 
	{

		try{
			File index=new File("/Users/wangzehui/Desktop/Semantic Web Tech/DBIndex");
			Directory directory=FSDirectory.open(index.toPath());
			IndexReader reader= DirectoryReader.open(directory);
			IndexSearcher searcher=new IndexSearcher(reader);
			QueryBuilder builder = new QueryBuilder(new StandardAnalyzer());
			   Query b = builder.createPhraseQuery("filmType",filmType);
			   TopDocs tds=searcher.search(b, 100);
				ScoreDoc[] sds=tds.scoreDocs;
				Map<String,Double> similarity = new HashMap();
				for(ScoreDoc sd:sds)
				{
					Document d=searcher.doc(sd.doc);
					//System.out.println(d.get("description"));
					double sim=DocumentSimilarity.calculateSim(description, d.get("description"));
					similarity.put(d.get("Name"), sim);
					if(sim!=0)
					{
						System.out.println(d.get("Name"));
						System.out.println(sim);
					}
				}
				//sortValue(similarity);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	
	/*public static void sortValue(Map similarity)
	{
		Set<Entry<String, Integer>> set = similarity.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        compareDouble t = new compareDouble();
        Collections.sort(similarity, new Comparator<Map<String, Object>>() {

			public int compare(Map<String, Object> lhs, Map<String, Object> rhs) {

		Double d5 = ((Double) rhs.get(OpenPrice));
		Double d6 = (Double) lhs.get(OpenPrice);
	       if (d5 != null && d6 != null) {
                     return d5.compareTo(d6);
		} else {
			return flag;
		    }
						// return d1.compareTo(d2);
		}
        }
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(entry.getKey()+" ==== "+entry.getValue());
         }
	}*/
	}
	}
