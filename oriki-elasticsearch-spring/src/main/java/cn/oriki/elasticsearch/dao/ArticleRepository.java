package cn.oriki.elasticsearch.dao;

import cn.oriki.elasticsearch.domain.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {

}

