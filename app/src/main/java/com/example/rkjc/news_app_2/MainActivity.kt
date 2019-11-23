package com.example.rkjc.news_app_2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import com.example.rkjc.news_app_2.data.NewsItemRepository
import com.example.rkjc.news_app_2.data.NewsItemViewModel
import com.example.rkjc.news_app_2.databinding.ActivityMainBinding
import com.example.rkjc.news_app_2.sync.SyncUtilities


class MainActivity : AppCompatActivity() {

    private var mNewsViewAdapter: NewsRecyclerViewAdapter? = null
    private var mNewsItemViewModel: NewsItemViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.newsRecyclerview.layoutManager = LinearLayoutManager(this)
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel::class.java)
        mNewsViewAdapter = NewsRecyclerViewAdapter(this@MainActivity)
        binding.newsRecyclerview.adapter = mNewsViewAdapter
        mNewsItemViewModel!!.allNewsItems.observe(this, Observer { newsItems ->
            mNewsViewAdapter!!.newsItemList = newsItems
            mNewsViewAdapter!!.notifyDataSetChanged()
        })
        SyncUtilities.scheduleAPISync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId

        if (itemThatWasClickedId == R.id.action_search) {
            val repository = NewsItemRepository.getInstance(this.application)
            repository.syncWithAPI()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
