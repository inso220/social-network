package ru.netology.wall

import ru.netology.wall.post.Comment
import ru.netology.wall.post.Likes
import ru.netology.wall.post.Post
import ru.netology.wall.post.Report
import ru.netology.wall.post.exceptions.CommentNotFoundException
import ru.netology.wall.post.exceptions.PostNotFoundException
import ru.netology.wall.post.exceptions.ReasonNotFound

object WallService {

    private var posts = emptyArray<Post>()
    private var postIdNext = 0
    private var comments = emptyArray<Comment>()
    private var commentIdNext = 0
    private var reports = emptyArray<Report>()

    fun add(post: Post): Post { //функция добавление нового поста
        posts += post
        post.id = ++postIdNext
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

    fun createComment(postId: Int, comment: Comment): Comment { // функция создания комментария
        for ((index, postsId) in posts.withIndex()) {
            if (postId == postsId.id) {
                comments += comment
                comment.id = ++commentIdNext
                return comments.last()
            }
        }
        throw PostNotFoundException ("Such a post doesn't exist")
    }

    fun createReportToComment (commentId: Int, report: Report): Report { // функция для уведомления о негативных комментариях
        for ((index, commentsId) in comments.withIndex()) {
            if(commentId == commentsId.id) {
                if(report.reason > 8 || report.reason < 0) {
                    throw ReasonNotFound("Wrong reason")
                }
                reports += report
                return reports.last()
            }
        }
        throw CommentNotFoundException("Wrong comment id")


    }


    fun clear() { // метод очистки
        posts = emptyArray()
        postIdNext = 0
        commentIdNext = 0
    }
}