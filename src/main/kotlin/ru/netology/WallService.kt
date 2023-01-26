package ru.netology

import ru.netology.post.Likes
import ru.netology.post.Post

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
                    canDelete = !post.canDelete, canEdit = !post.canEdit, likes = Likes(2, false)
                ))
                return true
            }
        }
        return false
    }

    fun clear() { // метод очистки
        posts = emptyArray()
        idNext = 0
    }
}