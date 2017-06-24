package io.bradenhart.broadcast.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class for the Broadcast SQLite Database.
 *
 * @author bradenhart
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "broadcast_db.db";
    private static final int DB_VERSION = 1;

    private static DbOpenHelper dbHelper;

    private final String SQL_CREATE_MESSAGE = "create table " + AppDbContract.Message.TABLE_NAME + "("
            + AppDbContract.Message._ID + " integer primary key autoincrement, "
            + AppDbContract.Message.COLUMN_MESSAGE_TITLE + " text, "
            + AppDbContract.Message.COLUMN_MESSAGE_CONTENT + " text not null"
            + ");";

    private final String SQL_CREATE_GROUP = "create table " + AppDbContract.Group.TABLE_NAME + "("
            + AppDbContract.Group._ID + " integer primary key autoincrement, "
            + AppDbContract.Group.COLUMN_GROUP_NAME + " text"
            + ");";

    private final String SQL_CREATE_CONTACT = "create table " + AppDbContract.Contact.TABLE_NAME + "("
            + AppDbContract.Contact._ID + " integer primary key autoincrement, "
            + AppDbContract.Contact.COLUMN_CONTACT_NAME + " text not null, "
            + AppDbContract.Contact.COLUMN_CONTACT_NUMBER + " text not null, "
            + AppDbContract.Contact.COLUMN_CONTACT_GROUP_ID + " integer not null"

            + "foreign key(" + AppDbContract.Contact.COLUMN_CONTACT_GROUP_ID + ")"
            + " references " + AppDbContract.Group.TABLE_NAME + "(" + AppDbContract.Group._ID + ")"
            + ");";

    private final String SQL_DELETE_MESSAGE = "drop table if exists " + AppDbContract.Message.TABLE_NAME;
    private final String SQL_DELETE_GROUP   = "drop table if exists " + AppDbContract.Group.TABLE_NAME;
    private final String SQL_DELETE_CONTACT = "drop table if exists " + AppDbContract.Contact.TABLE_NAME;

    private DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    static synchronized DbOpenHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbOpenHelper(context);
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
