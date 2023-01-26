package ru.netology.attachments

class Audio(
    override val type: String = "audio",
    var audioAttachment: AudioAttachment,
) : Attachments

class AudioAttachment (
    var id: Int = 0,
    var ownerId: Int = 0,
    var artist: String = "artist",
    var title: String = "name",
    var duration: Int = 0
)