package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-05 21:24
 */
@RestController
public class UploadController {
    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
