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
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * 模板
     */
    private static final String[] TEMPLATE_TYPE = {
            "vms/java/domain.java.vm",
            "vms/java/mapper.java.vm",
            "vms/java/service.java.vm",
            "vms/java/serviceImpl.java.vm",
            "vms/mapper/mapper.xml.vm",
    };

    /**
     * 生成代码
     */
    public static void generateCode(GenTable genTable) {
        //初始化vm方法
        initVelocity();
        String templateTypePath = TEMPLATE_TYPE[0];
        Template template = loadTemplate(templateTypePath);
        VelocityContext context = prepareContext(genTable);
        outputFile(templateTypePath, genTable, template, context);
    }

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
     * @param genTable 表信息
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
     * @param path 加载模版路径
     * @return 模版
     */
    public static Template loadTemplate(String path) {
        return Velocity.getTemplate(path, "UTF-8");
    }

    /**
     * 生成文件
     *
     * @param templateTypePath 模板类型路径
     * @param genTable         生成表信息
     * @param template         模版
     * @param context          模版变量
     */
    public static void outputFile(String templateTypePath, GenTable genTable, Template template, VelocityContext context) {
        //获取文件输出路径
        String outputPath = getFileName(templateTypePath, genTable);
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
        template.merge(context, fw);
        //释放资源
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String templateType, GenTable genTable) {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();

        // 大写类名
        String className = genTable.getClassName();

        String javaPath = PROJECT_PATH + "/" + MyStrUtils.replace(packageName, ".", "/");
        String mybatisPath = MYBATIS_PATH + "/";

        if (templateType.contains("domain.java.vm")) {
            fileName = MyStrUtils.format("{}/domain/{}.java", javaPath, className);
        } else if (templateType.contains("mapper.java.vm")) {
            fileName = MyStrUtils.format("{}/mapper/{}Mapper.java", javaPath, className);
        } else if (templateType.contains("service.java.vm")) {
            fileName = MyStrUtils.format("{}/service/I{}Service.java", javaPath, className);
        } else if (templateType.contains("serviceImpl.java.vm")) {
            fileName = MyStrUtils.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
        } else if (templateType.contains("controller.java.vm")) {
            fileName = MyStrUtils.format("{}/controller/{}Controller.java", javaPath, className);
        } else if (templateType.contains("mapper.xml.vm")) {
            fileName = MyStrUtils.format("{}/{}Mapper.xml", mybatisPath, className);
        }
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "generated" + File.separator + fileName;
    }
}
