package com.xiaoshu.service;

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
 * @Date : Create in 2019/9/22 9:48
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
public interface ImageUploadService {

    /**
     * 上传文件到指定的服务器上
     *
     * @param file
     * @param paramMap
     * @param serverIp
     * @return
     */
    String uploadSingleImage(byte[] file, Map<String, Object> paramMap, String serverIp);

    /**
     * 批量上传文件到图片服务器上
     *
     * @param paramsMap
     * @param serverIp
     * @return
     */
    String uploadMultiImage(Map<String, Object> paramsMap, String serverIp);

    /**
     * 批量上传文件到图片服务器上,重点排序
     *
     * @param paramsMap
     * @param serverIp
     * @return
     */
    String uploadMultiImageNew(HashMap<String, Object> paramsMap, String serverIp);

}
