package com.example.bustransportation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bustransportation.Model.NotesModel;
import com.example.bustransportation.Room.MyRoomDBClient;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding to database

        String a= MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                .notesDAO().InsertAll(new NotesModel(R.drawable.ic_launcher_background,
                "Title",
                "Description", "HTTP",
                "Type"));
        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
    }
}
