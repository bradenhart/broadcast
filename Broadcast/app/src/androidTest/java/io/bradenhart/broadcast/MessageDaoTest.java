package io.bradenhart.broadcast;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

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

//    @Test
    public void passes() {
        assertEquals(1, 1);
    }

    @Test
    public void createsDatabase() {
        assertNotNull(db);
    }

    @Test
    public void createsMessageDao() {
        assertNotNull(messageDao);
    }

    /* inserting a message with a title and content succeeds */
    @Test
    public void insertsMessage_WithTitleAndContent_Succeeds() {
        // create valid values for title and content
        String title = "Valid title";
        String content = "Valid content";

        // create a message and set the title and content
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // retrieve the message we just inserted
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(title, retMessage.getTitle());
        assertEquals(content, retMessage.getContent());
    }

    /* inserting a message with no title uses default value */
    @Test
    public void insertsMessage_WithNoTitle_UsesDefaultTitle() {
        // create valid value for content
        String content = "Valid content";

        // create a message and set the content
        Message message = new Message();
        message.setContent(content);

        // make sure that the message has the default title value
        assertEquals(message.getTitle(), Message.BLANK_TITLE);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // retrieve the message we just inserted
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(Message.BLANK_TITLE, retMessage.getTitle());
        assertEquals(content, retMessage.getContent());
    }

    /* inserting a message with no content uses default value */
    @Test
    public void insertMessage_WithNoContent_UsesDefaultContent() {
        // create valid value for title
        String title = "Valid title";

        // create a message and set the title
        Message message = new Message();
        message.setTitle(title);

        // make sure that the message has the default content value
        assertEquals(message.getContent(), Message.BLANK_CONTENT);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // retrieve the message we just inserted
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(title, retMessage.getTitle());
        assertEquals(Message.BLANK_CONTENT, retMessage.getContent());
    }

    /* inserting a message with no title and content uses default values */
    @Test
    public void insertsMessage_WithNoTitleOrContent_UsesDefaultValues() {
        // create a message
        Message message = new Message();

        // make sure the message has the default values for title and content
        assertEquals(message.getTitle(), Message.BLANK_TITLE);
        assertEquals(message.getContent(), Message.BLANK_CONTENT);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // retrieve the message we just inserted
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(Message.BLANK_TITLE, retMessage.getTitle());
        assertEquals(Message.BLANK_CONTENT, retMessage.getContent());
    }

    @Test
    public void retrievesAllInsertedMessages() {
        // create three messages
        Message m1 = new Message();
        m1.setTitle("title1");
        m1.setContent("content1");

        Message m2 = new Message();
        m2.setTitle("title2");
        m2.setContent("content2");

        Message m3 = new Message();
        m3.setTitle("title3");
        m3.setContent("content3");

        // insert three messages into db, get generated ids
        long id1 = messageDao.insertMessage(m1);
        assertEquals(1, id1);
        long id2 = messageDao.insertMessage(m2);
        assertEquals(2, id2);
        long id3 = messageDao.insertMessage(m3);
        assertEquals(3, id3);

        // retrieve all of the messages
        List<Message> retMessages = messageDao.getAllMessages();

        // make sure that we have retrieved the correct amount of messages
        assertEquals(3, retMessages.size());

        // check m1
        // make sure that the message fields are the values we expect
        assertEquals(id1, retMessages.get(0).getId());
        assertEquals("title1", retMessages.get(0).getTitle());
        assertEquals("content1", retMessages.get(0).getContent());

        // check m2
        // make sure that the message fields are the values we expect
        assertEquals(id2, retMessages.get(1).getId());
        assertEquals("title2", retMessages.get(1).getTitle());
        assertEquals("content2", retMessages.get(1).getContent());

        // check m3
        // make sure that the message fields are the values we expect
        assertEquals(id3, retMessages.get(2).getId());
        assertEquals("title3", retMessages.get(2).getTitle());
        assertEquals("content3", retMessages.get(2).getContent());
    }

    @Test
    public void updatesMessage_WithNewTitle() {
        // create valid values for title and content
        String title = "Valid title";
        String content = "Valid content";

        // create a message and set the title and content
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // update the title
        String updatedTitle = "Updated title";
        message.setId(id); // set the id so the message can be updated
        message.setTitle(updatedTitle);

        // update the message
        messageDao.updateMessage(message);

        // retrieve the message
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(updatedTitle, retMessage.getTitle());
        assertEquals(content, retMessage.getContent());
    }

    @Test
    public void updatesMessage_WithNewContent() {
        // create valid values for title and content
        String title = "Valid title";
        String content = "Valid content";

        // create a message and set the title and content
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // update the content
        String updatedContent = "Updated content";
        message.setId(id); // set the id so the message can be updated
        message.setContent(updatedContent);

        // update the message
        messageDao.updateMessage(message);

        // retrieve the message
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(title, retMessage.getTitle());
        assertEquals(updatedContent, retMessage.getContent());
    }

    @Test
    public void updatesMessage_WithNewTitleAndContent() {
        // create valid values for title and content
        String title = "Valid title";
        String content = "Valid content";

        // create a message and set the title and content
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // update the title and content
        String updatedTitle = "Updated title";
        String updatedContent = "Updated content";
        message.setId(id); // set the id so the message can be updated
        message.setTitle(updatedTitle);
        message.setContent(updatedContent);

        // update the message
        messageDao.updateMessage(message);

        // retrieve the message
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(updatedTitle, retMessage.getTitle());
        assertEquals(updatedContent, retMessage.getContent());
    }

    @Test
    public void updatesMessage_WithNoChanges() {
        // create valid values for title and content
        String title = "Valid title";
        String content = "Valid content";

        // create a message and set the title and content
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // don't update the title or the content
        message.setId(id); // set the id so the message can be updated

        // update the message
        messageDao.updateMessage(message);

        // retrieve the message
        Message retMessage = messageDao.getMessageById(id);

        // make sure that the message fields are the values we expect
        assertEquals(id, retMessage.getId());
        assertEquals(title, retMessage.getTitle());
        assertEquals(content, retMessage.getContent());
    }

    @Test
    public void deleteMessage() {
        // create valid values for title and content
        String title = "Valid title";
        String content = "Valid content";

        // create a message and set the title and content
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);

        // insert the message into the db, get the generated id
        long id = messageDao.insertMessage(message);

        // get all messages from the db
        List<Message> retMessages = messageDao.getAllMessages();

        // make sure that there is only one message
        assertEquals(1, retMessages.size());

        // delete the message
        message.setId(id); // set the id so the message can be deleted
        messageDao.deleteMessage(message);

        // get all message from the db again
        retMessages = messageDao.getAllMessages();

        // make sure that there are no messages in the db
        assertEquals(0, retMessages.size());
    }

}
