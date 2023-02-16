package ru.netology.chats

data class Chats (
    var messages: MutableList<ChatMessage> = mutableListOf(),
)