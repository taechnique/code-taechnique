package io.taech.print;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DefaultPrinter implements Printer {

    private static final Integer DEFAULT_NULL_LENGTH = 6;
    private static final String DEFAULT_NULL_STRING = "(null)";

    @Override
    public void print(Object obj) throws Exception {
        Objects.requireNonNull(obj);
        final List<Column> columns = new ArrayList<>();
        final Field[] fields = obj.getClass().getDeclaredFields();
        Integer totalLength = 0;

        Map<String, Object> columnMap = new HashMap<>();

        for (int i = 0; i < fields.length; i++) {
            try {
                final Field field = fields[i];
                field.setAccessible(true);

                final String filedName = field.getName();
                final Integer fieldNameLength = filedName.getBytes(StandardCharsets.UTF_8).length;
                final Integer length = Math.max(DEFAULT_NULL_LENGTH, fieldNameLength);
                totalLength += (length + 3);
                columns.add(new Column(length, filedName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < fields.length; i++) {
            try {
                final Field field = fields[i];
                field.setAccessible(true);

                final Object value = field.get(obj);
                final String fieldName = field.getName();
                final Integer length = value.toString().getBytes(StandardCharsets.UTF_8).length;
                final int columnLength = columns.get(i).length;
                if(columnLength <= length) {
                    totalLength -= columnLength;
                    totalLength += length;
                    columns.get(i).setLength(length);
                }
                columnMap.put(fieldName, value);
            } catch(Exception e) {
                throw e;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("+");
        for (int i = 0; i < totalLength; i++) {
            builder.append("-");
        }
        builder.append("+\n|");

        for (int i = 0; i < columns.size(); i++) {
            builder.append("  ");
            builder.append(columns.get(i).name);
            builder.append("  |");
        }
        builder.append("\n+");
        for (int i = 0; i < totalLength; i++) {
            builder.append("-");
        }
        builder.append("+\n|");

        for (int i = 0; i < columns.size(); i++) {
            builder.append("  ");
            builder.append(columnMap.get(columns.get(i).name));
            builder.append("  |");
        }
        builder.append("\n+");
        for (int i = 0; i < totalLength; i++) {
            builder.append("-");
        }
        builder.append("+\n");
        System.out.println(builder);
    }

    @Override
    public void print(Collection<?> collection) throws Exception {

    }
    
    static class Column {
        private int length;
        private String name;
        
        public Column (int length, String name) {
            this.length = length;
            this.name = name;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
