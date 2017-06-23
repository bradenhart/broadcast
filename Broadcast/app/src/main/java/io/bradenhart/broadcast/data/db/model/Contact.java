package io.bradenhart.broadcast.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.bradenhart.broadcast.data.db.AppDbContract;

/**
 * Represents a contact than is part of a group.
 *
 * @author bradenhart
 */
@Entity(tableName = AppDbContract.Contact.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Group.class,
        parentColumns = AppDbContract.Group._ID,
        childColumns = AppDbContract.Contact.COLUMN_CONTACT_GROUP_ID,
        onDelete = ForeignKey.CASCADE))
public class Contact {

    @Nullable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AppDbContract.Contact._ID)
    Long id;

    @NonNull
    @ColumnInfo(name = AppDbContract.Contact.COLUMN_CONTACT_NAME)
    String name;

    @NonNull
    @ColumnInfo(name = AppDbContract.Contact.COLUMN_CONTACT_NUMBER)
    String number;

    @NonNull
    @ColumnInfo(name = AppDbContract.Contact.COLUMN_CONTACT_GROUP_ID, index = true)
    Long groupId;

    /**
     * Creates a contact with a name, number, and group id for inserting to a database.
     *
     * @param name    the name of the contact, not null
     * @param number  the number for the contact, not null
     * @param groupId the id of the group the contact belongs to, not null
     */
    public Contact(@NonNull String name, @NonNull String number, @NonNull Long groupId) {
        this.name = name;
        this.number = number;
        this.groupId = groupId;
    }

    /**
     * Creates a contact with an id, name, number, and group id.
     * Used when retrieving a contact from database as the id is known.
     *
     * @param id      the id for the retrieved contact, not null
     * @param name    the name of the contact, not null
     * @param number  the number for the contact, not null
     * @param groupId the id of the group the contact belongs to, not null
     */
    @Ignore
    public Contact(@NonNull Long id, @NonNull String name, @NonNull String number, @NonNull Long groupId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.groupId = groupId;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getNumber() {
        return number;
    }

    public void setNumber(@NonNull String number) {
        this.number = number;
    }

    @NonNull
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(@NonNull Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (!name.equals(contact.name)) return false;
        if (!number.equals(contact.number)) return false;
        return groupId.equals(contact.groupId);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + groupId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
