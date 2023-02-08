package ru.netology

import org.junit.Before
import org.junit.Test
import ru.netology.wall.post.exceptions.CommentNotFoundException
import ru.netology.wall.post.exceptions.PostNotFoundException
import ru.netology.wall.post.exceptions.ReasonNotFound
import ru.netology.wall.post.attachments.Attachments
import ru.netology.wall.post.attachments.Audio
import ru.netology.wall.post.attachments.AudioAttachment
import ru.netology.wall.WallService
import ru.netology.wall.post.*
import kotlin.test.assertEquals

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)

        WallService.add(post)
        val result = post.id
        assertEquals(1, result)
    }



    @Test
    fun updateWithoutAdd() {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)

        val result = WallService.update(post)

        assertEquals(false, result)
    }

    @Test
    fun updateWithAdd() {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)
        WallService.add(post)

        val result = WallService.update(post)

        assertEquals(true, result)
    }

    @Test
    fun createCommentCorrect() {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)
        WallService.add(post)

        var newComment = Comment()
        val result = WallService.createComment(1, newComment)

        assertEquals (newComment, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrowPost() {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)
        WallService.add(post)

        var newComment = Comment()
        WallService.createComment(111, newComment)
    }


    @Test
    fun createReportToCommentCorrect () {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)
        WallService.add(post)

        var newComment = Comment()
        WallService.createComment(1, newComment)

        var newReport = Report(reason = 0)
        WallService.createReportToComment(1, newReport)
    }

    @Test (expected = CommentNotFoundException::class)
    fun shouldThrowComment () {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)
        WallService.add(post)

        var newComment = Comment()
        WallService.createComment(1, newComment)

        var newReport = Report(reason = 0)
        val result = WallService.createReportToComment(111, newReport)

        assertEquals(newReport, result)
    }


    @Test (expected = ReasonNotFound::class)
    fun shouldThrowReason() {
        val likesCount = Likes()
        val isDonut = Donut()
        val viewsCount = Views()
        val audioAttachment1 = AudioAttachment()
        val audio1 = Audio(audioAttachment = audioAttachment1)
        var array = arrayOf<Attachments>(audio1)
        val post = Post(likes = likesCount, donut = isDonut, views = viewsCount, attachments = array)
        WallService.add(post)

        var newComment = Comment()
        WallService.createComment(1, newComment)

        var newReport = Report(reason = 10)
        WallService.createReportToComment(1, newReport)
    }
}