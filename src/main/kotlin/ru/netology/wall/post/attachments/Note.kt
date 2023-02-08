package ru.netology.wall.post.attachments

class Note (
    override val type: String = "note",
    var note: NoteAttachments,
): Attachments

class NoteAttachments (
    var id: Int,
    var ownerId: Int,
    var title: String,
    var text: String,
    var date: Int,
)