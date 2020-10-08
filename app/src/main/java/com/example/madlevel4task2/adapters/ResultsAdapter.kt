package com.example.madlevel4task2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.entities.MatchOption
import com.example.madlevel4task2.entities.MatchOutcome
import com.example.madlevel4task2.entities.MatchResult
import kotlinx.android.synthetic.main.item_result.view.*

class ResultsAdapter(private val matchResult: List<MatchResult>) : RecyclerView.Adapter<ResultsAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(matchResult: MatchResult) {

            // Set computers image pick
            when(matchResult.computerPick) {
                MatchOption.ROCK -> {
                    itemView.imgResultComputer.setBackgroundResource(R.drawable.rock)
                }
                MatchOption.PAPER -> {
                    itemView.imgResultComputer.setBackgroundResource(R.drawable.paper)
                }
                MatchOption.SCISSORS -> {
                    itemView.imgResultComputer.setBackgroundResource(R.drawable.scissors)
                }
            }

            // Set players image pick
            when(matchResult.playerPick) {
                MatchOption.ROCK -> {
                    itemView.imgResultPlayer.setBackgroundResource(R.drawable.rock)
                }
                MatchOption.PAPER -> {
                    itemView.imgResultPlayer.setBackgroundResource(R.drawable.paper)
                }
                MatchOption.SCISSORS -> {
                    itemView.imgResultPlayer.setBackgroundResource(R.drawable.scissors)
                }
            }

            // Set match outcome text
            when(matchResult.outcome) {
                MatchOutcome.WIN -> {
                    itemView.tvResult.text = itemView.context.getString(R.string.game_win)
                }
                MatchOutcome.LOSS -> {
                    itemView.tvResult.text = itemView.context.getString(R.string.game_loss)
                }
                MatchOutcome.DRAW -> {
                    itemView.tvResult.text = itemView.context.getString(R.string.game_draw)
                }
            }

            // Set match date
            itemView.tvDate.text = matchResult.date.toString()
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return matchResult.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(matchResult[position])
    }
}