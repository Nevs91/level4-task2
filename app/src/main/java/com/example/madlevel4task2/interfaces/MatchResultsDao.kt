package com.example.madlevel4task2.interfaces

import androidx.room.*
import com.example.madlevel4task2.entities.MatchResult

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
}
