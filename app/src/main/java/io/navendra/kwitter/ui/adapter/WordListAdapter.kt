package io.navendra.kwitter.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.navendra.kwitter.KwitterLog
import io.navendra.kwitter.R
import io.navendra.kwitter.data.WordItem
import kotlinx.android.synthetic.main.word_item_layout.view.*

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.ViewHolder>(){

    private var words = listOf<WordItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.word_item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = words[pos]
        holder.word.text = item.word
    }

    fun setData(_words : List<WordItem>){
        this.words = _words
        words.forEach { KwitterLog.d{ "Retw - $it"} }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val word = itemView.textView
    }
}