package ru.netology.chats

data class Chats (
    var userMessages: MutableList<ChatMessage> = mutableListOf(),
    var myMessages: MutableList<ChatMessage> = mutableListOf()
)