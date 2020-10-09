package com.example.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.models.MatchOutcome
import com.example.madlevel4task2.models.MatchResult

@Dao
interface MatchResultsDao {

    @Query("SELECT * FROM resultsTable")
    suspend fun getAllResults(): List<MatchResult>

    @Insert
    suspend fun insertResult(matchResult: MatchResult)

    @Delete
    suspend fun deleteResult(matchResult: MatchResult)

    @Query("DELETE FROM resultsTable")
    suspend fun deleteAllMatchResults()

    @Query("SELECT COUNT(*) FROM resultsTable WHERE matchOutcome = :matchOutcome")
    suspend fun getNumberOfWins(matchOutcome: MatchOutcome): Int

    @Query("SELECT COUNT(*) FROM resultsTable WHERE matchOutcome = :matchOutcome")
    suspend fun getNumberOfDraws(matchOutcome: MatchOutcome): Int

    @Query("SELECT COUNT(*) FROM resultsTable WHERE matchOutcome = :matchOutcome")
    suspend fun getNumberOfLosses(matchOutcome: MatchOutcome): Int
}
