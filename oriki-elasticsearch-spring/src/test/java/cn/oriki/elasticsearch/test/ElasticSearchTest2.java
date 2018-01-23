package cn.oriki.elasticsearch.test;

import cn.oriki.elasticsearch.dao.ArticleRepository;
import cn.oriki.elasticsearch.domain.Article;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ElasticSearchTest2 {

    @Autowired
    private ArticleRepository articleRepository;

    //保存或更新一个文档
    @Test
    public void testSaveOrUpdate() {
        Article article = new Article();
        article.setId(101);
        article.setTitle("我是好人");
        article.setContent("我是一个好人");
        articleRepository.save(article);
    }

    @Test
    public void testDelete() {
        //可以接收主键值或对象
        articleRepository.delete(101);
    }

    @Test
    public void testFindAll() {
        Iterable<Article> articleIter = articleRepository.findAll();
        for (Article article : articleIter) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindOne() {
        Article article = articleRepository.findOne(101);
        System.out.println(article);
    }

    //复杂查询
    @Test
    public void testFindFuza() {
        //1）词条精确匹配
//		TermQueryBuilder query=new TermQueryBuilder("title", "好人");
        //2)通配符匹配
//		WildcardQueryBuilder query=new WildcardQueryBuilder("title", "*人*");
        //3)相似度查询
//		FuzzyQueryBuilder query=new FuzzyQueryBuilder("content", "gaod");
        //4）全字段匹配,先分词再查询，默认走配置的分词器searchAnalyzer="ik"
//		QueryStringQueryBuilder query=new QueryStringQueryBuilder("坏人");
        //5)组合匹配
        BoolQueryBuilder query = new BoolQueryBuilder();
//		query.should(queryBuilder);//并集
//		query.must(queryBuilder);//交集
//		query.mustNot(queryBuilder)//补集
        //查询：先分词再搜索
        Iterable<Article> articleIter = articleRepository.search(query);
        for (Article article : articleIter) {
            System.out.println(article);
        }
    }


}

