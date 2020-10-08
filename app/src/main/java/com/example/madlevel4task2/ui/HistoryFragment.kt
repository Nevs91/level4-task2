package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.adapters.ResultsAdapter
import com.example.madlevel4task2.entities.MatchResult
import com.example.madlevel4task2.repositories.MatchResultsRepository
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private lateinit var matchResultsRepository: MatchResultsRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val matchResults = arrayListOf<MatchResult>()
    private val resultsAdapter = ResultsAdapter(matchResults)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchResultsRepository = MatchResultsRepository(requireContext())

        getGameResultsFromDatabase()

        initRv()
    }

    /**
     * Set the menu with specific menu options for this fragment
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_history_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Handle clicks on icons in the title bar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            R.id.action_clear_history -> {
                clearResultsHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Initialize the recycler view with a linear layout manager, adapter
     */
    private fun initRv() {
        rvMatchResults.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvMatchResults.adapter = resultsAdapter
        rvMatchResults.setHasFixedSize(true)
        rvMatchResults.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    /**
     * Remove all stored match results in the database and re-populate the recyclerView
     */
    private fun clearResultsHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                matchResultsRepository.deleteAllMatchResults()
            }
            getGameResultsFromDatabase()
        }
    }

    /**
     * Load all match results from the database
     */
    private fun getGameResultsFromDatabase() {
        mainScope.launch {
            val matchResults = withContext(Dispatchers.IO) {
                matchResultsRepository.getAllMatchResults()
            }

            this@HistoryFragment.matchResults.clear()
            this@HistoryFragment.matchResults.addAll(matchResults)
            this@HistoryFragment.resultsAdapter.notifyDataSetChanged()
        }
    }
}