package com.example.theenigma.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [QuestionListItem::class],version = 1)
abstract class QuestionDataBase : RoomDatabase() {


    abstract fun getQuestionDao() : QuestionDao


    companion object {
        private var INSTANCE: QuestionDataBase? = null
        fun getDatabase(context: Context): QuestionDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,QuestionDataBase::class.java, "questionDB")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }


}