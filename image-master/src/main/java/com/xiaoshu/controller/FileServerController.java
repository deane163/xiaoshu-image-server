package com.xiaoshu.controller;

import com.xiaoshu.loadbalance.Choose;
import com.xiaoshu.model.ServerConfig;
import com.xiaoshu.service.ImageUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * @Date : Create in 2019/9/21 20:33
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */

@RestController
@RequestMapping(value = "/file")
@Slf4j
@Api(value = "The Image upload")
public class FileServerController {
    @Autowired
    private ImageUploadService uploadService;

    @Autowired
    private Choose choose;


    @PostMapping(value = {"/uploadSingle", "/uploadFile"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "for single file upload ..")
    public String uploadFile(@ApiParam("file") @RequestParam("file") MultipartFile file) throws IOException {
        log.info("start upload the file :{}...", file);
        ServerConfig selector = choose.chooseServer();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("file", file);
        String result = uploadService.uploadSingleImage(file.getBytes(), paramMap, selector.getIp());
        return result;
    }

    @PostMapping(value = {"/uploadBatch", "/uploadFiles"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String uploadFiles(@RequestParam("files") MultipartFile[] files) {
        log.info("start batch upload files ...");
        //upload  files
        return null;
    }

}
