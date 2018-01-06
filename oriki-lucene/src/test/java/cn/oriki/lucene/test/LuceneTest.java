package cn.oriki.lucene.test;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.Arrays;

public class LuceneTest {
    @Test
    public void test() throws Exception {
        /**
         * 创建索引步骤：
         *
         * 1、创建索引目录类，指定索引存放位置
         * 2、创建索引写出工具配置类对象
         * 3、创建索引写出工具类
         * 4、将文档对象添加到索引写出工具类中
         * 5、提交
         */

        // 0、准备文档
        Document document = new Document();
        // 参数：字段的名称、字段的值、是否存储，这里选Store.YES代表存储到文档列表。Store.NO代表不存储
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new TextField("title", "谷歌地图之父跳槽facebook", Field.Store.YES));

        // 1、创建索引目录类，指定索引存放位置
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、创建索引写出工具配置类对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
        // 3、创建索引写出工具类
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // 4、将文档对象添加到索引写出工具类中
        indexWriter.addDocuments(Arrays.asList(document));
        // 5、提交
        indexWriter.commit();

        indexWriter.close();
    }

    @Test
    public void test2() throws Exception {
        /**
         * 创建索引步骤：
         *
         * 1、创建索引目录类，指定索引存放位置
         * 2、创建索引写出工具配置类对象
         * 3、创建索引写出工具类
         * 4、将文档对象添加到索引写出工具类中
         * 5、提交
         */

        // 0、准备文档
        Document document = new Document();
        // 参数：字段的名称、字段的值、是否存储，这里选Store.YES代表存储到文档列表。Store.NO代表不存储
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new TextField("title", "谷歌地图之父跳槽facebook", Field.Store.YES));

        // 1、创建索引目录类，指定索引存放位置
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、创建索引写出工具配置类对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        // 3、创建索引写出工具类
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // 4、将文档对象添加到索引写出工具类中
        indexWriter.addDocuments(Arrays.asList(document));
        // 5、提交
        indexWriter.commit();

        indexWriter.close();
    }


    @Test
    public void test3() throws Exception {
        /**
         * 创建索引步骤：
         *
         * 1、创建索引目录类，指定索引存放位置
         * 2、创建索引写出工具配置类对象
         * 3、创建索引写出工具类
         * 4、将文档对象添加到索引写出工具类中
         * 5、提交
         */

        // 0、准备文档
        //  创建文档对象
        Document document1 = new Document();
        document1.add(new StringField("id", "1", Field.Store.YES));
        document1.add(new TextField("title", "谷歌地图之父跳槽facebook", Field.Store.YES));
        //  创建文档对象
        Document document2 = new Document();
        document2.add(new StringField("id", "2", Field.Store.YES));
        document2.add(new TextField("title", "谷歌地图之父加盟FaceBook", Field.Store.YES));
        //  创建文档对象
        Document document3 = new Document();
        document3.add(new StringField("id", "3", Field.Store.YES));
        document3.add(new TextField("title", "谷歌地图创始人拉斯离开谷歌加盟Facebook", Field.Store.YES));
        //  创建文档对象
        Document document4 = new Document();
        document4.add(new StringField("id", "4", Field.Store.YES));
        document4.add(new TextField("title", "谷歌地图之父跳槽Facebook与Wave项目取消有关", Field.Store.YES));
        //  创建文档对象
        Document document5 = new Document();
        document5.add(new StringField("id", "5", Field.Store.YES));
        document5.add(new TextField("title", "谷歌地图之父拉斯加盟社交网站Facebook", Field.Store.YES));

        // 1、创建索引目录类，指定索引存放位置
        FSDirectory directory = FSDirectory.open(new File("indexDir"));
        // 2、创建索引写出工具配置类对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        // 设置清空索引库
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 3、创建索引写出工具类
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // 4、将文档对象添加到索引写出工具类中
        indexWriter.addDocuments(Arrays.asList(document1, document2, document3, document4, document5));
        // 5、提交
        indexWriter.commit();

        indexWriter.close();
    }
}
