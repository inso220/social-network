package ru.netology.notes

data class Notes(
    var id: Int = 0, // id заметки
    var title: String = "title", // Заголовок заметки
    var text: String = "text", // Текст заметки
    var noteComments: MutableList<NoteComment>, // для хранения комментариев к заметке
    var deleteComments: MutableMap<Int, NoteComment> // для хранения удаленных комментариев
)