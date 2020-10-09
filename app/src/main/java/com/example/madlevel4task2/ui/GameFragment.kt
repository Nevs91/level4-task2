package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madlevel4task2.R
import com.example.madlevel4task2.models.MatchOption
import com.example.madlevel4task2.models.MatchOption.*
import com.example.madlevel4task2.models.MatchOutcome
import com.example.madlevel4task2.models.MatchOutcome.*
import com.example.madlevel4task2.models.MatchResult
import com.example.madlevel4task2.repositories.MatchResultsRepository
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var matchResultsRepository: MatchResultsRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchResultsRepository = MatchResultsRepository(requireContext())

        // Show statistics of previous played games.
        updateScreenStatistics()

        // Event handlers for selecting images
        imgRock.setOnClickListener {
            playMatch(ROCK)
        }

        imgPaper.setOnClickListener {
            playMatch(PAPER)
        }

        imgScissors.setOnClickListener {
            playMatch(SCISSORS)
        }
    }

    /**
     * Play a match based on the players image choice
     */
    private fun playMatch(playerChoice: MatchOption) {
        // Get the computer choice by generating a random MatchOption
        val computerChoice = enumValues<MatchOption>()[Random.nextInt(0, 3)]

        // Get the matchResult by comparing the players choice against the computers choice.
        val matchResult = getMatchResult(playerChoice, computerChoice);

        // Store the object to the database and update the UI
        saveAndDisplayMatchResult(matchResult)
    }

    /**
     * Make and return a MatchResult object based on the players MatchOption and the computers MatchOption
     */
    private fun getMatchResult(playerChoice: MatchOption, computerChoice: MatchOption): MatchResult {
        val now = Date()
        var outcome: MatchOutcome = WIN

        when(playerChoice) {
            computerChoice -> {
                outcome = DRAW
            }
            ROCK -> {
                if (computerChoice == PAPER) {
                    outcome = LOSS
                }
            }
            PAPER -> {
                if (computerChoice == SCISSORS) {
                    outcome = LOSS
                }
            }
            SCISSORS -> {
                if (computerChoice == ROCK) {
                    outcome = LOSS
                }
            }
        }

        return MatchResult(computerChoice, playerChoice, outcome, now)
    }

    /**
     * Save a matchResult object to the database and update the UI.
     */
    private fun saveAndDisplayMatchResult(matchResult: MatchResult) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                matchResultsRepository.insertMatchResult(matchResult)
            }

            updateScreenResults(matchResult)
        }
    }

    /**
     * Update the user UI with data provided from the MatchResult object.
     */
    private fun updateScreenResults(matchResult: MatchResult) {

        // Set computers image pick
        when(matchResult.computerPick) {
            ROCK -> {
                imgResultComputer.setBackgroundResource(R.drawable.rock)
            }
            PAPER -> {
                imgResultComputer.setBackgroundResource(R.drawable.paper)
            }
            SCISSORS -> {
                imgResultComputer.setBackgroundResource(R.drawable.scissors)
            }
        }

        // Set players image pick
        when(matchResult.playerPick) {
            ROCK -> {
                imgResultPlayer.setBackgroundResource(R.drawable.rock)
            }
            PAPER -> {
                imgResultPlayer.setBackgroundResource(R.drawable.paper)
            }
            SCISSORS -> {
                imgResultPlayer.setBackgroundResource(R.drawable.scissors)
            }
        }

        // Set match outcome text
        when(matchResult.outcome) {
            WIN -> {
                tvResult.text = getString(R.string.game_win)
            }
            LOSS -> {
                tvResult.text = getString(R.string.game_loss)
            }
            DRAW -> {
                tvResult.text = getString(R.string.game_draw)
            }
        }

        // Show statistics of this game and previous played games.
        updateScreenStatistics()
    }

    /**
     * Display information about the amount of games won, lost or ended in a draw.
     */
    private fun updateScreenStatistics() {
        var wins = 0
        var draws = 0
        var losses = 0

        mainScope.launch {
            withContext(Dispatchers.IO) {
                wins = matchResultsRepository.getNumberOfWins()
                draws = matchResultsRepository.getNumberOfDraws()
                losses = matchResultsRepository.getNumberOLosses()
            }

            tvDetailedStatistics.text = getString(R.string.title_statistics_numbers, wins, draws, losses)
        }
    }
}