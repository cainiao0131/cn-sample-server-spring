package org.cainiao.sample.config.dao;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.cainiao.common.dao.ColumnDefine;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据实体类生成 DDL<br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@RequiredArgsConstructor
public class DDLGenerator {

    private final String packageName;
    private final String outputPath;

    public void generateDDL() throws IOException, ClassNotFoundException {
        List<Class<?>> classes = getClasses(packageName);
        StringBuilder ddl = new StringBuilder();
        for (Class<?> clazz : classes) {
            classToTable(clazz, ddl);
        }
        writeToFile(outputPath, ddl.toString());
    }

    private void classToTable(Class<?> clazz, StringBuilder ddl) {
        String tableName = getTableName(clazz);
        if (!StringUtils.hasText(tableName)) {
            return;
        }
        String schemaTableName = "public." + tableName;
        ddl.append("-- ").append(schemaTableName).append(" definition\n\n");
        ddl.append("CREATE TABLE IF NOT EXISTS ").append(schemaTableName).append(" (\n");

        List<String> primaryKey = new ArrayList<>();
        List<String> uniqueKeys = new ArrayList<>();
        List<Pair<String, String>> fieldComments = new ArrayList<>();

        List<Field> fields = getAllFields(clazz);
        int size = fields.size();
        for (int i = 0; i < size; i++) {
            fieldToColumn(fields.get(i), ddl, i, size, fieldComments, primaryKey, uniqueKeys);
        }
        setConstraint(primaryKey, uniqueKeys, ddl, tableName);
        ddl.append(");\n\n");
        setComment(fieldComments, ddl, schemaTableName);
        ddl.append("\n");
    }

    private void setComment(List<Pair<String, String>> fieldComments, StringBuilder ddl, String schemaTableName) {
        for (Pair<String, String> fieldComment : fieldComments) {
            ddl.append("COMMENT ON COLUMN ").append(schemaTableName).append(".")
                .append(fieldComment.getLeft()).append(" IS '").append(fieldComment.getRight()).append("';\n");
        }
    }

    private void setConstraint(List<String> primaryKey, List<String> uniqueKeys, StringBuilder ddl, String tableName) {
        if (!primaryKey.isEmpty()) {
            ddl.append("\tCONSTRAINT ").append(tableName)
                .append("_pk PRIMARY KEY (").append(primaryKey.get(0)).append(")");
        }
        if (!uniqueKeys.isEmpty()) {
            ddl.append(",\n");
            ddl.append(uniqueKeys.stream().map(uniqueKey -> String
                    .format("\tCONSTRAINT %s_unique UNIQUE (%s)", tableName, uniqueKey))
                .collect(Collectors.joining(",\n")));
        }
        ddl.append("\n");
    }

    private void fieldToColumn(Field field, StringBuilder ddl, int i, int size,
                               List<Pair<String, String>> fieldComments,
                               List<String> primaryKey, List<String> uniqueKeys) {
        if ("serialVersionUID".equals(field.getName())) {
            return;
        }
        TableField tableFieldAnnotation = null;
        if (field.isAnnotationPresent(TableField.class)) {
            tableFieldAnnotation = field.getAnnotation(TableField.class);
            if (!tableFieldAnnotation.exist()) {
                return;
            }
        }
        ColumnDefine columnDefine = field.isAnnotationPresent(ColumnDefine.class)
            ? field.getAnnotation(ColumnDefine.class) : null;

        String comment = null;
        if (field.isAnnotationPresent(Schema.class)) {
            comment = field.getAnnotation(Schema.class).description();
        }
        boolean hasComment = StringUtils.hasText(comment);

        String columnName = getColumnName(field, tableFieldAnnotation);
        boolean isPrimary = field.isAnnotationPresent(TableId.class);
        String pgSqlType = getPgSQLType(field, columnName, isPrimary, primaryKey);
        ddl.append("\t").append(columnName).append(" ").append(pgSqlType);
        boolean hasDefault = columnDefine != null && columnDefine.hasDefault();
        if (hasDefault) {
            // 有默认值
            String defaultValue = columnDefine.defaultValue();
            if (StringUtils.hasText(defaultValue)) {
                ddl.append(" ").append(defaultValue);
            } else {
                switch (pgSqlType) {
                    case "timestamp" -> ddl.append(" now()");
                    case "boolean" -> ddl.append(" false");
                    default -> {
                    }
                }
            }
        }
        FieldStrategy fieldStrategy = tableFieldAnnotation == null ? null : tableFieldAnnotation.insertStrategy();
        // 主键一定是 not null 的
        boolean notNull = isPrimary
            || FieldStrategy.NOT_NULL.equals(fieldStrategy) || FieldStrategy.NOT_EMPTY.equals(fieldStrategy);
        ddl.append(notNull ? " NOT NULL" : " NULL");

        if (hasComment) {
            fieldComments.add(Pair.of(columnName, comment));
        }
        if (columnDefine != null && columnDefine.unique()) {
            uniqueKeys.add(columnName);
        }

        if (i < size - 1 || !primaryKey.isEmpty() || !uniqueKeys.isEmpty()) {
            ddl.append(",");
        }
        if (hasComment) {
            ddl.append(" -- ").append(comment);
        }
        ddl.append("\n");
    }

    private static void writeToFile(String outputPath, String ddl) throws IOException {
        Path filePath = Paths.get(outputPath);
        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, ddl);
    }

    private String getPgSQLType(Field field, String columnName, boolean isPrimary, List<String> primaryKey) {
        if (isPrimary) {
            primaryKey.add(columnName);
            TableId tableIdAnnotation = field.getAnnotation(TableId.class);
            if (tableIdAnnotation.type() == IdType.AUTO) {
                return "bigserial";
            }
        }
        Class<?> javaType = field.getType();
        if (javaType == String.class) {
            return "varchar";
        }
        if (javaType == LocalDateTime.class) {
            return "timestamp";
        }
        if (javaType == long.class || javaType == Long.class) {
            return "int8";
        }
        if (javaType == int.class || javaType == Integer.class) {
            return "integer";
        }
        if (javaType == boolean.class || javaType == Boolean.class) {
            return "boolean";
        }
        if (javaType == double.class || javaType == Double.class) {
            return "double precision";
        }
        return "text";
    }

    private String getColumnName(Field field, TableField tableFieldAnnotation) {
        if (tableFieldAnnotation != null) {
            return tableFieldAnnotation.value();
        }
        return convertCamelToSnake(field.getName());
    }

    private String convertCamelToSnake(String camelCase) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append("_").append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private String getTableName(Class<?> clazz) {
        if (clazz.isAnnotationPresent(TableName.class)) {
            return clazz.getAnnotation(TableName.class).value();
        }
        return null;
    }

    private List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
        List<String> directories = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            directories.add(resource.getFile());
        }

        ArrayList<Class<?>> classes = new ArrayList<>();
        for (String directory : directories) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private List<Class<?>> findClasses(String directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File dir = new File(directory.replace("%20", " "));
        if (!dir.exists()) {
            return classes;
        }
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file.getAbsolutePath(), packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }
}
