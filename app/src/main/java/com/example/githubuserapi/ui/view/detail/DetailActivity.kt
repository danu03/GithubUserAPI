package com.example.githubuserapi.ui.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapi.R
import com.example.githubuserapi.ui.adapter.SectionsPagerAdapter
import com.example.githubuserapi.data.pref.PreferenceHelper
import com.example.githubuserapi.ui.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        supportActionBar?.apply {
            title = resources.getString(R.string.detail_user)
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        preferenceHelper = PreferenceHelper(this)

        val username = preferenceHelper.username.toString()
        detailViewModel.detail(username)
        showLoading(true)

        detailViewModel.getDetail().observe(this, Observer {
            if (it != null) {
                Glide.with(this@DetailActivity).load(it.avatarUrl).into(iv_user)
                tv_name.text = it.name
                tv_username.text = it.login
                tv_company.text = it.company
                tv_location.text = it.location
                tv_repository.text = it.publicRepos.toString()
                showLoading(false)
            }

        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_detail.visibility = View.VISIBLE
        } else {
            pb_detail.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}