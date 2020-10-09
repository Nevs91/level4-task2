package com.example.madlevel4task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel4task2.converters.Converters
import com.example.madlevel4task2.models.MatchResult
import com.example.madlevel4task2.dao.MatchResultsDao

@Database(entities = [MatchResult::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameResultListRoomDatabase : RoomDatabase() {

    abstract fun matchResultsDao(): MatchResultsDao

    companion object {
        private const val DATABASE_NAME = "GAME_RESULT_LIST_DATABASE"

        @Volatile
        private var gameResultListRoomDatabaseInstance: GameResultListRoomDatabase? = null

        fun getDatabase(context: Context): GameResultListRoomDatabase? {
            if (gameResultListRoomDatabaseInstance == null) {
                synchronized(GameResultListRoomDatabase::class.java) {
                    if (gameResultListRoomDatabaseInstance == null) {
                        gameResultListRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,GameResultListRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return gameResultListRoomDatabaseInstance
        }
    }

}
