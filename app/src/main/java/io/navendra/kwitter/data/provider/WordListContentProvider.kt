package io.navendra.kwitter.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import io.navendra.kwitter.data.local.WordDbOpenHelper
import io.navendra.kwitter.data.provider.WordContract.CONTENT_URI
import io.navendra.kwitter.data.provider.WordContract.MULTIPLE_RECORDS_MIME_TYPE
import io.navendra.kwitter.data.provider.WordContract.SINGLE_RECORD_MIME_TYPE

class WordListContentProvider : ContentProvider(){

    companion object{
        val TAG = WordListContentProvider::class.java.simpleName
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    private var db : WordDbOpenHelper? = null

    private val URI_ALL_ITEMS_CODE = 10
    private val URI_ONE_ITEM_CODE = 20
    private val URI_COUNT_CODE = 30

    override fun onCreate(): Boolean {
        db = WordDbOpenHelper(context!!)
        initialiseUriMatching()
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri) : String? =  when(uriMatcher.match(uri)) {
        URI_ALL_ITEMS_CODE -> MULTIPLE_RECORDS_MIME_TYPE
        URI_ONE_ITEM_CODE -> SINGLE_RECORD_MIME_TYPE
        else -> null
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if(values == null || db == null) return null
        val id : Long = db?.insert(values)!!
        return Uri.parse("$CONTENT_URI/$id")
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        var cursor : Cursor? = null

        when(uriMatcher.match(uri)){
            URI_ALL_ITEMS_CODE ->{ cursor = db?.query(WordContract.ALL_ITEMS) }
            URI_ONE_ITEM_CODE -> {}
            URI_COUNT_CODE -> {}
            UriMatcher.NO_MATCH ->{}
            else -> {}
        }

        return cursor
    }



    private fun initialiseUriMatching(){
        uriMatcher.addURI(WordContract.AUTHORITY,WordContract.CONTENT_PATH,URI_ALL_ITEMS_CODE)
        uriMatcher.addURI(WordContract.AUTHORITY,"${WordContract.CONTENT_PATH}/#",URI_ONE_ITEM_CODE)
        uriMatcher.addURI(WordContract.AUTHORITY,"${WordContract.CONTENT_PATH}/${WordContract.COUNT}",
            URI_COUNT_CODE)
    }

}