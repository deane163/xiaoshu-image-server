package com.xiaoshu.service.impl;

import com.xiaoshu.service.FileService;
import com.xiaoshu.util.DirMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
 * @Date : Create in 2019/9/22 10:31
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
@Component
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${application.message.images.direction}")
    private String filePath;

    /**
     * 生成新的文件名
     *
     * @param fileName
     * @return
     */
    private static String generateNewdFileName(String fileName) {
        int r = (int) (Math.random() * 900 + 100);
        if (fileName.lastIndexOf(".") != -1) {
            return String.valueOf(System.currentTimeMillis()) + r + fileName.substring(fileName.lastIndexOf("."), fileName.length());
        } else {
            return String.valueOf(System.currentTimeMillis()) + r;
        }
    }

    @Override
    public String createNewdFile(MultipartFile file) {
        logger.info("save new file into local disk ...");
        String fileName = file.getName();
        fileName = generateNewdFileName(fileName);
        File outFile = new File(filePath + fileName);
        try {
            DirMaker.createFile(outFile);
            file.transferTo(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public String replaceLast(String text, String regex, String replacement) {

        return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

}
