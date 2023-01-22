package ru.netology

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val likesCount = Likes()
        val post = Post(likes = likesCount)

        WallService.add(post)
        val result = post.id
        assertEquals(2, result)
    }

    @Test
    fun updateWithoutAdd() {
        val likesCount = Likes()
        val post = Post(likes = likesCount)

        val result = WallService.update(post)

        assertEquals(false, result)
    }

    @Test
    fun updateWithAdd() {
        val likesCount = Likes()
        val post = Post(likes = likesCount)
        WallService.add(post)

        val result = WallService.update(post)

        assertEquals(true, result)
    }
}