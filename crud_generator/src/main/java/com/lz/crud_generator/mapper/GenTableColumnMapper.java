package com.lz.crud_generator.mapper;

import com.lz.crud_generator.model.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenTableColumnMapper {
    List<GenTableColumn> selectTableByTableName(String tableName);
}
