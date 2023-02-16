package ru.netology.chats

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.chats.exceptions.MessageDeletedException
import ru.netology.chats.exceptions.MessageIsNotExistException
import ru.netology.notes.NoteComment
import ru.netology.notes.Notes
import ru.netology.notes.NotesService
import ru.netology.notes.exceptions.CommentAlreadyDeletedException

class ChatServiceTest {

    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }

    @Test
    fun add() {
        val message = ChatMessage ()
        ChatService.add(100, message)
        val result = message.id
        assertEquals(result, 1)
    }

    @Test
    fun delete() {
        val message = ChatMessage ()
        ChatService.add(100, message)
        val result = ChatService.delete(100)
        assertEquals(result, true)
    }

    @Test
    fun getChatList() {
        val message = ChatMessage (text = "Hello")
        ChatService.add(100, message)
        val result = ChatService.getChatList()
        assertEquals(result, 1)
    }

    @Test
    fun getUnreadChatsCount() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        val result = ChatService.getUnreadChatsCount()
        assertEquals(result, 1)
    }

    @Test
    fun addMessage() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        val result = ChatService.addMessage(100, message2)
        assertEquals(result, 2)
    }

    @Test
    fun addMessageUnsuccessful() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        val result = ChatService.addMessage(10, message2)
        assertEquals(result, -1)
    }

    @Test
    fun deleteMessage() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        val result = ChatService.deleteMessage(2)
        assertEquals(result, 2)
    }

    @Test
    fun deleteMessageUnsuccessful() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        val result = ChatService.deleteMessage(3)
        assertEquals(result, -1)
    }

    @Test(expected = MessageDeletedException::class)
    fun shouldThrowDeleteMessage() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        ChatService.deleteMessage(2)
        val result = ChatService.deleteMessage(2)
    }

    @Test
    fun editMessage() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        val result = ChatService.editMessage(2, "Good morning")
        assertEquals(result, "Good morning")
    }

    @Test(expected = MessageIsNotExistException::class)
    fun shouldThrowNotExistEditMessage() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        val result = ChatService.editMessage(3, "Good morning")
    }

    @Test(expected = MessageDeletedException::class)
    fun shouldThrowEditMessage() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        ChatService.deleteMessage(2)
        val result = ChatService.editMessage(2, "Good morning")
    }

//    @Test
//    fun getMessagesReadList() {
//        val message = ChatMessage (text = "Hello")
//        val message2 = ChatMessage (text = "Hi")
//        ChatService.add(100, message)
//        ChatService.add(100, message2)
//        val result = ChatService.getMessagesReadList(100, 1, 1)
//        assertEquals(result, "true")
//    }

    @Test(expected = MessageIsNotExistException::class)
    fun shouldThrowGetMessagesReadList() {
        val message = ChatMessage (text = "Hello")
        val message2 = ChatMessage (text = "Hi")
        ChatService.add(100, message)
        ChatService.add(100, message2)
        val result = ChatService.getMessagesReadList(100, 3, 2)
    }
}