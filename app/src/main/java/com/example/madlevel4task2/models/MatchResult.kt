package com.example.madlevel4task2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

enum class MatchOption {
    ROCK, PAPER, SCISSORS
}

enum class MatchOutcome {
    WIN, LOSS, DRAW
}

@Entity(tableName = "resultsTable")
data class MatchResult(

    @ColumnInfo(name = "computerPick")
    var computerPick: MatchOption,

    @ColumnInfo(name = "playerPick")
    var playerPick: MatchOption,

    @ColumnInfo(name = "matchOutcome")
    var outcome: MatchOutcome,

    @ColumnInfo(name = "matchDate")
    val date: Date?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)