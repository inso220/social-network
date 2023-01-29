package ru.netology.post.attachments

class Graffiti (
    override val type: String = "graffiti",
    var graffitiAttachments: GraffitiAttachments,
): Attachments

class GraffitiAttachments (
    var id: Int,
    var ownerId: Int,
    var photo130: String,
    var photo604: String,
)