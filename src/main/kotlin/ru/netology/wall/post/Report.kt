package ru.netology.wall.post

class Report (
    val ownerId: Int = 0, //идентификатор пользователя или сообщества, которому принадлежит комментарий
    val commentId: Int = 0, //идентификатор комментария
    val reason: Int, // причина жалобы (0...8)
)