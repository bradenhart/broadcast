package io.bradenhart.broadcast.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.bradenhart.broadcast.data.db.AppDbContract;
import io.bradenhart.broadcast.data.db.model.Contact;

/**
 * @author bradenhart
 */
@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if a conflict occurs, just don't insert the contact
    long insertContact(final Contact contact);

    @Query("select * from " + AppDbContract.Contact.TABLE_NAME)
    List<Contact> getAllContacts();

    @Query("select * from " + AppDbContract.Contact.TABLE_NAME
            + " where " + AppDbContract.Contact.COLUMN_CONTACT_GROUP_ID + " = :groupId")
    List<Contact> getContactsInGroup(Long groupId);

    @Query("select * from " + AppDbContract.Contact.TABLE_NAME
            + " where " + AppDbContract.Contact._ID + " = :id")
    Contact getContactById(Long id);

    @Update
    void updateContact(Contact contact);

    @Update
    void deleteContact(Contact contact);

}
