package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.adapters.SearchResultsAdapter
import com.williamxyshi.njsandroid.utils.MovieServerAccessObject
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel

class SearchFragment: Fragment() {

    private lateinit var vm: MainActivityViewModel

    private lateinit var searchButton: Button
    private lateinit var searchText: EditText

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: SearchResultsAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false) as ViewGroup
        setUpVM()

        searchButton = rootView.findViewById(R.id.searchButton)

        searchText = rootView.findViewById(R.id.movieSearchName)


        initialize()

        recyclerView = rootView.findViewById(R.id.searchResultRecyclerView)
        adapter = SearchResultsAdapter(vm, context?:return null)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter



        return rootView
    }

    private fun setUpVM(){
        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)


        vm.searchPageResults.observe(this, Observer{

            recyclerView.adapter?.notifyDataSetChanged()

        })
    }

    private fun initialize(){
        searchText.addTextChangedListener(  object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                MovieServerAccessObject.searchMovie(p0.toString(), vm)
            }
        }


        )

        searchButton.setOnClickListener {
            val text: String = searchText.text.toString()

            Log.d(TAG, "movie search string: ${text}")

            MovieServerAccessObject.searchMovie(text, vm)


        }


    }


    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
        vm.currentPage.value = MainActivityViewModel.SEARCH_PAGE
    }

    companion object{
        const val TAG = "SearchFragment"
    }




}