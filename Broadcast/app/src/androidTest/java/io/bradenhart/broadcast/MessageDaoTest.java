package io.bradenhart.broadcast;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import io.bradenhart.broadcast.data.db.AppDatabase;
import io.bradenhart.broadcast.data.db.dao.MessageDao;
import io.bradenhart.broadcast.data.db.model.Message;

/**
 * Created by bradenhart on 23/06/17.
 */
@RunWith(AndroidJUnit4.class)
public class MessageDaoTest {

    private MessageDao messageDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        messageDao = db.messageDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void passes() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void insertsMessageIntoDatabaseAndRetrievesIt() {
        // create a message with a title and content
        String title = "Attn: Board Members";
        String content = "There is a cyclone headed towards us.";
        Message message = new Message(title, content);
        // the message has no id (it's null)
        // the id is created when the message is inserted into the db
        long id = messageDao.insertMessage(message);
        // set the id so that our assertion works below
        message.setId(id);

        // get the message from the db
        Message byId = messageDao.getMessageById(id);

        // the message we created should equal the message we retrieved as both will
        // have the same id now
        assertThat(byId, equalTo(message));
    }

    @Test
    public void insertsSomeMessagesAndGetsAllFromDatabase() {

    }

}
