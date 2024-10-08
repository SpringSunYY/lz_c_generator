package com.lz.crud_generator.mapper;

import com.lz.crud_generator.model.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GenTableColumnMapper {
    List<GenTableColumn> selectTableColumnByTableName(@Param("tableName") String tableName);
}
