package io.bradenhart.broadcast.data.db;

import java.util.List;

import io.bradenhart.broadcast.data.db.model.Contact;
import io.bradenhart.broadcast.data.db.model.Group;
import io.bradenhart.broadcast.data.db.model.Message;

/**
 * Implements the DbHelper interface to handle CRUD operations on the
 * SQLite database.
 *
 * @author bradenhart
 */
class AppDbHelper implements DbHelper {


    @Override
    public long insertMessage(Message message) {
        return 0;
    }

    @Override
    public List<Message> getAllMessages() {
        return null;
    }

    @Override
    public Message getMessageById(Long id) {
        return null;
    }

    @Override
    public int deleteMessage(Long id) {
        return 0;
    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public Group getGroupById(Long id) {
        return null;
    }

    @Override
    public int deleteGroup(Long id) {
        return 0;
    }

    @Override
    public List<Contact> getAllContacts() {
        return null;
    }

    @Override
    public List<Contact> getContactsInGroup(Long groupId) {
        return null;
    }

    @Override
    public Contact getContactById(Long id) {
        return null;
    }

    @Override
    public int deleteContact(Long id) {
        return 0;
    }

    @Override
    public int updateContact(Long id, Contact contact) {
        return 0;
    }

    @Override
    public long insertContact(Contact contact) {
        return 0;
    }

    @Override
    public int updateGroup(Long id, Group message) {
        return 0;
    }

    @Override
    public long insertGroup(Group message) {
        return 0;
    }

    @Override
    public int updateMessage(Long id, Message message) {
        return 0;
    }
}
