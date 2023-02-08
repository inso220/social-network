package ru.netology.wall.post.exceptions

import java.lang.RuntimeException

class CommentNotFoundException (message: String): RuntimeException(message)