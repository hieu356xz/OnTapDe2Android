package com.example.ontapde2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val question: String,
    val answer_a: String,
    val answer_b: String,
    val answer_c: String,
    val answer_d: String,
    val correct_answer: Int
)
