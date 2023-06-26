package com.controledohistricodedoenasemumalocalidade.persiste;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.controledohistricodedoenasemumalocalidade.model.ControleDoencas;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ControleDoencasDao {

    @Insert
    long insert(ControleDoencas controleDoencas);

    @Delete
    void delete(ControleDoencas controleDoencas);

    @Update
    void update(ControleDoencas controleDoencas);

    //    Sqlite não difere maiusculas de minusculas para nome da classe
    @Query("SELECT * FROM controleDoencas WHERE id = :id")
    ControleDoencas queryForId(long id);

    //    Sqlite não difere maiusculas de minusculas para nome da classe
    @Query("SELECT * FROM controleDoencas ORDER BY doenca ASC")
    List<ControleDoencas> queryAll();
}
