package com.example.madlevel4task2.repositories

import android.content.Context
import com.example.madlevel4task2.database.GameResultListRoomDatabase
import com.example.madlevel4task2.entities.MatchOption
import com.example.madlevel4task2.entities.MatchOutcome
import com.example.madlevel4task2.entities.MatchResult
import com.example.madlevel4task2.interfaces.MatchResultsDao

class MatchResultsRepository(context: Context) {

    private val matchResultsDao: MatchResultsDao

    init {
        val database = GameResultListRoomDatabase.getDatabase(context)
        matchResultsDao = database!!.matchResultsDao()
    }

    suspend fun getAllMatchResults(): List<MatchResult> {
        return matchResultsDao.getAllResults()
    }

    suspend fun insertMatchResult(matchResult: MatchResult) {
        matchResultsDao.insertResult(matchResult)
    }

    suspend fun deleteMatchResult(matchResult: MatchResult) {
        matchResultsDao.deleteResult(matchResult)
    }

    suspend fun deleteAllMatchResults() {
        matchResultsDao.deleteAllMatchResults()
    }

    suspend fun getNumberOfWins(): Int {
        return matchResultsDao.getNumberOfWins(MatchOutcome.WIN)
    }

    suspend fun getNumberOfDraws(): Int {
        return matchResultsDao.getNumberOfWins(MatchOutcome.DRAW)
    }

    suspend fun getNumberOLosses(): Int {
        return matchResultsDao.getNumberOfWins(MatchOutcome.LOSS)
    }
}
