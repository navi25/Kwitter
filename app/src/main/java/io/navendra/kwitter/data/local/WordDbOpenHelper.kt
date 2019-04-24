package io.navendra.kwitter.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import io.navendra.kwitter.KwitterLog
import io.navendra.kwitter.data.WordItem

import io.navendra.kwitter.data.DataConstants.DATABASE_VERSION
import io.navendra.kwitter.data.DataConstants.Query.CREATE_WORD_TABLE
import io.navendra.kwitter.data.DataConstants.Query.DROP_WORD_TABLE

import io.navendra.kwitter.data.contentProvider.WordContract.DATABASE_NAME
import io.navendra.kwitter.data.contentProvider.WordContract.WordList.KEY_ID
import io.navendra.kwitter.data.contentProvider.WordContract.WordList.KEY_WORD
import io.navendra.kwitter.data.contentProvider.WordContract.WordList.WORD_LIST_TABLE


class WordDbOpenHelper @JvmOverloads constructor(
    context: Context, dbName : String = DATABASE_NAME,
    cursorFactory: SQLiteDatabase.CursorFactory? = null,
    dbVersion: Int = DATABASE_VERSION) : SQLiteOpenHelper(context,dbName,cursorFactory,dbVersion){

    private val TAG = WordDbOpenHelper::class.java.simpleName



    init {
        KwitterLog.d { "$TAG - Constructed WordDBOpenHelper" }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_WORD_TABLE)
        fillDatabaseWithData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        KwitterLog.d {
            "$TAG - Upgrading $WORD_LIST_TABLE from $oldVersion to $newVersion. Destroying all data"
        }
        db?.execSQL(DROP_WORD_TABLE)
        onCreate(db)
    }

    fun query(position : Int) : WordItem?{
        val query = "SELECT  * FROM  $WORD_LIST_TABLE ORDER BY  $KEY_WORD  ASC LIMIT $position, $1"
        var cursor : Cursor? = null
        var entry : WordItem? = null
        try {
            cursor = readableDatabase?.rawQuery(query,null)
            cursor?.moveToFirst()
            val id = cursor?.getInt(cursor.getColumnIndex(KEY_ID))!!
            val word = cursor.getString(cursor.getColumnIndex(KEY_WORD))
            entry = WordItem(id,word)

        }catch (e : Exception){

            KwitterLog.d { "$TAG - EXCEPTION in Reading at $position!! - ${e.message}" }

        }finally {
            cursor?.close()
            return entry
        }
    }

    fun getAllWords() : List<WordItem>?{
        val query = "SELECT  * FROM  $WORD_LIST_TABLE ORDER BY  $KEY_WORD ASC"
        var cursor : Cursor? = null
        var entry : WordItem? = null
        val list = mutableListOf<WordItem>()
        try {
            cursor = readableDatabase?.rawQuery(query,null)
            if(cursor == null){
                return null
            }

            cursor.moveToFirst()

            do{
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))!!
                val word = cursor.getString(cursor.getColumnIndex(KEY_WORD))
                entry = WordItem(id,word)
                list.add(entry)

            } while(cursor.moveToNext())


        }catch (e : Exception){

            KwitterLog.d { "$TAG - EXCEPTION in Reading all words - ${e.message}" }

        }finally {
            cursor?.close()
            return list
        }
    }

    fun insert(item: WordItem) : Long{
        var id : Long = 0
        val values = ContentValues()
        values.put(KEY_WORD,item.word)

        try {
            id = writableDatabase.insert(WORD_LIST_TABLE,null,values)
        }catch (e: Exception){
            KwitterLog.d { "$TAG - EXCEPTION in Inserting - ${e.message}"  }
        }

        return id
    }


    private fun fillDatabaseWithData(db: SQLiteDatabase?){

        val words = arrayListOf("Android", "Adapter", "ListView", "AsyncTask",
            "Android Studio", "SQLiteDatabase", "SQLOpenHelper",
            "Data model", "ViewHolder","Android Performance",
            "OnClickListener")

        val values = ContentValues()

        words.forEach {
            values.put(KEY_WORD,it)
            db?.insert(WORD_LIST_TABLE,null,values)
        }
    }

}