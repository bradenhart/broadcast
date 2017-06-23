package io.bradenhart.broadcast.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.bradenhart.broadcast.data.db.AppDbContract;
import io.bradenhart.broadcast.data.db.model.Message;

/**
 * @author bradenhart
 */
@Dao
public interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if a conflict occurs, just don't insert the message
    long insertMessage(Message message);

    @Query("select * from " + AppDbContract.Message.TABLE_NAME)
    List<Message> getAllMessages();

    @Query("select * from " + AppDbContract.Message.TABLE_NAME
            + " where " + AppDbContract.Message._ID + " = :id")
    Message getMessageById(Long id);

    @Update
    void updateMessage(Message message);

    @Delete
    void deleteMessage(Message message);
}
