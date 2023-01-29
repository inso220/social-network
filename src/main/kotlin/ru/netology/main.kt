package ru.netology

import ru.netology.post.*
import ru.netology.post.attachments.Attachments
import ru.netology.post.attachments.Audio
import ru.netology.post.attachments.AudioAttachment


fun main() {
    val likesCount = Likes()
    val isDonut = Donut()
    val viewsCount = Views()
    val audioAttachment1 = AudioAttachment()
    val audio1 = Audio(audioAttachment = audioAttachment1)
    var array = arrayOf<Attachments>(audio1)
    val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)

    WallService.add(post)

    println(post)

    var newComment = Comment()

    println(WallService.createComment(1, newComment))

    var newReport = Report(reason = 0)
    println(WallService.createReportToComment(1, newReport))
}


