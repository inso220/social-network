package ru.netology.chats

data class ChatMessage (
    var id: Int = 0,
    var text: String = "text",
    var deleted: Boolean = false, // если true - сообщение удалено
    var read: Boolean = false // если true - сообщение прочитано
)