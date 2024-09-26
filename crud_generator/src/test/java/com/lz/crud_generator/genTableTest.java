package com.lz.crud_generator;

import com.lz.crud_generator.mapper.GenTableColumnMapper;
import com.lz.crud_generator.model.GenTable;
import com.lz.crud_generator.model.GenTableColumn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Project: crud_generator
 * @Package: com.lz.crud_generator
 * @Author: YY
 * @CreateTime: 2024-09-26  20:16
 * @Description: genTableTest
 * @Version: 1.0
 */
@SpringBootTest
public class genTableTest {
    @Autowired
    public GenTableColumnMapper genTableColumnMapper;

    @Test
    void getTableInfo(){
        List<GenTableColumn> genTableColumns = genTableColumnMapper.selectTableByTableName("book_info");
        for (GenTableColumn genTableColumn : genTableColumns) {
            System.out.println("genTableColumn = " + genTableColumn);
        }
    }
}
