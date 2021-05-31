package ru.mirea.velikanov.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Employe.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {
    public abstract EmplouyeeDAO employeeDao();
}