package com.xiaoshu.controller;

import com.xiaoshu.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : function description
 * ---------------------------------
 * @Author : deane
 * @Date : Create in 2019/9/22 10:29
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
@RestController
@Slf4j
public class FileClientController {

    @Autowired
    private FileService fileService;


    @PostMapping(value = "/uploadSingle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String postImage(@RequestParam("file") MultipartFile file) {
        log.info("start upload single file ...");
        fileService.createNewdFile(file);
        return null;
    }

    @PostMapping(value = "/uploadMultiple", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String postImages(@RequestParam("file") MultipartFile[] files) {
        log.info("start upload multiple files ...");
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                fileService.createNewdFile(file);
            }
        }
        return null;
    }

}
