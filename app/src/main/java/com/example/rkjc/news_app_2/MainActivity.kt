package com.example.rkjc.news_app_2

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import com.example.rkjc.news_app_2.data.NewsItemRepository
import com.example.rkjc.news_app_2.data.NewsItemViewModel
import com.example.rkjc.news_app_2.databinding.ActivityMainBinding
import com.example.rkjc.news_app_2.sync.SyncUtilities
import android.content.Intent
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
import com.example.rkjc.news_app_2.data.NewsItem


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.newsRecyclerview.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProviders.of(this).get(NewsItemViewModel::class.java)
        val adapter = NewsRecyclerViewAdapter(NewsItemListener{
            it?.let{startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))}
        })

        binding.newsRecyclerview.adapter = adapter
        viewModel.allNewsItems.observe(this, Observer {
            it?.let{
                adapter.submitList(it)
            }
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