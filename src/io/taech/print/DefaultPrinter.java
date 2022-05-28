package io.taech.print;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DefaultPrinter implements Printer {

    private static final String DEFAULT_NULL_STRING = "(null)";
    private static final int DEFAULT_MAX_COLUMN_LENGTH = 30;
    private static final String APEX = "+";
    private static final int EACH_SPACE_LENGTH = 2;
    private static final String WALL = "|";
    private static final String LINEFEED = "\n";

    private static final String PRINT_TARGET_IS_NULL = "This print target is null.";

    private void setColumnData(final Field [] fields, final List<Column> columns) {

        for (int i = 0; i < fields.length; i++) {
            try {
                final Field field = fields[i];
                field.setAccessible(true);

                final String filedName = field.getName();
                final Integer fieldNameLength = filedName.getBytes(StandardCharsets.UTF_8).length;
                final Integer length = fieldNameLength;

                columns.add(new Column(length + EACH_SPACE_LENGTH, filedName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setFieldValues(Object obj, Field [] fields, List<Column> columns, List<Map<String, String>> columnMapList) {
        Map<String, String> columnMap = new HashMap<>();
        IntStream.range(0, fields.length).forEach(i -> {
            try {
                final Field field = fields[i];
                field.setAccessible(true);

                Object value = field.get(obj);
                if (value == null)
                    value = DEFAULT_NULL_STRING;

                String strValue = value.toString();

                final String fieldName = field.getName();
                Integer length = strValue.getBytes(StandardCharsets.UTF_8).length;
                final Column column = columns.get(i);
                if (length > DEFAULT_MAX_COLUMN_LENGTH) {
                    strValue = strValue.substring(0, (DEFAULT_MAX_COLUMN_LENGTH - 3)).concat("...");
                    length = DEFAULT_MAX_COLUMN_LENGTH;
                }

                if (column.getLength() <= (length + EACH_SPACE_LENGTH))
                    column.setLength(length + EACH_SPACE_LENGTH);


                columnMap.put(fieldName, strValue);
            } catch(Exception skipped){}
        });
        columnMapList.add(columnMap);
    }

    private void stackLayer(final List<Column> columns, final StringBuilder builder) {
        builder.append(APEX);
        columns.stream().forEach(c -> {
            IntStream.range(0, c.getLength()).forEach((n) -> builder.append("-"));
            builder.append(APEX);
        });
        builder.append(LINEFEED);
    }

    private void stackHeader(final List<Column> columns, final StringBuilder builder) {
        stackLayer(columns, builder);
        columns.stream().forEach(c ->
                builder.append(String.format("%s %-" + (c.length - 1) + "s", WALL, c.name)));
        builder.append(WALL + LINEFEED);
        stackLayer(columns, builder);
    }

    private void stackBody(final List<Column> columns, final List<Map<String, String>> columnMapList, final StringBuilder builder) {
        columnMapList.stream().forEach((cm) -> {
            columns.stream().forEach(c ->
                builder.append(String.format("%s %-" + (c.length - 1) + "s", WALL, cm.get(c.name))));
            builder.append(WALL + LINEFEED);
            stackLayer(columns, builder);
        });
    }



    public String print(final Object obj, final String message) throws Exception {

        if(obj instanceof List)
            return printList(obj, message);

        return print(Arrays.asList(obj), "");
    }
    public String printList(final Object objList, final String message) throws Exception {
        final List<Object> list = (List) objList;
        if (list.isEmpty()) {
            return PRINT_TARGET_IS_NULL;
        }

        final StringBuilder builder = new StringBuilder();
        final List<Column> columns = new ArrayList<>();
        final List<Map<String, String>> columnMapList = new ArrayList<>();

        final Object o = list.get(0);
        setColumnData(o.getClass().getDeclaredFields(), columns);
        builder.append(String.format("\nList<%s> ↓  Message: \"%s\"\n", o.getClass().getSimpleName(), message));

        list.stream().forEach(obj -> {

            final Field[] fields = obj.getClass().getDeclaredFields();

            setFieldValues(obj, fields, columns, columnMapList);
        });



        //== 컬럼 명 ==//
        stackHeader(columns, builder);

        //== 컬럼 값 ==//
        stackBody(columns, columnMapList, builder);


        return builder.toString();
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
