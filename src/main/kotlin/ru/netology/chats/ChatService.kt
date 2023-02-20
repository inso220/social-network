package ru.netology.chats

import ru.netology.chats.exceptions.MessageDeletedException
import ru.netology.chats.exceptions.MessageIsNotExistException

object ChatService {
    private var idNext = 0
    private var chats = mutableMapOf<Int, Chats>()

    fun add(userId: Int, message: ChatMessage) { // создание чата и первого сообщения в нем
        chats.getOrPut(userId) { Chats() }.messages.add(message)
        message.id = ++idNext
    }

    fun delete(userId: Int): Boolean { // удаление чата
        chats.remove(userId)
        return true
    }

    fun getChatList(): Int { // получение списка чата с последним сообщением
        chats.values.map { it.messages.lastOrNull { message -> !message.deleted }?.text ?: "No messages" }
        return chats.size
    }


    fun getMessagesReadList (userId: Int, messageId: Int, messageCount: Int): List<ChatMessage> {
        return chats[userId]?.messages?.let { message ->
            val index = message.indexOfFirst { it.id == messageId }
            if (index + messageCount <= message.size) {
                message.slice(index until index + messageCount).onEach { it.read = true }
            } else {
                message.slice(index until message.size).onEach { it.read = true }
            }
        } ?: emptyList()
    }

    fun getUnreadChatsCount(): Int { // количество непрочитанных чатов
        var count: Int = 0
        chats.forEach { (key, value) ->
            value.messages.map {
                if (!it.read) {
                    count++
                    return@forEach
                }
            }
        }
        return count
    }

    fun addMessage(userId: Int, message: ChatMessage):Int { // добавление сообщений
        chats.forEach { (key, value) ->
            if (key == userId) {
                value.messages.add(message)
                message.id = ++idNext
                return message.id
            }
        }
        return -1
    }

    fun deleteMessage(id: Int):Int { // удаление сообщений
        chats.values.map { chat ->
            chat.messages.map { message ->
                if (message.id == id) {
                    if (!message.deleted) {
                        message.deleted = true
                        return message.id
                    } else {
                        throw MessageDeletedException("Message already deleted")
                    }
                }
            }
        }
        return -1
    }

    fun editMessage(id: Int, text: String):Any { // редактирование сообщений
        var count = 0
        chats.values.map { chat ->
            chat.messages.map { message ->
                if (message.id == id) {
                    if (!message.deleted) {
                        message.text = text
                        count++
                        return message.text
                    } else {
                        throw MessageDeletedException("Deleted message")
                    }
                }
            }
        }
        throw MessageIsNotExistException("Message do not exist")
    }

    fun clear() { // метод очистки
        chats = mutableMapOf<Int, Chats>()
        idNext = 0
    }
}



