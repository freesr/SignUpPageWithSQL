package com.example.loginpagewithsql;

import android.provider.BaseColumns;

public class DataBaseSchema {

    public static final class TableDB implements BaseColumns{

        public static final String TABLE_NAME = "usernames";
        public static  final  String COLUMN_FIRSTNAME = "Firstname";
        public static  final  String COLUMN_LASTNAME = "Lastname";


    }
}
