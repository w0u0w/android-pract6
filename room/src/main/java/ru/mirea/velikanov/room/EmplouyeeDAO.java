package ru.mirea.velikanov.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmplouyeeDAO {
    @Query("SELECT * FROM employe")
    List<Employe> getAll();
    @Query("SELECT * FROM employe WHERE id = :id")
    Employe getById(long id);
    @Insert
    void insert(Employe employee);
    @Update
    void update(Employe employee);
    @Delete
    void delete(Employe employee);
}