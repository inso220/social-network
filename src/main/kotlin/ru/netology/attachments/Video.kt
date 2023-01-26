package ru.netology.attachments

class Video (
    override val type: String = "video",
    var videoAttachments: VideoAttachments,
) : Attachments

class VideoAttachments (
    var id: Int,
    var ownerId: Int,
    var title: String,
    var description: String,
    var duration: Int,
)