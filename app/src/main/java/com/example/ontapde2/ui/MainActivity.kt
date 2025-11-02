package com.example.ontapde2.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ontapde2.data.Question
import com.example.ontapde2.data.QuestionAdapter
import com.example.ontapde2.data.QuestionDatabase
import com.example.ontapde2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: QuestionDatabase
    lateinit var adapter: QuestionAdapter
    lateinit var questionList: MutableList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = QuestionDatabase.getInstance(this)
        adapter = QuestionAdapter()
        binding.rvQuestions.layoutManager = LinearLayoutManager(this)
        binding.rvQuestions.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
//            db.questionDao().insert(
//                Question(
//                    question = "Trong kotlin, từ khóa nào được sử dụng để khai báo một biến không thể thay đổi?",
//                    answer_a = "val",
//                    answer_b = "vla",
//                    answer_c = "lva",
//                    answer_d = "alv",
//                    correct_answer = 1
//                )
//            )
//
//            db.questionDao().insert(
//                Question(
//                    question = "Trong kotlin, từ khóa nào được sử dụng để khai báo một biến không thể thay đổi?",
//                    answer_a = "val",
//                    answer_b = "vla",
//                    answer_c = "lva",
//                    answer_d = "alv",
//                    correct_answer = 1
//                )
//            )


            val list = db.questionDao().selectAll()
            withContext(Dispatchers.Main) {
                adapter.submitList(list)
                questionList = list.toMutableList()
            }
        }

        binding.btSubmit.setOnClickListener {
            val answers = adapter.getSelectedAnswers()
            val resultsMap = mutableMapOf<Int, Boolean>()

            answers.forEach {
                idx, answer ->
                resultsMap[idx] = answers[idx] == questionList[idx].correct_answer
            }

            val i = Intent(this@MainActivity, ResultActivity::class.java)

            i.putExtra("ANSWERS", HashMap(resultsMap))
            startActivity(i)
        }
    }
}