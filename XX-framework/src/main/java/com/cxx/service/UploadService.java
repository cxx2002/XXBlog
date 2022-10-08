package com.cxx.service;

import com.cxx.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 陈喜喜
 * @date 2022-10-05 21:28
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
