package io.taech.print;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;

public class DefaultPrinter implements Printer {

    private static final int DEFAULT_NULL_LENGTH = 6;
    private static final String DEFAULT_NULL_STRING = "(null)";
    private static final int DEFAULT_COLUMN_LENGTH = 30;
    private static final String APEX = "+";

    @Override
    public void print(final Object obj) throws Exception {
        Objects.requireNonNull(obj);
        final List<Column> columns = new ArrayList<>();
        final Field[] fields = obj.getClass().getDeclaredFields();

        final Map<String, Object> columnMap = new HashMap<>();

        for (int i = 0; i < fields.length; i++) {
            try {
                final Field field = fields[i];
                field.setAccessible(true);

                final String filedName = field.getName();
                final Integer fieldNameLength = filedName.getBytes(StandardCharsets.UTF_8).length;
                final Integer length = Math.max(DEFAULT_NULL_LENGTH, fieldNameLength);

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
                final Column column = columns.get(i);
                final int columnLength = column.length;

                if(columnLength <= (length + 1))
                    column.setLength(length + 1);
                columnMap.put(fieldName, value);

            } catch(Exception e) {
                throw e;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(APEX);

        columns.stream().forEach(c -> {
            System.out.println("c.getLength() = " + c.getLength());
            IntStream.range(0, c.getLength()).forEach((n) -> builder.append("-"));
            builder.append("+");
        });

        builder.append("\n");
        //== 컬럼 명 ==//
        for(int i = 0;i < columns.size();i++) {
            final Column column = columns.get(i);
            builder.append("| ");
            builder.append(String.format("%-"+column.length+"s",column.name));
            System.out.println("column = " + column.length);
            int valueLength = columnMap.get(column.name).toString().getBytes(StandardCharsets.UTF_8).length;
            int gap = column.length - valueLength;
            System.out.printf("valueLength: %d, gap: %s\n", valueLength, gap);
            while(--gap > 0)
                builder.append(" ");

        }
        builder.append(" |\n");
        builder.append(APEX);

        columns.stream().forEach(c -> {
            IntStream.range(0, c.getLength()).forEach((n) -> builder.append("-"));
            builder.append("+");
        });
        builder.append("\n");
        //== 컬럼 값 ==//
        for (int i = 0; i < columns.size(); i++) {
            final Column column = columns.get(i);
            builder.append("| ");
            builder.append(String.format("%-" + column.length + "s ", columnMap.get(column.name)));
            int valueLength = columnMap.get(column.name).toString().getBytes(StandardCharsets.UTF_8).length;
            int gap = column.length - valueLength;
            while (--gap > 0)
                builder.append(" ");

        }
        builder.append(" |\n");

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

        public int getLength() {
            return this.length;
        }
    }
}
