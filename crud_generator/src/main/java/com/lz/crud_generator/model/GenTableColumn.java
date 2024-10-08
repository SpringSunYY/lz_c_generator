package com.lz.crud_generator.model;

/**
 * @Project: crud_generator
 * @Package: com.lz.crud_generator.model
 * @Author: YY
 * @CreateTime: 2024-09-25  12:01
 * @Description: GenTableColumn
 * @Version: 1.0
 */
public class GenTableColumn {
    public String columnName;                // 列名
    public String dataType;                  // 数据类型
    public String columnType;                // 列类型
    public String columnComment;             // 列注释
    private String isPk;                     // 主键
    private String columnKey;                // 键类型
    private String isNullable;               // 是否可为空
    private String javaType;                 // Java类型
    private String javaField;                // Java字段
    private static final long serialVersionUID = 1L;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaField() {
        return javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    @Override
    public String toString() {
        return "\nGenTableColumn{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", isPk='" + isPk + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", javaType='" + javaType + '\'' +
                ", javaField='" + javaField + '\'' +
                '}';
    }
}
