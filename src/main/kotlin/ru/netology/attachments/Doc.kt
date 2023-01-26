package ru.netology.attachments

class Doc (
    override val type: String = "doc",
    var docAttachments: DocAttachments,
): Attachments

class DocAttachments(
    var id: Int,
    var ownerId: Int,
    var title: String,
    var size: Int,
    var ext: String,
)