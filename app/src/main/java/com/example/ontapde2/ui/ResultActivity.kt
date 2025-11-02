package com.example.ontapde2.ui

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ontapde2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val answers = intent.getSerializableExtra("ANSWERS") as? HashMap<Int, Boolean>

        if (answers != null) {
            var resultText = ""

            answers.forEach {
                idx, answer ->
                resultText += generateResultText(idx + 1, answer)
            }

            binding.tvResult.text = Html.fromHtml(resultText, Html.FROM_HTML_MODE_LEGACY)

            binding.btExit.setOnClickListener {
                finish()
            }
        }
    }

    private fun generateResultText(questionNumber: Int, isCorrect: Boolean): String {
        val correctText = "<font color=${Color.GREEN}>Đúng</font>"
        val incorrectText = "<font color=${Color.RED}>Sai</font>"

        return "Câu ${questionNumber}: ${if (isCorrect) correctText else incorrectText} <br/>"
    }
}