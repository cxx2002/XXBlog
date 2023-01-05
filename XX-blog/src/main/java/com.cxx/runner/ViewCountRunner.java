package com.cxx.runner;

import com.cxx.domain.entity.Article;
import com.cxx.mapper.ArticleMapper;
import com.cxx.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈喜喜
 * @date 2022-10-08 10:26
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private RedisCache redisCache;

    //启动时运行的
    @Override
    public void run(String... args) throws Exception {
        //查询该文章信息 id（key） 浏览量（value）     存到redis中
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream().
                collect(Collectors.toMap(
                        article1 -> article1.getId().toString(),
                        article -> article.getViewCount().intValue()
                ));
        redisCache.setCacheMap("article:viewCount", viewCountMap);
    }
}
