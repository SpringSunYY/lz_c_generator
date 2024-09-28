package com.lz.crud_generator.mapper;

import com.lz.crud_generator.model.GenTable;
import com.lz.crud_generator.model.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface GenTableMapper {
    GenTable selectTableByTableName(@Param("tableName") String tableName);
}
