package com.cxx.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 陈喜喜
 * @date 2022-10-17 11:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewArticleVo {



        private Long id;
        //标题
        private String title;


        private Date createTime;


}
