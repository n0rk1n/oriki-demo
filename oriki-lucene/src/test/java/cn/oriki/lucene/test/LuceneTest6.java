package cn.oriki.lucene.test;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneTest6 {

    /**
     * 普通查询
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void test() throws IOException, ParseException {
        FSDirectory directory = FSDirectory.open(new File("indexDir"));

        DirectoryReader directoryReader = DirectoryReader.open(directory);

        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());
        Query query = queryParser.parse("谷歌地图");

        TopDocs topDocs = indexSearcher.search(query, 10);

        System.out.println("本次共找到 " + topDocs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);

            String id = document.get("id");
            System.out.println("文档的编号：" + id);
            String title = document.get("title");
            System.out.println("文档的title：" + title);

            System.out.println("得分：" + scoreDoc.score);
        }
    }

    /**
     * 排序查询
     *
     * @throws ParseException
     * @throws IOException
     */
    @Test
    public void test2() throws ParseException, IOException {
        FSDirectory directory = FSDirectory.open(new File("indexDir"));

        DirectoryReader directoryReader = DirectoryReader.open(directory);

        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());
        Query query = queryParser.parse("谷歌地图");

        Sort sort = new Sort(new SortField("id", SortField.Type.INT, true));

        TopDocs topDocs = indexSearcher.search(query, 10, sort);

        System.out.println("本次共找到 " + topDocs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);

            String id = document.get("id");
            System.out.println("文档的编号：" + id);
            String title = document.get("title");
            System.out.println("文档的title：" + title);

            System.out.println("得分：" + scoreDoc.score);
        }
    }
}
