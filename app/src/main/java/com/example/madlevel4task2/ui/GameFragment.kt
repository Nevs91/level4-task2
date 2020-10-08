package com.example.madlevel4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.R
import com.example.madlevel4task2.entities.MatchOption
import kotlinx.android.synthetic.main.fragment_game.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgRock.setOnClickListener {
            playMatch(MatchOption.ROCK)
        }

        imgPaper.setOnClickListener {
            playMatch(MatchOption.PAPER)
        }

        imgScissors.setOnClickListener {
            playMatch(MatchOption.SCISSORS)
        }
    }

    private fun playMatch(matchOption: MatchOption) {

    }

}