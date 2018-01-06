package cn.oriki.lucene.test;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneTest7 {

    @Test
    public void test() throws IOException, ParseException {
        // 每页条数
        int pageSize = 2;
        // 当前页码
        int pageNum = 2;
        // 当前页的起始条数
        int start = (pageNum - 1) * pageSize;
        // 当前页的结束条数（不能包含）
        int end = start + pageSize;


        Directory directory = FSDirectory.open(new File("indexDir"));

        IndexReader reader = DirectoryReader.open(directory);

        IndexSearcher searcher = new IndexSearcher(reader);
        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("谷歌地图");

        Sort sort = new Sort(new SortField("id", SortField.Type.INT, false));

        TopDocs topDocs = searcher.search(query, end, sort);
        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = start; i < end; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];

            int docID = scoreDoc.doc;
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            System.out.println("title: " + doc.get("title"));
        }
    }

}

