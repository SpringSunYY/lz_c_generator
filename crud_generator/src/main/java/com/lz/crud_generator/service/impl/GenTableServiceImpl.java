package com.lz.crud_generator.service.impl;

import com.lz.crud_generator.mapper.GenTableColumnMapper;
import com.lz.crud_generator.mapper.GenTableMapper;
import com.lz.crud_generator.model.GenTable;
import com.lz.crud_generator.model.GenTableColumn;
import com.lz.crud_generator.service.GenTableService;
import com.lz.crud_generator.utils.GenTableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: crud_generator
 * @Package: com.lz.crud_generator.service.impl
 * @Author: YY
 * @CreateTime: 2024-09-28  19:57
 * @Description: GenTableColumnServiceImpl
 * @Version: 1.0
 */
@Service
public class GenTableServiceImpl implements GenTableService {
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Autowired
    private GenTableMapper genTableMapper;

    /**
     * @description: 初始化表信息
     * @author: YY
     * @method: initTableInfo
     * @date: 2024/9/28 20:48
     * @param:
     * @param: tableName
     * @return: com.lz.crud_generator.model.GenTable
     **/
    @Override
    public GenTable initTableInfo(String tableName) {
        GenTable genTable = genTableMapper.selectTableByTableName(tableName);
        GenTableUtils.initTable(genTable);
        List<GenTableColumn> tableColumns = genTableColumnMapper.selectTableColumnByTableName(tableName);
        genTable.setColumns(tableColumns);
        //初始化要生成的表信息
        GenTableUtils.initTableInfo(genTable);
        return genTable;
    }
}
