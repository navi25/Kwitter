package io.navendra.kwitter.data

import android.content.Context
import android.net.Uri
import io.navendra.kwitter.data.provider.WordContract


class WordRepository(private val context: Context) {

    private val queryUri = WordContract.CONTENT_URI.toString() // base uri
    private val projection = arrayOf(WordContract.CONTENT_PATH) //table
    private val selectionClause: String? = null
    private val selectionArgs: Array<String>? = null
    private val sortOrder = "ASC"


    fun getAllWords() : List<WordItem>{
        val cursor = context.contentResolver.query(Uri.parse(queryUri),projection,selectionClause,
            selectionArgs,sortOrder)
        if(cursor==null){
            return emptyList()
        }

        cursor.moveToFirst()
        var entry : WordItem? = null
        val list = mutableListOf<WordItem>()

        do{
            val id = cursor.getInt(cursor.getColumnIndex(WordContract.WordList.KEY_ID))
            val word = cursor.getString(cursor.getColumnIndex(WordContract.WordList.KEY_WORD))
            entry = WordItem(id,word)
            list.add(entry)

        } while(cursor.moveToNext())

        cursor.close()

        return list
    }

    fun delete(){}

//    fun getAllWords() = db.getAllWords()
}