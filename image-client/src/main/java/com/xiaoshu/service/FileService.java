package com.xiaoshu.service;

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
 * @Date : Create in 2019/9/22 10:30
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
public interface FileService {
    /**
     * save image to locate Direction
     *
     * @param file
     * @return
     */
    String createNewdFile(MultipartFile file);

    /**
     * @param text
     * @param regex
     * @param replacement
     * @return
     */
    String replaceLast(String text, String regex, String replacement);
}
