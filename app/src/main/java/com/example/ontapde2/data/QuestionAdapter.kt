package com.example.ontapde2.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ontapde2.R

class QuestionAdapter: ListAdapter<Question, QuestionAdapter.QuestionHolder>(QuestionDiffCallback()) {

    private val selectedAnswers = mutableMapOf<Int, Int>()

    fun getSelectedAnswers(): Map<Int, Int> {
        return selectedAnswers
    }

    class QuestionHolder(view: View): RecyclerView.ViewHolder(view) {
        val questionText = view.findViewById<TextView>(R.id.tv_question)
        val optionGroup = view.findViewById<RadioGroup>(R.id.rg_answers)

        val answerA = view.findViewById<RadioButton>(R.id.rb_answer_1)
        val answerB = view.findViewById<RadioButton>(R.id.rb_answer_2)
        val answerC = view.findViewById<RadioButton>(R.id.rb_answer_3)
        val answerD = view.findViewById<RadioButton>(R.id.rb_answer_4)

        fun bind(question: Question, onAnswerSelected: (Int) -> Unit) {
            questionText.text = question.question
            answerA.text = question.answer_a
            answerB.text = question.answer_b
            answerC.text = question.answer_c
            answerD.text = question.answer_d

            optionGroup.setOnCheckedChangeListener(null)
            optionGroup.setOnCheckedChangeListener {
                _, checkedId ->
                val selected = when(checkedId) {
                    answerA.id -> 1
                    answerB.id -> 2
                    answerC.id -> 3
                    answerD.id -> 4
                    else -> 0
                }

                onAnswerSelected(selected)
            }
        }
    }

    class QuestionDiffCallback: DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item) { selectedAnswer ->
            selectedAnswers[position] = selectedAnswer
        }
    }
}