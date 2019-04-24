package io.navendra.kwitter.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.navendra.kwitter.KwitterLog
import io.navendra.kwitter.R
import io.navendra.kwitter.ui.adapter.WordListAdapter
import io.navendra.kwitter.viewModels.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel
    private val wordAdapter = WordListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.words.observe(this, Observer {
            if(it == null) return@Observer
            it.forEach { KwitterLog.d { "Wordss -  $it" } }
            wordAdapter.setData(it)
        })
        initRecycler()
    }

    override fun onStart() {
        super.onStart()
        wordViewModel.loadData()
    }

    private fun initRecycler(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = wordAdapter
        }
    }
}
