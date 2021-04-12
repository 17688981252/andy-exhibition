package com.zel.business.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import com.zelin.plugin.mybatis.generator.MbpGenerator;

public class Generator {

    private static String SETTING_FILE_PATH = "base.properties";

    public static void main(String[] args) {
        final String classPath = ClassUtil.getClassPath();
        final String fullPath = classPath + SETTING_FILE_PATH;
        MbpGenerator.Companion.execute(FileUtil.newFile(fullPath));
    }

}
