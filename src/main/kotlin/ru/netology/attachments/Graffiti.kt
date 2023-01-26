package ru.netology.attachments

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