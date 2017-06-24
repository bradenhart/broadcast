package io.bradenhart.broadcast.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import io.bradenhart.broadcast.data.db.dao.ContactDao;
import io.bradenhart.broadcast.data.db.dao.GroupDao;
import io.bradenhart.broadcast.data.db.dao.MessageDao;
import io.bradenhart.broadcast.data.db.model.Contact;
import io.bradenhart.broadcast.data.db.model.Group;
import io.bradenhart.broadcast.data.db.model.Message;

/**
 * Created by bradenhart on 23/06/17.
 */
@Database(entities = {Message.class, Group.class, Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = AppDbContract.DB_NAME;

    public abstract MessageDao messageDao();

    public abstract GroupDao groupDao();

    public abstract ContactDao contactDao();
}
