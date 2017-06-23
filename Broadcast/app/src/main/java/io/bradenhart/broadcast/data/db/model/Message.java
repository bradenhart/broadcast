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

    @Nullable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AppDbContract.Message._ID)
    private Long id;

    @Nullable
    @ColumnInfo(name = AppDbContract.Message.COLUMN_MESSAGE_TITLE)
    private String title;

    @NonNull
    @ColumnInfo(name = AppDbContract.Message.COLUMN_MESSAGE_CONTENT)
    private String content;

    /**
     * Creates a message with a title and content for inserting to a database.
     *
     * @param title   the title of the message, not null
     * @param content the content of the message, not null
     */
    public Message(@Nullable String title, @NonNull String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Creates a message with an id, title, and content.
     * Used when retrieving a message from database as the id is known.
     *
     * @param id      the id for the retrieved message, not null
     * @param title   the title of the message, not null
     * @param content the content of the message, not null
     */
    @Ignore
    public Message(@NonNull Long id, @Nullable String title, @NonNull String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (title != null ? !title.equals(message.title) : message.title != null) return false;
        return content.equals(message.content);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + content.hashCode();
        return result;
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
