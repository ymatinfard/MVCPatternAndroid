package com.matinfard.mvcpatternandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.question_list_item.view.*


class QuestionListAdapter(val context: Context, onQuestionClickListener: onQuestionClickListener): RecyclerView.Adapter<QuestionListAdapter.ViewHolder>(){

    interface onQuestionClickListener{
        fun onQuestionClicked(question: Question)
    }

    var mOnQuestionListener = onQuestionClickListener
    var mQuestions = emptyList<Question>()
    class ViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {
        val textTitle = viewItem.txt_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mQuestions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textTitle.text = mQuestions[position].mTitle
    }

    fun submit(questions: List<Question>){
        mQuestions = questions
        notifyDataSetChanged()
    }


}