package com.example.bustransportation;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bustransportation.Model.NotesModel;
import com.example.bustransportation.Room.MyRoomDBClient;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding to database


        //This is RXJava code. This following code will take care of doing the task in background thread.
        //As per document, Room DB operations like Read, Update, delete shouldn't do in mail thread.
        //So here we are using RXJava to do it in background thread.
        /*RXJava Start*/
        Completable.fromAction(() -> {
            //adding to database
            MyRoomDBClient.getInstance(getApplicationContext()).getAppDatabase()
                    .notesDAO().InsertAll(new NotesModel(R.drawable.ic_launcher_background,
                            "Title",
                            "Description", "HTTP",
                            "Type"));
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "DB insert fail", Toast.LENGTH_SHORT).show();
            }
        });
        /*RXJava End*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //We need to destroy the instance of Room DB.
        //So it must to destroy in life cycle of Android.
        MyRoomDBClient.destroyInstance();
    }
}
