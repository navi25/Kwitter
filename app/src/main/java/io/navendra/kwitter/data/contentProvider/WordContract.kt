package io.navendra.kwitter.data.contentProvider

import android.net.Uri
import android.provider.BaseColumns
import io.navendra.kwitter.BuildConfig
import io.navendra.kwitter.data.DataConstants

object WordContract {

    val DATABASE_NAME = "WordDB"
    val AUTHORITY = "${BuildConfig.APPLICATION_ID}.wordcontentprovider.provider"
    val CONTENT_PATH = "words"
    val CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
    val ALL_ITEMS = -2
    val WORD_ID = "id"

    val SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.io.navendra.provider.words"
    val MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.item/vnd.io.navendra.provider.words"

     object WordList : BaseColumns{
        val WORD_LIST_TABLE = "word_list_table"

        val KEY_ID = "_id"
        val KEY_WORD = "word"
    }

}

