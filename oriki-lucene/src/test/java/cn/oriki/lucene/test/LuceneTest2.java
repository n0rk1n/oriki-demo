package cn.oriki.lucene.test;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneTest2 {

    @Test
    public void test() throws IOException, ParseException {
        /**
         * 1、获取索引目录
         * 2、创建索引读取类对象
         * 3、创建索引查询类对象
         * 4、创建索引查询解析器
         * 5、创建查询对象
         * 6、返回结果
         * 7、解析结果
         */

        // 1、获取索引目录
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、获取索引读取对象
        DirectoryReader directoryReader = DirectoryReader.open(directory);

        // 3、创建索引查看器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        // 4、创建索引查看解析器
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());
        // 5、创建查询对象
        Query query = queryParser.parse("谷歌地图之父拉斯");

        // 6、返回结果
        TopDocs docs = indexSearcher.search(query, 10);

        // 7、解析结果
        System.out.println("本次共找到 " + docs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);

            String id = document.get("id");
            System.out.println("文档的编号：" + id);
            String title = document.get("title");
            System.out.println("文档的title：" + title);

            System.out.println("得分：" + scoreDoc.score);
        }
    }

    @Test
    public void test2() throws IOException, ParseException {
        /**
         * 1、获取索引目录
         * 2、创建索引读取类对象
         * 3、创建索引查询类对象
         * 4、创建索引查询解析器
         * 5、创建查询对象
         * 6、返回结果
         * 7、解析结果
         */

        // 1、获取索引目录
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、获取索引读取对象
        DirectoryReader directoryReader = DirectoryReader.open(directory);

        // 3、创建索引查看器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        TermQuery termQuery = new TermQuery(new Term("title", "谷歌"));

        // 6、返回结果
        TopDocs docs = indexSearcher.search(termQuery, 10);

        // 7、解析结果
        System.out.println("本次共找到 " + docs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);

            String id = document.get("id");
            System.out.println("文档的编号：" + id);
            String title = document.get("title");
            System.out.println("文档的title：" + title);

            System.out.println("得分：" + scoreDoc.score);
        }
    }

    @Test
    public void test3() throws IOException, ParseException {
        /**
         * 1、获取索引目录
         * 2、创建索引读取类对象
         * 3、创建索引查询类对象
         * 4、创建索引查询解析器
         * 5、创建查询对象
         * 6、返回结果
         * 7、解析结果
         */

        // 1、获取索引目录
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、获取索引读取对象
        DirectoryReader directoryReader = DirectoryReader.open(directory);

        // 3、创建索引查看器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        WildcardQuery wildcardQuery = new WildcardQuery(new Term("title", "*歌*"));

        // 6、返回结果
        TopDocs docs = indexSearcher.search(wildcardQuery, 10);

        // 7、解析结果
        System.out.println("本次共找到 " + docs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);

            String id = document.get("id");
            System.out.println("文档的编号：" + id);
            String title = document.get("title");
            System.out.println("文档的title：" + title);

            System.out.println("得分：" + scoreDoc.score);
        }
    }

    @Test
    public void test4() throws IOException, ParseException {
        /**
         * 1、获取索引目录
         * 2、创建索引读取类对象
         * 3、创建索引查询类对象
         * 4、创建索引查询解析器
         * 5、创建查询对象
         * 6、返回结果
         * 7、解析结果
         */

        // 1、获取索引目录
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、获取索引读取对象
        DirectoryReader directoryReader = DirectoryReader.open(directory);

        // 3、创建索引查看器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term("title", "facedook"), 2);
        // 6、返回结果
        TopDocs docs = indexSearcher.search(fuzzyQuery, 10);

        // 7、解析结果
        System.out.println("本次共找到 " + docs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);

            String id = document.get("id");
            System.out.println("文档的编号：" + id);
            String title = document.get("title");
            System.out.println("文档的title：" + title);

            System.out.println("得分：" + scoreDoc.score);
        }
    }

    @Test
    public void test5() throws IOException, ParseException {
        /**
         * 1、获取索引目录
         * 2、创建索引读取类对象
         * 3、创建索引查询类对象
         * 4、创建索引查询解析器
         * 5、创建查询对象
         * 6、返回结果
         * 7、解析结果
         */

        // 1、获取索引目录
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、获取索引读取对象
        DirectoryReader directoryReader = DirectoryReader.open(directory);

        // 3、创建索引查看器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        //    数值范围查询对象，参数：字段名称，最小值、最大值、是否包含最小值、是否包含最大值
        Query numbericRangequery = NumericRangeQuery.newIntRange("id", 2, 2, true, true);
        // 6、返回结果
        TopDocs docs = indexSearcher.search(numbericRangequery, 10);

        // 7、解析结果
        System.out.println("本次共找到 " + docs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);

            String id = document.get("id");
            System.out.println("文档的编号：" + id);
            String title = document.get("title");
            System.out.println("文档的title：" + title);

            System.out.println("得分：" + scoreDoc.score);
        }
    }


    @Test
    public void test6() throws IOException, ParseException {
        /**
         * 1、获取索引目录
         * 2、创建索引读取类对象
         * 3、创建索引查询类对象
         * 4、创建索引查询解析器
         * 5、创建查询对象
         * 6、返回结果
         * 7、解析结果
         */

        // 1、获取索引目录
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、获取索引读取对象
        DirectoryReader directoryReader = DirectoryReader.open(directory);

        // 3、创建索引查看器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        Query query1 = NumericRangeQuery.newLongRange("id", 1L, 3L, true, true);
        Query query2 = NumericRangeQuery.newLongRange("id", 2L, 4L, true, true);
        // 创建布尔查询的对象
        BooleanQuery booleanQuery = new BooleanQuery();
        // 组合其它查询
        booleanQuery.add(query1, BooleanClause.Occur.MUST_NOT);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        // 6、返回结果
        TopDocs docs = indexSearcher.search(booleanQuery, 10);

        // 7、解析结果
        System.out.println("本次共找到 " + docs.totalHits + " 条数据");

        for (ScoreDoc scoreDoc : docs.scoreDocs) {
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
