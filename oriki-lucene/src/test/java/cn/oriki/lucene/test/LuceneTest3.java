package cn.oriki.lucene.test;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneTest3 {

    @Test
    public void test() throws IOException {
        Document document = new Document();
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new TextField("title", "(修改)谷歌地图之父跳槽facebook", Field.Store.YES));

        FSDirectory directory = FSDirectory.open(new File("indexDir"));

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());

        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        indexWriter.updateDocument(new Term("id", "1"), document);

        indexWriter.commit();

        indexWriter.close();
    }
}
