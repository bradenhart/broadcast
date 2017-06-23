package io.bradenhart.broadcast.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.bradenhart.broadcast.data.db.AppDbContract;
import io.bradenhart.broadcast.data.db.model.Group;

/**
 * @author bradenhart
 */
@Dao
public interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if a conflict occurs, just don't insert the group
    long insertGroup(final Group message);

    @Query("select * from " + AppDbContract.Group.TABLE_NAME)
    List<Group> getAllGroups();

    @Query("select * from " + AppDbContract.Group.TABLE_NAME
            + " where " + AppDbContract.Group._ID + " = :id")
    Group getGroupById(Long id);

    @Update
    void updateGroup(Group message);

    @Delete
    void deleteGroup(Group group);

}
