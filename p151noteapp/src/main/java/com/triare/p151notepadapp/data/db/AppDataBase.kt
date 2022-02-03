package com.triare.p151notepadapp.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.triare.p151notepadapp.NotepadApp
import com.triare.p151notepadapp.data.db.dao.ContentDao
import com.triare.p151notepadapp.data.db.dao.NoteDao
import com.triare.p151notepadapp.data.db.model.ContentDbo
import com.triare.p151notepadapp.data.db.model.NoteDbo

@Database(entities = [ContentDbo::class, NoteDbo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contentDao(): ContentDao
    abstract fun noteDao(): NoteDao

    companion object {

        private var instance: AppDataBase? = null

        fun getInstance(): AppDataBase {
            if (instance == null) {
                synchronized(AppDataBase::class.java) {
                    instance = Room.databaseBuilder(
                        NotepadApp.context,
                        AppDataBase::class.java,
                        "notepadApp"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }
    }
}