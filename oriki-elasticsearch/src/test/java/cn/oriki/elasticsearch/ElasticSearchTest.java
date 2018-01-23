package cn.oriki.elasticsearch;

import cn.oriki.elasticsearch.domain.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

public class ElasticSearchTest {


    /**
     * 创建索引
     *
     * @throws Exception
     */
    @Test
    public void testCreateIndex() throws Exception {
        /**
         * 创建搜索服务器对象
         * 默认端口号9300
         */
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        //创建索引，参数为索引的名字
        client.admin().indices().prepareCreate("index_blog").get();

        client.close();
    }

    /**
     * 删除索引
     *
     * @throws Exception
     */
    @Test
    public void testDeleteIndex() throws Exception {
        /**
         * 创建搜索服务器对象
         * 默认端口号9300
         */
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));


        //删除索引
        client.admin().indices().prepareDelete("index_blog").get();

        //关闭连接
        client.close();

    }

    /**
     * 建立映射
     *
     * @throws Exception
     */
    @Test
    public void testCreateMapping() throws Exception {
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        // 创建用来组装json对象的XContentBuilder对象
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject().
                        startObject("properties").
                        startObject("id")
                .field("type", "integer").field("store", "yes")
                .endObject()
                .startObject("title")
                .field("type", "string").field("store", "yes")
                .endObject()
                .startObject("content")
                .field("type", "string").field("store", "yes")
                .endObject()
                .endObject()
                .endObject();

        // 创建映射关系
        PutMappingRequest mappingRequest = Requests.putMappingRequest("index_blog")
                .type("article")
                .source(builder);

        // 释放资源
        client.close();
    }


    /**
     * 文档的创建，修改和删除
     *
     * @throws Exception
     */
    @Test
    public void testOperDocument() throws Exception {
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        Article article = new Article();
        article.setId(1001);
        article.setTitle("我是好人");
        article.setContent("我是一个善良的好人");

        ObjectMapper objectMapper = new ObjectMapper();

        // 创建文档，参数1为索引的名称，参数2为文档的类型，参数3为文档的主键（如果不指定，随机生成）
        //参数1：索引表的名字
        //参数2：文档类型（通过映射分词条）
        //参数3：文档的主键，如果不指定，则默认生成随机索引主键，如果指定，则索引主键和业务主键一样。
        client.prepareIndex("index_blog", "article", article.getId().toString())
                .setSource(objectMapper.writeValueAsString(article))
                .get();
        //更新文档:底层先删除，再添加
//		client.prepareUpdate("idx_blog1", "article",article.getId().toString())
//		.setDoc(objectMapper.writeValueAsString(article))
//		.get();
        //删除文档
        client.prepareDelete("index_blog", "article", article.getId().toString())
                .get();

        //关闭连接
        client.close();
    }


    /**
     * 批量插入数据
     *
     * @throws Exception
     */
    @Test
    public void testAddDocumentBatch() throws Exception {
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 1; i <= 100; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle("我是好人" + i);
            article.setContent("我是一个善良的好人" + i);
            //建立文档
            client.prepareIndex("index_blog", "article", article.getId().toString())
                    .setSource(objectMapper.writeValueAsString(article))
                    .get();
        }

        //关闭连接
        client.close();
    }

    /**
     * 简单查询
     *
     * @throws Exception
     */
    @Test
    public void testBaseQuery() throws Exception {
        //创建连接搜索服务器对象
        //默认的服务的端口是9300
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        //搜索数据
        SearchResponse searchResponse = client.prepareSearch("index_blog")//从哪个索引中检索数据
                .setTypes("article")//检索数据类别，如果不写，则是所有类别
                //设置查询策略
                .setQuery(QueryBuilders.matchAllQuery())//所有的数据
                .get();

        // 通过结果响应对象
        // 获取命中的数据信息
        SearchHits hits = searchResponse.getHits();
        // 1）获取命中次数，查询有多少结果对象
        System.out.println("查询的结果的总条数：" + hits.getTotalHits() + ",最高分：" + hits.getMaxScore());
        // 2）获取命中的数据元素的集合
        Iterator<SearchHit> searchHitIterator = hits.iterator();
        while (searchHitIterator.hasNext()) {
            //依次检索每个命中对象
            SearchHit searchHit = searchHitIterator.next();
            System.out.println("分数：" + searchHit.getScore());

            //获取命中的对象关联的源文档的字符串格式内容:json字符串
            System.out.println("文档对象：" + searchHit.getSourceAsString());
            //获取命中对象的关联的源文档的某个属性的值
            System.out.println("文档的某个字段，如title：" + searchHit.getSource().get("title"));

        }

        //关闭连接
        client.close();
    }


    /**
     * 分页查询
     *
     * @throws Exception
     */
    @Test
    public void testPageQuery() throws Exception {
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        //搜索数据
        SearchResponse searchResponse = client.prepareSearch("index_blog")//从哪个索引中检索数据
                .setTypes("article")//检索数据类别，如果不写，则是所有类别
                //设置查询策略
                .setQuery(QueryBuilders.matchAllQuery())
                //每页10条，第二页
                .setFrom(10)//起始的行号，默认是0
                .setSize(20)//最大记录数，默认是10
                .get();

        // 通过结果响应对象
        // 获取命中的数据信息
        SearchHits hits = searchResponse.getHits();
        // 1）获取命中次数，查询有多少结果对象
        System.out.println("++++++++++查询的结果的总条数：" + hits.getTotalHits() + ",最高分：" + hits.getMaxScore());
        // 2）获取命中的数据元素的集合
        Iterator<SearchHit> searchHitIterator = hits.iterator();
        while (searchHitIterator.hasNext()) {
            //依次检索每个命中对象
            SearchHit searchHit = searchHitIterator.next();
            System.out.println("分数：" + searchHit.getScore());

            //获取命中的对象关联的源文档的字符串格式内容:json字符串
            System.out.println("文档对象：" + searchHit.getSourceAsString());
            //获取命中对象的关联的源文档的某个属性的值
            System.out.println("文档的某个字段，如title：" + searchHit.getSource().get("title"));

        }

        //关闭连接
        client.close();
    }

    /**
     * 数据高亮显示
     *
     * @throws Exception
     */
    @Test
    public void testHihtlightQuery() throws Exception {
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        //搜索数据
        //获取搜索结果的响应对象
        SearchResponse searchResponse = client.prepareSearch("index_blog")//从哪个索引中检索数据
                .setTypes("article")//检索数据类别，如果不写，则是所有类别
                //设置查询策略
                .setQuery(
                        // 1.词条term精确匹配
                        QueryBuilders.termQuery("title", "好")
                        //2.词条通配符匹配
//						QueryBuilders.wildcardQuery("title", "*好*")
                        //3.全文全字段检索，检索该文档的所有的字段的词条，只要有匹配就ok
//						QueryBuilders.queryStringQuery("善")
                        //自动分词后，再根据分词后的词条，匹配存储的词条，因为现在英文分词器，一个字一个字分词。
//						QueryBuilders.queryStringQuery("邪恶的人")
                        //4.相似度匹配（英文可以，中文不行）
//						QueryBuilders.fuzzyQuery("title", "a")
                )
                // 设置高亮设置
                // 高亮结果可能显示不全，最多100个字符，后面的没有，自己需要加...
                .addHighlightedField("title")//对哪个字段实现高亮显示
                .addHighlightedField("content")//对哪个字段实现高亮显示
                .setHighlighterPreTags("<em>")//高亮的前置标签
                .setHighlighterPostTags("</em>")//高亮的后置标签
                .get();

        //通过结果响应对象，来获取我们需要的信息
        //获取命中的数据信息
        SearchHits hits = searchResponse.getHits();
        // 1）获取命中次数，查询有多少结果对象
        System.out.println("++++++++++查询的结果的总条数：" + hits.getTotalHits() + ",最高分：" + hits.getMaxScore());
        // 2）获取命中的数据元素的集合
        Iterator<SearchHit> searchHitIterator = hits.iterator();
        while (searchHitIterator.hasNext()) {
            //依次检索每个命中对象
            SearchHit searchHit = searchHitIterator.next();
            System.out.println("分数：" + searchHit.getScore());

            //获取命中的对象关联的源文档的字符串格式内容:json字符串
            System.out.println("文档对象：" + searchHit.getSourceAsString());
            //获取命中对象的关联的源文档的某个属性的值
            System.out.println("文档的某个字段，如title：" + searchHit.getSource().get("title"));

            // 2.获取高亮结果，拼接成高亮字段值
            //获取到所有的高亮的字段对象map
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            //根据字段获取高亮结果
            HighlightField titleHighlightField = highlightFields.get("title");
            //多个高亮片段（长度有限制的）
            Text[] fragments = titleHighlightField.getFragments();
            String titleHighlight = "";
            //将高亮片段拼接到一起
            for (Text text : fragments) {
                titleHighlight += text + "...";//如果片段太长，加省略号
            }

            System.out.println("title的高亮结果：" + titleHighlight);
        }

        //关闭连接
        client.close();
    }

}
