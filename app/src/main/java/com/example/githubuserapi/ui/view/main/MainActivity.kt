package com.example.githubuserapi.ui.view.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapi.R
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.data.pref.PreferenceHelper
import com.example.githubuserapi.ui.adapter.UserAdapter
import com.example.githubuserapi.ui.view.detail.DetailActivity
import com.example.githubuserapi.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val list: MutableList<Item> = mutableListOf()
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        preferenceHelper = PreferenceHelper(this)

        adapter = UserAdapter(list)
        adapter.setOnItemClickCallback(object : UserAdapter.OnClickCallback {
            override fun onItemClicked(data: Item, position: Int) {
                showSelectedData(data)
            }
        })

        rv_search.layoutManager = LinearLayoutManager(this)
        rv_search.adapter = adapter

        mainViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        mainViewModel.getUser().observe(this, Observer {
            if (it != null) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
                showLoading(false)
                emptyData(false)
            }
        })
    }

    private fun showSelectedData(data: Item) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        preferenceHelper.username = data.login
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search_action)?.actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryHint = resources.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query?.trim().toString().isNotEmpty()) {
                        mainViewModel.searchUser(query.toString())
                        showLoading(true)
                        emptyData(true)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.trim().toString().isNotEmpty()) {
                        mainViewModel.searchUser(newText.toString())
                        showLoading(true)
                        emptyData(true)
                    }
                    return true
                }
            })
            return true
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_search.visibility = View.VISIBLE
        } else {
            pb_search.visibility = View.GONE
        }
    }

    private fun emptyData(state: Boolean) {
        if (state) {
            rv_search.visibility = View.GONE
            animationView.visibility = View.VISIBLE
        } else {
            rv_search.visibility = View.VISIBLE
            animationView.visibility = View.GONE
        }
    }
}