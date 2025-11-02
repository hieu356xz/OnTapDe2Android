package com.example.ontapde2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionDao {
    @Insert
    suspend fun insert(question: Question)

    @Update
    suspend fun update(question: Question)

    @Delete
    suspend fun delete(question: Question)

    @Query("select * from Question")
    suspend fun selectAll(): List<Question>
}