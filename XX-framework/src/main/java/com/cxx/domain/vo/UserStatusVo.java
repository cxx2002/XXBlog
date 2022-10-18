package com.cxx.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 陈喜喜
 * @date 2022-10-16 10:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserStatusVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
}
