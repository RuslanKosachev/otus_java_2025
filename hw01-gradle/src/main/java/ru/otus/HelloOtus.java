package ru.otus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


public class HelloOtus {
    public static void main(String... args) {

        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("row2", "column3", 23);
        table.put("row1", "column2", 12);
        table.put("row2", "column1", 21);
        table.put("row1", "column1", 11);
        table.put("row1", "column3", 13);
        table.put("row2", "column2", 22);

        System.out.println(table);
    }
}
