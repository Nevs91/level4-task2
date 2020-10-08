package com.example.madlevel4task2.repositories

import android.content.Context
import com.example.madlevel4task2.database.GameResultListRoomDatabase
import com.example.madlevel4task2.entities.MatchOutcome
import com.example.madlevel4task2.entities.MatchResult
import com.example.madlevel4task2.interfaces.MatchResultsDao

class MatchResultsRepository(context: Context) {

    private val matchResultsDao: MatchResultsDao

    /**
     * Initialise this repository with an instance of the GameResultListRoomDatabase
     * and a MatchResults Data Access Object
     */
    init {
        val database = GameResultListRoomDatabase.getDatabase(context)
        matchResultsDao = database!!.matchResultsDao()
    }

    /**
     * Get and return a list of all played games.
     */
    suspend fun getAllMatchResults(): List<MatchResult> {
        return matchResultsDao.getAllResults()
    }

    /**
     * Insert a new [MatchResult] object into the database.
     */
    suspend fun insertMatchResult(matchResult: MatchResult) {
        matchResultsDao.insertResult(matchResult)
    }

    /**
     * Delete a [MatchResult] from the database.
     */
    suspend fun deleteMatchResult(matchResult: MatchResult) {
        matchResultsDao.deleteResult(matchResult)
    }

    /**
     * Remove all data of played games.
     */
    suspend fun deleteAllMatchResults() {
        matchResultsDao.deleteAllMatchResults()
    }

    /**
     * Get the amount of wins from played games.
     */
    suspend fun getNumberOfWins(): Int {
        return matchResultsDao.getNumberOfWins(MatchOutcome.WIN)
    }

    /**
     * Get the amount of draws from played games.
     */
    suspend fun getNumberOfDraws(): Int {
        return matchResultsDao.getNumberOfWins(MatchOutcome.DRAW)
    }

    /**
     * Get the amount of losses from played games.
     */
    suspend fun getNumberOLosses(): Int {
        return matchResultsDao.getNumberOfWins(MatchOutcome.LOSS)
    }
}
