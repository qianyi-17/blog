package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;

import com.qianyi.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 千亦
 * @create 2022-11-01-15:52
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        ResponseResult result = uploadService.uploadImg(img);
        return result;
    }
}
