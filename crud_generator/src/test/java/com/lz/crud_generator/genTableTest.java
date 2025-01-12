package com.lz.crud_generator;

import com.lz.crud_generator.mapper.GenTableColumnMapper;
import com.lz.crud_generator.mapper.GenTableMapper;
import com.lz.crud_generator.model.GenTable;
import com.lz.crud_generator.model.GenTableColumn;
import com.lz.crud_generator.service.GenTableService;
import com.lz.crud_generator.utils.MyStrUtils;
import com.lz.crud_generator.utils.VelocityUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    private GenTableMapper genTableMapper;

    @Autowired
    private GenTableService genTableService;

    @Test
    void getTableInfo() {
        List<GenTableColumn> genTableColumns = genTableColumnMapper.selectTableColumnByTableName("book_info");
        for (GenTableColumn genTableColumn : genTableColumns) {
            System.out.println("genTableColumn = " + genTableColumn);
        }
        GenTable genTable = genTableMapper.selectTableByTableName("book_info");
        System.out.println("genTable = " + genTable);
    }

    @Test
    void initTable() {
        GenTable genTable = genTableService.initTableInfo("book_info");
        System.out.println("genTable = " + genTable);
    }

    @Test
    void generateDomain() throws IOException {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //创建Velocity容器
        VelocityContext context = new VelocityContext();
        GenTable genTable = genTableService.initTableInfo("book_info");
        String tableName = genTable.getTableName();
        String tableComment = genTable.getTableComment();
        String className = genTable.getClassName();
        String packageName = genTable.getPackageName();
        String author = genTable.getAuthor();
        List<GenTableColumn> columns = genTable.getColumns();
        String isPk = genTable.getIsPk();
        context.put("tableName", tableName);
        context.put("tableComment", tableComment);
        context.put("className", className);
        context.put("packageName", packageName);
        context.put("author", author);
        context.put("columns", columns);
        context.put("isPk", isPk);

        //加载模板
        Template tpl = Velocity.getTemplate("vms/java/domain.java.vm", "UTF-8");

        //文件输出位置
        String projectPath = System.getProperty("user.dir");
        String[] packagePaths = MyStrUtils.split(packageName, ".");
        StringBuilder packagePath = new StringBuilder();
        for (String s : packagePaths) {
            System.out.println(s);
            packagePath.append(s).append(File.separator);
        }
        String outputPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "generated" + File.separator + packagePath + className + ".java";
        // 创建文件对象
        File outputFile = new File(outputPath);

        // 创建目录
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        // 创建文件写入器
        FileWriter fw = new FileWriter(outputFile);
        //合并数据到模板
        tpl.merge(context, fw);
        //释放资源
        fw.close();
    }

    @Test
    public void genTable() {
        GenTable genTable = genTableService.initTableInfo("book_info");
        VelocityUtils.generateCode(genTable);
    }

    @Test
    public void  generateTableValue() {
        GenTable genTable = genTableService.initTableInfo("book_info");
        System.out.println("genTable = " + genTable);
        String insertTable = MyStrUtils.format("insert into {} (", genTable.tableName);
        List<String> filed = new ArrayList<>();
        List<GenTableColumn> columns = genTable.getColumns();
        StringBuilder builder = new StringBuilder(insertTable);
        Scanner scanner = new Scanner(System.in); // 移动到这里

        for (int i = 0; i < columns.size(); i++) {
            GenTableColumn column = columns.get(i);
            System.out.println("当前字段：" + column.getColumnName() + "\t字段类型：" + column.getColumnType());
            System.out.println("需要添加：1,不需要添加：2");

            int require = scanner.nextInt();

            if (require == 1) {
                filed.add(column.getColumnKey());
                if (i == columns.size() - 1) {
                    builder.append(column.getColumnName()).append(",");
                } else {
                    builder.append(column.getColumnName());
                }
            }
            scanner.next();
        }

        System.out.println("builder = " + builder);
        scanner.close(); // 移到方法的最后
    }
}
