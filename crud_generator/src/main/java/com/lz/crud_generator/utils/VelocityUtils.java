package com.lz.crud_generator.utils;

import com.lz.crud_generator.model.GenTable;
import com.lz.crud_generator.model.GenTableColumn;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @Project: crud_generator
 * @Package: com.lz.crud_generator.utils
 * @Author: YY
 * @CreateTime: 2024-10-07  22:49
 * @Description: VelocityUtils
 * 模版工具类
 * @Version: 1.0
 */
public class VelocityUtils {
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable) {
        //创建Velocity容器
        VelocityContext context = new VelocityContext();
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
        return context;
    }

    /**
     * 加载模板
     *
     * @param path 加载模版
     * @return 模版
     */
    public static Template loadTemplate(String path) {
        return Velocity.getTemplate(path, "UTF-8");
    }

    public static void outputFile(String packageName,String className){
        Template tpl = loadTemplate("");
        VelocityContext context = prepareContext(new GenTable());
        //文件输出位置
        String projectPath = System.getProperty("user.dir");
        String[] packagePaths = MyStrUtils.split(packageName,".");
        System.out.println(packageName);
        StringBuilder packagePath = new StringBuilder();
        for (String s : packagePaths) {
            System.out.println(s);
            packagePath.append(s).append(File.separator);
        }
        String outputPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "generated" + File.separator + packagePath + className+".java";
        // 创建文件对象
        File outputFile = new File(outputPath);

        // 创建目录
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        // 创建文件写入器
        FileWriter fw = null;
        try {
            fw = new FileWriter(outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //合并数据到模板
        tpl.merge(context, fw);
        //释放资源
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
