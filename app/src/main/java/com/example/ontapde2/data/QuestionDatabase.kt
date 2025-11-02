package com.example.ontapde2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionDatabase? = null

        fun getInstance(context: Context): QuestionDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionDatabase::class.java,
                    "question.db",
                ).build()
                INSTANCE = db
                db
            }
        }
    }
}