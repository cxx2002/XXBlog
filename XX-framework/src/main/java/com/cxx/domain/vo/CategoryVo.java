package com.cxx.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈喜喜
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

    private Long id;
    private String name;
    //描述
    private String description;
}
