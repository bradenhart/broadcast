package io.bradenhart.broadcast.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.bradenhart.broadcast.data.db.AppDbContract;

/**
 * Represents a group of contacts that messages
 * can be sent to.
 *
 * @author bradenhart
 */
@Entity(tableName = AppDbContract.Group.TABLE_NAME)
public class Group {

    @Nullable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AppDbContract.Group._ID)
    private Long id;

    @Nullable
    @ColumnInfo(name = AppDbContract.Group.COLUMN_GROUP_NAME)
    private String name;

    /**
     * Creates a group with an id.
     * Used when retrieving a group from a database as the id is known.
     *
     * @param id the id for the retrieved group, not null
     */
    @Ignore
    public Group(@NonNull Long id) {
        this.id = id;
    }

    /**
     * Creates a group with a name for inserting to a database.
     *
     * @param name the name of the group, not null
     */
    public Group(@Nullable String name) {
        this.name = name;
    }

    /**
     * Creates a group with an id and name.
     * Used when retrieving a group from a database as the id is known.
     *
     * @param id   the id for the retrieved group, not null
     * @param name the name of the group, not null
     */
    @Ignore
    public Group(@NonNull Long id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;
        return name != null ? name.equals(group.name) : group.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
