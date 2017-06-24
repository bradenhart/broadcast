package io.bradenhart.broadcast.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.bradenhart.broadcast.data.db.AppDbContract;

/**
 * Represents a message that the user can create
 * and send to a group of contacts.
 *
 * @author bradenhart
 */
@Entity(tableName = AppDbContract.Message.TABLE_NAME)
public class Message {

    @Ignore
    public static final String BLANK_TITLE = "";
    @Ignore
    public static final String BLANK_CONTENT = "";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AppDbContract.Message._ID)
    private long id;

    @ColumnInfo(name = AppDbContract.Message.COLUMN_MESSAGE_TITLE)
    private String title = BLANK_TITLE;

    @ColumnInfo(name = AppDbContract.Message.COLUMN_MESSAGE_CONTENT)
    private String content = BLANK_CONTENT;

    public Message() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        // if a null value is provided, keep the default value BLANK_TITLE
        // otherwise, use the provided title
        if (title != null) this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        // if a null value is provided, keep the default value BLANK_CONTENT
        // otherwise, use the provided content
        if (content != null) this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
