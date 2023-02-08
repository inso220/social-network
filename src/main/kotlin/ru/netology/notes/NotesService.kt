package ru.netology.notes

import ru.netology.notes.exceptions.CommentAlreadyDeletedException
import ru.netology.notes.exceptions.CommentIsNotDeleted


object NotesService {
    private var noteIdNext = 0
    private var notes = mutableListOf<Notes>()
    private var commentIdNext = 0

    fun add(note: Notes): Int { // Создает новую заметку у текущего пользователя.
        notes.add(note)
        note.id = ++noteIdNext
        return notes.last().id
    }

    fun createComment (noteId: Int, noteComment: NoteComment): Int { // Добавляет новый комментарий к заметке
        for(index in notes) {
            if (index.id == noteId) {
                index.noteComments += noteComment
                noteComment.id = ++commentIdNext
                return index.noteComments.last().id
            }
        }
        return -1
    }

    fun delete(noteId: Int):Boolean { // Удаляет заметку
        for (index in notes) {
            if (index.id == noteId) {
                notes.remove(index)
                return true
            }
        }
        return false
    }

    fun deleteComment(commentId: Int): Boolean { // Удаляет комментарий
        for (i in notes) {
            for (index in i.noteComments) {
                if (index.id == commentId) {
                    i.deleteComments[index.id] = i.noteComments[i.id - 1]
                    i.noteComments.remove(index)
                    return true
                }
            }
            for (index in i.deleteComments) {
                if (index.key == commentId) {
                    throw CommentAlreadyDeletedException ("Comment has already deleted") // если пользователь пытается удалить уже удаленный комментарий
                }
            }
        }
        return false
    }

    fun editNote (note: Notes, newText: String, newTitle: String): Boolean { // Редактирует заметку
        for (index in notes) {
            if (index.id == note.id) {
                notes[index.id - 1] = note.copy(text = newText, title = newTitle)
                return true
            }
        }
        return false
    }

    fun editComment (noteComment: NoteComment, newMessage: String): Boolean { // Редактирует указанный комментарий
        for (i in notes) {
            for(index in i.noteComments) {
                if (index.id == noteComment.id) {
                    i.noteComments[index.id - 1] = noteComment.copy(message = newMessage)
                    return true
                }
            }
            for (index in i.deleteComments) {
                if (index.key == noteComment.id) {
                    throw CommentAlreadyDeletedException ("Comment is deleted") // если пользователь пытается редактировать удаленный комментарий
                }
            }
        }
        return false
    }

    fun noteGetById (noteId: Int): Notes? {
        for (index in notes) {
            if (index.id == noteId) {
                return notes[index.id - 1]
            }
        }
        return null
    }

    fun getCommentsOfNote (noteId: Int): List<NoteComment>? {  // Возвращает список комментариев к заметке
        for (index in notes) {
            if (index.id == noteId) {
                return index.noteComments
            }
        }
        return null
    }

    fun restoreComment (commentId: Int): Boolean { // Восстанавливает удалённый комментарий
        for(i in notes) {
            for (index in i.deleteComments) {
                if (commentId == index.key) {
                    i.noteComments += i.deleteComments.getValue(commentId)
                    i.deleteComments.remove(commentId)
                    return true
                }
            }
            for (index in i.noteComments) {
                if (index.id == commentId) {
                    throw CommentIsNotDeleted ("Comment is not deleted") // если пользователь пытается восстановить неудаленный комментарий
                }
            }
        }
        return false
    }

    fun clear() { // метод очистки
        notes = mutableListOf<Notes>()
        noteIdNext = 0
        commentIdNext = 0
    }

}