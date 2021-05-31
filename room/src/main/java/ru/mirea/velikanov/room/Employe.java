package ru.mirea.velikanov.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Employe {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int salary;
}