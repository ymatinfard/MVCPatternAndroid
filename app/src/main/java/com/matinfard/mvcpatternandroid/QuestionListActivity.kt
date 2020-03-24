package com.matinfard.mvcpatternandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionListActivity : AppCompatActivity(), QuestionListAdapter.onQuestionClickListener {

    lateinit var mStackoOverflowApi: StackOverflowApi
    lateinit var mListView: ListView
    lateinit var mQuestionListAdapter: QuestionListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mQuestionListAdapter = QuestionListAdapter(applicationContext, this)
        mStackoOverflowApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StackOverflowApi::class.java)

        recycler_view.apply {
            adapter = mQuestionListAdapter
            layoutManager = LinearLayoutManager(this@QuestionListActivity)
        }
    }

    override fun onStart() {
        super.onStart()
        fetchQuestions()
    }

    private fun fetchQuestions() {
        mStackoOverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            .enqueue(object : Callback<QuestionsListResponseSchema?>{
                override fun onFailure(call: Call<QuestionsListResponseSchema?>, t: Throwable) {
                    networkCallFailure()
                }

                override fun onResponse(
                    call: Call<QuestionsListResponseSchema?>,
                    response: Response<QuestionsListResponseSchema?>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let { responseBody ->
                            bindQuestions(responseBody.questions)
                        }

                    }

                }
            })
    }

    private fun networkCallFailure() {
        Toast.makeText(this, "Network failed", Toast.LENGTH_SHORT).show()
    }

    private fun bindQuestions(questions: List<QuestionSchema>) {
        val filteredQuestions = questions.map { Question(it.mId, it.mTitle) }
        mQuestionListAdapter.submit(filteredQuestions)
    }

    override fun onQuestionClicked(question: Question) {

    }

}
