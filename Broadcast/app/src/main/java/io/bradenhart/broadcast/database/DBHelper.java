package io.bradenhart.broadcast.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.bradenhart.broadcast.database.DBContract.*;

/**
 * Helper class for the Broadcast SQLite Database.
 *
 * @author bradenhart
 */
class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "broadcast_db.db";
    private static final int DB_VERSION = 1;

    private static DBHelper dbHelper;

    private final String SQL_CREATE_MESSAGE = "create table " + Message.TABLE_NAME + "("
            + Message._ID + " integer primary key autoincrement, "
            + Message.COLUMN_MESSAGE_TITLE + " text, "
            + Message.COLUMN_MESSAGE_CONTENT + " text not null"
            + ");";

    private final String SQL_CREATE_GROUP = "create table " + Group.TABLE_NAME + "("
            + Group._ID + " integer primary key autoincrement, "
            + Group.COLUMN_GROUP_NAME + " text"
            + ");";

    private final String SQL_CREATE_CONTACT = "create table " + Contact.TABLE_NAME + "("
            + Contact._ID + " integer primary key autoincrement, "
            + Contact.COLUMN_CONTACT_NAME + " text not null, "
            + Contact.COLUMN_CONTACT_NUMBER + " text not null, "
            + Contact.COLUMN_CONTACT_GROUP_ID + " integer not null"

            + "foreign key(" + Contact.COLUMN_CONTACT_GROUP_ID + ")"
            + " references " + Group.TABLE_NAME + "(" + Group._ID + ")"
            + ");";

    private final String SQL_DELETE_MESSAGE = "drop table if exists " + Message.TABLE_NAME;
    private final String SQL_DELETE_GROUP   = "drop table if exists " + Group.TABLE_NAME;
    private final String SQL_DELETE_CONTACT = "drop table if exists " + Contact.TABLE_NAME;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    static synchronized DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MESSAGE);
        db.execSQL(SQL_CREATE_GROUP);
        db.execSQL(SQL_CREATE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MESSAGE);
        db.execSQL(SQL_DELETE_GROUP);
        db.execSQL(SQL_DELETE_CONTACT);
        onCreate(db);
    }
}
