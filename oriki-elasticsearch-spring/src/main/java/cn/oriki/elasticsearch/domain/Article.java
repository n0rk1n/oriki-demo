package cn.oriki.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

//文章的实体类
@Document//es文档对象的标识
        (indexName = "idx_blog3",//指定索引的名字
                type = "article")//文档类型，一般和实体类一样即可
public class Article {
    @Id//es主键标识
    @Field(//字段属性配置
            type = FieldType.Integer//字段类型:es会根据类型进行不同处理
            /*是否索引
             * analyzed:该字段分词，并索引，可以查询出来（多个词条）
             * not_analyzed：不分词，但索引，可以查询出来（整个字段的值作为一个词条）
             * no：不索引，没有词条，不能根据该字段查询
             *
             */
            , index = FieldIndex.not_analyzed//id可以选第二种
            //文档是否保存：true，Lucene的文档中有，可以查询出来，false，Lucene的文档中没有，只能作为查询条件，不能打印结果。
            , store = true
    )
    private Integer id;
    @Field(
            type = FieldType.String
            , index = FieldIndex.analyzed//分词索引
            , analyzer = "ik"//存储文档的时候分词用的分词器，不指定使用默认分词器
            , searchAnalyzer = "ik"//查询的时候，默认的分词器
            , store = true
    )
    private String title;
    @Field(
            type = FieldType.String
            , index = FieldIndex.analyzed//分词索引
            , analyzer = "ik"//存储文档的时候分词用的分词器，不指定使用默认分词器
            , searchAnalyzer = "ik"//查询的时候，默认的分词器
            , store = true
    )
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}