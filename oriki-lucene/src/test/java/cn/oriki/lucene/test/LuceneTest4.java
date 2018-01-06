package cn.oriki.lucene.test;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneTest4 {

    @Test
    public void test() throws IOException {
        FSDirectory directory = FSDirectory.open(new File("indexDir"));

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        indexWriter.deleteAll();

        indexWriter.commit();
    }
}
