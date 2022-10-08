package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-02 18:38
 */
@RestController
@RequestMapping("link")
public class LinkController {
    @Resource
    private LinkService linkService;

    @GetMapping("getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }

}
