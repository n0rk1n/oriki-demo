package cn.oriki.lucene.test;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneTest5 {
    @Test
    public void test() throws IOException, ParseException, InvalidTokenOffsetsException {
        /**
         * 1、获取目录对象
         * 2、创建读取工具
         * 3、创建索引搜索工具
         * 4、创建条件搜索
         *
         * 5、创建格式化器
         * 6、准备高亮工具
         * 7、搜索
         *
         * 8、使用高亮工具处理
         */

        FSDirectory directory = FSDirectory.open(new File("indexDir"));

        DirectoryReader directoryReader = DirectoryReader.open(directory);

        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());
        Query query = queryParser.parse("谷歌地图");

        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer queryScorer = new QueryScorer(query);

        Highlighter highlighter = new Highlighter(formatter, queryScorer);

        TopDocs topDocs = indexSearcher.search(query, 10);

        System.out.println("本次共搜索到 " + topDocs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docID = scoreDoc.doc;
            Document doc = directoryReader.document(docID);
            System.out.println("id: " + doc.get("id"));
            String title = doc.get("title");
            String hTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", title);
            System.out.println("title: " + hTitle);
            System.out.println("得分：" + scoreDoc.score);
        }
    }


}
