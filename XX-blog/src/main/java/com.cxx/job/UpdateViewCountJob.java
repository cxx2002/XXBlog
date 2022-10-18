package com.cxx.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cxx.domain.entity.Article;
import com.cxx.service.ArticleService;
import com.cxx.utils.RedisCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈喜喜
 * @date 2022-10-08 11:25
 */
@Component
public class UpdateViewCountJob {
    @Resource
    private RedisCache redisCache;
    @Resource
    private ArticleService articleService;

    @Scheduled(cron = "0 1/5 * * * ?")
    public void updateViewCount(){
        System.out.println("定时更新mysql的浏览量");
        //获取redis中的浏览量  更新到数据库中
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        List<Article> articles = viewCountMap.entrySet().
                stream().
                map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue())).
                collect(Collectors.toList());


        for(Article article : articles){
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, article.getId());
            updateWrapper.set(Article::getViewCount, article.getViewCount());
            articleService.update(null,updateWrapper);
        }
        System.out.println("定时更新mysql的浏览量完成");

    }
}
