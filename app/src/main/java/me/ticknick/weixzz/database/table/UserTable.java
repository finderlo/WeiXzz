package me.ticknick.weixzz.database.table;

/**
 * Created by Finderlo on 2016/8/6.
 */
public class UserTable {

        public static final String TABLE_NAME_USER = " UserModel ";
        public static final String CREATE_TABLE_SQL = "create table " +TABLE_NAME_USER+
                "( " +
                "tableId                    integer primary key autoincrement ," +
                "id                         text ,"     +
                "idstr                      text ,"     +
                "json                       text "      +
                ");";

}
