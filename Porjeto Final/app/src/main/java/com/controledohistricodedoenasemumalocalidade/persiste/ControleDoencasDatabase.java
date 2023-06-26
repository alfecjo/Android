package com.controledohistricodedoenasemumalocalidade.persiste;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.controledohistricodedoenasemumalocalidade.model.ControleDoencas;

@Database(entities = {ControleDoencas.class}, version = 1, exportSchema = false)
public abstract class ControleDoencasDatabase extends RoomDatabase {

    public abstract ControleDoencasDao controleDoencasDao();

    private static ControleDoencasDatabase instance;

    public static ControleDoencasDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (ControleDoencasDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, ControleDoencasDatabase.class,
                            "controledoencas.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
