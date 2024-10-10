package com.lz.crud_generator.utils;

import com.lz.crud_generator.config.GenTableConfig;
import com.lz.crud_generator.model.GenTable;
import com.lz.crud_generator.model.GenTableColumn;

import java.util.Arrays;
import java.util.List;

import static com.lz.crud_generator.model.constant.GenTableConstants.*;

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
     * @param: genTable
     **/
    public static void initTableInfo(GenTable genTable, List<GenTableColumn> tableColumns) {
        for (GenTableColumn tableColumn : tableColumns) {
            //初始化Java类型
            initTableColumnJavaType(tableColumn);
            //初始化Java字段
            initTableColumnJavaFiled(tableColumn);
            //判断主键
            if (tableColumn.getColumnKey().equals(PRI)) {
                genTable.setIsPk(tableColumn.getColumnName());
                genTable.setIsPkJavaType(tableColumn.getJavaType());
                genTable.setIsPkJavaFiled(tableColumn.getJavaField());
            }
        }
        genTable.setColumns(tableColumns);
    }

    /**
     * 初始化Java字段
     * @param tableColumn
     */
    private static void initTableColumnJavaFiled(GenTableColumn tableColumn) {
        tableColumn.setJavaField(MyStrUtils.toCamelCase(tableColumn.getColumnName()));
    }

    /**
     * 初始化Java类型
     * @param tableColumn
     */
    private static void initTableColumnJavaType(GenTableColumn tableColumn) {
        tableColumn.setJavaType(TYPE_STRING);
        String dataType = getDbType(tableColumn.getDataType());
        if (arraysContains(COLUMNTYPE_TIME, dataType)) {
            tableColumn.setJavaType(TYPE_DATE);
        } else if (arraysContains(COLUMNTYPE_NUMBER, dataType)) {
            // 如果是浮点型 统一用BigDecimal
            String[] str = MyStrUtils.split(MyStrUtils.substringBetween(tableColumn.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                tableColumn.setJavaType(TYPE_BIGDECIMAL);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                tableColumn.setJavaType(TYPE_INTEGER);
            }
            // 长整形
            else {
                tableColumn.setJavaType(TYPE_LONG);
            }
        }
    }

    public static String getDbType(String columnType) {
        //如果有括号且在后面，则截取括号之前的内容
        if (MyStrUtils.indexOf(columnType, "(") > 0) {
            return MyStrUtils.substringBefore(columnType, "(");
        } else {
            return columnType;
        }
    }

    /**
     * 驼峰命名
     *
     * @param arr
     * @param targetValue
     */
    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * @description: 初始化表信息
     * @param: genTable
     **/
    public static void initTable(GenTable genTable) {
        String tableName = genTable.getTableName();
        genTable.setClassName(initTableName(tableName));
        genTable.setAuthor(GenTableConfig.getAuthor());
        genTable.setPackageName(GenTableConfig.getPackageName());
    }

    /**
     * @description: 初始化表名
     * @param: tableName
     **/
    private static String initTableName(String tableName) {
        //是否要去掉表头
        boolean autoRemovePre = GenTableConfig.getAutoRemovePre();
        String tablePrefix = GenTableConfig.getTablePrefix();
        if (autoRemovePre && MyStrUtils.isNotEmpty(tablePrefix)) {
            String[] searchList = MyStrUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return MyStrUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     *
     * @param replacementm 替换值
     * @param searchList   替换列表
     */
    public static String replaceFirst(String replacementm, String[] searchList) {
        String text = replacementm;
        for (String searchString : searchList) {
            if (replacementm.startsWith(searchString)) {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }
}
