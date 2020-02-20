package com.example.bustransportation.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bustransportation.Model.NotesModel;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM NoteTable")
    Maybe<List<NotesModel>> GetAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //Basically insert query will not return string.
        //It'll return only following types "void, long or list long"
    void InsertAll(NotesModel notesModel);
}
