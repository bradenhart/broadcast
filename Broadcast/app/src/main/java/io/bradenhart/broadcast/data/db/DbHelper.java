package io.bradenhart.broadcast.data.db;

import java.util.List;

import io.bradenhart.broadcast.data.db.model.Contact;
import io.bradenhart.broadcast.data.db.model.Group;
import io.bradenhart.broadcast.data.db.model.Message;


/**
 * Interface to describe what CRUD operations are supported by our database.
 */
public interface DbHelper {

    /* crud - Message */
    long insertMessage(final Message message);
    List<Message> getAllMessages();
    Message getMessageById(Long id);
    int updateMessage(Long id, final Message message);
    int deleteMessage(Long id);

    /* crud - Group */
    long insertGroup(final Group message);
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    int updateGroup(Long id, final Group message);
    int deleteGroup(Long id);


    /* crud - Contact */
    long insertContact(final Contact contact);
    List<Contact> getAllContacts();
    List<Contact> getContactsInGroup(Long groupId);
    Contact getContactById(Long id);
    int updateContact(Long id, final Contact contact);
    int deleteContact(Long id);
}
