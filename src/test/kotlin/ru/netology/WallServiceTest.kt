package ru.netology

import org.junit.Before
import org.junit.Test
import ru.netology.attachments.Attachments
import ru.netology.attachments.Audio
import ru.netology.attachments.AudioAttachment
import ru.netology.post.Donut
import ru.netology.post.Likes
import ru.netology.post.Post
import ru.netology.post.Views
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
}