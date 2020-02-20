package com.example.bustransportation.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bustransportation.Model.NotesModel;

@Database(entities = {NotesModel.class}, version = 1, exportSchema = false)

public abstract class MyRoomDB extends RoomDatabase {

    public abstract NotesDAO notesDAO();
}
