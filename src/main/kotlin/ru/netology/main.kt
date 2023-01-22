package ru.netology

data class Post(
    var id: Int = 0, //Идентификатор записи.
    var ownerId: Int = 0, //Идентификатор владельца стены, на которой размещена запись.
    var fromId: Int = 0, //Идентификатор автора записи
    var date: Int = 0, //Время публикации записи
    var text: String = "content", //Текст записи
    var replyOwnerId: Int = 0, //Идентификатор владельца записи, в ответ на которую была оставлена текущая.
    var replyPostId: Int = 0, //Идентификатор записи, в ответ на которую была оставлена текущая.
    var canPin: Boolean = false, //Информация о том, может ли текущий пользователь закрепить запись
    var canDelete: Boolean = false, // Информация о том, может ли текущий пользователь удалить запись
    var canEdit: Boolean = false, //Информация о том, может ли текущий пользователь редактировать запись
    var likes: Likes,
)

class Likes(
    var count: Int = 0, //число пользователей, которым понравилась запись
    var userLikes: Boolean = false, //наличие отметки «Мне нравится» от текущего пользователя
) {
    override fun toString(): String {
      return count.toString()
    }
}

object WallService {

    private var posts = emptyArray<Post>()
    private var idNext = 0

    fun add(post: Post): Post { //функция добавление нового поста
        posts += post
        post.id = ++idNext
        return posts.last()
    }

    fun update(post: Post): Boolean { //функция обновления поста
        for ((index, postUpdate) in posts.withIndex()) {
            if (post.id == postUpdate.id) {
                posts.set(index, post.copy(id = post.id + 1, fromId = post.fromId, text = post.text + " ",
                    replyOwnerId = post.replyOwnerId + 1, replyPostId = post.replyPostId + 1, canPin = !post.canPin,
                    canDelete = !post.canDelete, canEdit = !post.canEdit, likes = Likes(2, false)))
                return true
            }
        }
        return false
    }

    fun clear() { // метод очистки
        posts = emptyArray()
    }
}

fun main() {

}


