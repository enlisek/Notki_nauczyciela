package com.example.notki_nauczyciela.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Student::class,Group::class,Mark::class,StudentGroup::class,Note::class],version = 1,exportSchema = false)
abstract class MyDatabase:RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun groupDao():GroupDao
    abstract fun markDao():MarkDao
    abstract fun studentGroupDao():StudentGroupDao
    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase?=null

        fun getDatabase(context: Context):MyDatabase{
            val tempInstance=INSTANCE

            if(tempInstance!=null)
                return tempInstance
            else
                synchronized(this)
                {
                    val instance= Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "my_database"
                    ).build()
                    INSTANCE=instance
                    return instance

                }


        }


    }
}