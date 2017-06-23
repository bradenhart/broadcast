package io.bradenhart.broadcast.data.db;

import android.provider.BaseColumns;

/**
 * Contract class to define database information.
 * Contains classes for the respective tables in the database.
 *
 * @author bradenhart
 */
public class AppDbContract {

    private AppDbContract() {}

    /**
     * Defines Message table name and columns.
     * Message table stores messages to be sent.
     */
    public static class Message implements BaseColumns {
        public static final String TABLE_NAME = "Message";
        public static final String COLUMN_MESSAGE_TITLE = "title";
        public static final String COLUMN_MESSAGE_CONTENT = "content";
    }

    /**
     * Defines Group table name and columns.
     * Group table stores groups for contacts to be associated with.
     */
    public static class Group implements BaseColumns {
        public static final String TABLE_NAME = "Group_";
        public static final String COLUMN_GROUP_NAME = "name";
    }

    /**
     * Defines Contact table name and columns.
     * Contact table stores contacts and associates them with a group.
     */
    public static class Contact implements BaseColumns {
        public static final String TABLE_NAME = "Contact";
        public static final String COLUMN_CONTACT_NAME = "name";
        public static final String COLUMN_CONTACT_NUMBER = "number";
        public static final String COLUMN_CONTACT_GROUP_ID = "groupId";
    }


}
