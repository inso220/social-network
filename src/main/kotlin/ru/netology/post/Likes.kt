package ru.netology.post

class Likes(
    var count: Int = 0, //число пользователей, которым понравилась запись
    var userLikes: Boolean = false, //наличие отметки «Мне нравится» от текущего пользователя
) {
    override fun toString(): String {
        return count.toString()
    }
}