package ru.netology.notes

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.notes.exceptions.CommentAlreadyDeletedException
import ru.netology.notes.exceptions.CommentIsNotDeleted
import ru.netology.wall.WallService
import ru.netology.wall.post.exceptions.PostNotFoundException

class NotesServiceTest {

    @Before
    fun clearBeforeTest() {
        NotesService.clear()
    }

    @Test
    fun add() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)

        val result = NotesService.add(note)
        assertEquals(result, 1)
    }

    @Test
    fun createCommentSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)

        val result = NotesService.createComment(1, comment)
        assertEquals(result, 1)
    }

    @Test
    fun createCommentUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)

        val result = NotesService.createComment(100, comment)
        assertEquals(result, -1)
    }

    @Test
    fun deleteSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        NotesService.add(note)

        val result = NotesService.delete(note.id)
        assertEquals(result, true)
    }

    @Test
    fun deleteUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)

        val result = NotesService.delete(note.id)
        assertEquals(result, false)
    }

    @Test
    fun deleteCommentSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)

        val result = NotesService.deleteComment(comment.id)
        assertEquals(result, true)
    }

    @Test
    fun deleteCommentUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)

        val result = NotesService.deleteComment(5)
        assertEquals(result, false)
    }

    @Test(expected = CommentAlreadyDeletedException::class)
    fun shouldThrowDeleteComment() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)
        NotesService.deleteComment(1)
        NotesService.deleteComment(1)
    }

    @Test
    fun editSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        NotesService.add(note)

        val result = NotesService.editNote(note, "new text", "new title")
        assertEquals(result, true)
    }

    @Test
    fun editUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)

        val result = NotesService.editNote(note, "new text", "new title")
        assertEquals(result, false)
    }

    @Test
    fun editCommentSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)

        val result = NotesService.editComment(comment, "new message")
        assertEquals(result, true)
    }

    @Test
    fun editCommentUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)

        val result = NotesService.editComment(comment, "new message")
        assertEquals(result, false)
    }

    @Test(expected = CommentAlreadyDeletedException::class)
    fun shouldThrowEditComment() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)
        NotesService.deleteComment(1)
        NotesService.editComment(comment, "new message")
    }

    @Test
    fun noteGetByIdSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        NotesService.add(note)

        val result = NotesService.noteGetById(1)
        assertEquals(result, note)
    }

    @Test
    fun noteGetByIdUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        NotesService.add(note)

        val result = NotesService.noteGetById(5)
        assertEquals(result, null)
    }

    @Test
    fun getCommentsOfNoteSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)

        val result = NotesService.getCommentsOfNote(1)
        assertEquals(result, note.noteComments)
    }

    @Test
    fun getCommentsOfNoteUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)

        val result = NotesService.getCommentsOfNote(100)
        assertEquals(result, null)
    }

    @Test
    fun restoreCommentSuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)
        NotesService.deleteComment(comment.id)

        val result = NotesService.restoreComment(comment.id)
        assertEquals(result, true)
    }

    @Test
    fun restoreCommentUnsuccessful() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)
        NotesService.deleteComment(comment.id)

        val result = NotesService.restoreComment(5)
        assertEquals(result, false)
    }

    @Test(expected = CommentIsNotDeleted::class)
    fun shouldThrowRestoreComment() {
        val listOfNoteComments = mutableListOf<NoteComment>()
        val listOfDeleteNoteComments = mutableMapOf<Int, NoteComment>()
        val note = Notes(noteComments = listOfNoteComments, deleteComments = listOfDeleteNoteComments)
        val comment = NoteComment ()
        NotesService.add(note)
        NotesService.createComment(1, comment)

        NotesService.restoreComment(comment.id)
    }
}