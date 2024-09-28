package com.lz.crud_generator.utils;

import com.lz.crud_generator.config.GenTableConfig;
import com.lz.crud_generator.model.GenTable;

/**
 * @Project: crud_generator
 * @Package: com.lz.crud_generator.utils
 * @Author: YY
 * @CreateTime: 2024-09-28  20:12
 * @Description: GenTableUtils 代码生成工具类
 * @Version: 1.0
 */
public class GenTableUtils {
    /**
     * @description: 初始化表信息
     * @author: YY
     * @method: initTableInfo
     * @date: 2024/9/28 20:19
     * @param:
     * @param: genTable
     * @return: void
     **/
    public static void initTableInfo(GenTable genTable) {

    }

    /**
     * @description: 初始化表信息
     * @author: YY
     * @method: initTable
     * @date: 2024/9/28 20:49
     * @param:
     * @param: genTable
     * @return: void
     **/
    public static void initTable(GenTable genTable) {
        String tableName = genTable.getTableName();
        String tableComment = genTable.getTableComment();
        genTable.setClassName(initTableName(tableName));
        genTable.setAuthor(GenTableConfig.getAuthor());
        genTable.setPackageName(GenTableConfig.getPackageName());
        System.out.println("genTable = " + genTable);

    }

    /**
     * @description: 初始化表名
     * @author: YY
     * @method: initTableName
     * @date: 2024/9/28 20:57
     * @param:
     * @param: tableName
     * @return: java.lang.String
     **/
    private static String initTableName(String tableName) {
        //是否要去掉表头
        boolean autoRemovePre = GenTableConfig.getAutoRemovePre();
        String tablePrefix = GenTableConfig.getTablePrefix();
        if (autoRemovePre && MyStrUtils.isNotEmpty(tablePrefix)){
            String[] searchList = MyStrUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return MyStrUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     *
     * @param replacementm 替换值
     * @param searchList 替换列表
     * @return
     */
    public static String replaceFirst(String replacementm, String[] searchList)
    {
        String text = replacementm;
        for (String searchString : searchList)
        {
            if (replacementm.startsWith(searchString))
            {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }
}
