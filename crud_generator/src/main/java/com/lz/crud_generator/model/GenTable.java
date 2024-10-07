package com.lz.crud_generator.model;

import java.util.List;

/**
 * @Project: crud_generator
 * @Package: com.lz.crud_generator.model
 * @Author: YY
 * @CreateTime: 2024-09-25  12:12
 * @Description: GenTable
 * @Version: 1.0
 */
public class GenTable {
    public String tableName;              // 表名
    public String tableComment;           // 表注释
    private String className;             // 实体类名称(首字母大写)
    private String packageName;           // 生成包路径
    private String author;                // 作者
    private String isPk;                  // 主键
    private List<GenTableColumn> columns; // 列表信息
    private static final long serialVersionUID = 1L;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<GenTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<GenTableColumn> columns) {
        this.columns = columns;
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    @Override
    public String toString() {
        return "GenTable{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", className='" + className + '\'' +
                ", packageName='" + packageName + '\'' +
                ", author='" + author + '\'' +
                ", isPk='" + isPk + '\'' +
                ", columns=" + columns +
                '}';
    }
}
