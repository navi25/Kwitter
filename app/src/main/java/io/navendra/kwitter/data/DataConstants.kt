package io.navendra.kwitter.data

import android.net.Uri
import io.navendra.kwitter.BuildConfig

object DataConstants {

    //region LocalDB Constants
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "WordDB"
    const val WORD_LIST_TABLE = "word_list_table"

    const val KEY_ID = "_id"
    const val KEY_WORD = "word"
    const val KEY_MEANING = "meaning"

    val COLUMNS = arrayListOf(
        KEY_ID,
        KEY_WORD
    )

    object Query{
        const val CREATE_WORD_TABLE = "CREATE TABLE $WORD_LIST_TABLE ($KEY_ID INTEGER PRIMARY KEY, " +
                "$KEY_WORD TEXT );"

        const val DROP_WORD_TABLE = "DROP TABLE IF EXISTS $WORD_LIST_TABLE"
    }
    //endregion

    //region ContentProvider Constants

    private const val AUTHORITY = "${BuildConfig.APPLICATION_ID}.wordcontentprovider.provider"
    private const val CONTENT_PATH = "words"
    val CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
    const val ALL_ITEMS = -2
    const val WORD_ID = "id"

    const val SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.provider.words"
    //endregion

}