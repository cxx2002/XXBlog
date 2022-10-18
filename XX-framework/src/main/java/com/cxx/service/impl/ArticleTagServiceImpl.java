package com.cxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.domain.entity.ArticleTag;
import com.cxx.mapper.ArticleTagMapper;
import com.cxx.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
* @author 陈喜喜
* @description 针对表【xx_article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2022-10-13 10:07:39
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




