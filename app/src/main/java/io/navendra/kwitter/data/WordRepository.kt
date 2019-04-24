package io.navendra.kwitter.data

import android.content.Context
import io.navendra.kwitter.data.local.WordDbOpenHelper

class WordRepository(context: Context) {

    private val db : WordDbOpenHelper by lazy { WordDbOpenHelper(context) }

    fun getAllWords() = db.getAllWords()
}