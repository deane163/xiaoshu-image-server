package com.xiaoshu.service.impl;

import com.xiaoshu.service.ImageUploadService;
import com.xiaoshu.util.OkHttpRestUtils;
import org.springframework.stereotype.Component;

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
 * @Date : Create in 2019/9/22 9:49
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
@Component
public class ImageUploadServiceImpl implements ImageUploadService {

    @Override
    public String uploadSingleImage(byte[] file, Map<String, Object> paramMap,
                                    String serverIp) {

        String result = OkHttpRestUtils.post(serverIp + "/uploadSingle", file, paramMap);
        return result;
    }

    @Override
    public String uploadMultiImage(Map<String, Object> paramsMap,
                                   String serverIp) {
        return null;
    }

    @Override
    public String uploadMultiImageNew(HashMap<String, Object> paramsMap,
                                      String serverIp) {
        return null;
    }
}
