package com.example.afb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afb.data.Meme
import com.example.afb.databinding.ActivityMainBinding
import com.example.afb.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var afbAdapter: AfbAdapter

    private val viewModel: AfbViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        afbAdapter = AfbAdapter(mutableListOf())
        initUi()
        initObservers()

    }

    private fun initObservers() {
        viewModel.getMemes().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvAfb.visibility = View.VISIBLE
                        resource.data?.let { memes -> retrieveList(memes) }
                    }
                    Status.ERROR -> {
                        binding.rvAfb.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvAfb.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(memes: List<Meme>) {
        afbAdapter.apply {
            addMemes(memes)
        }
    }

    private fun initUi() {

        binding.apply {
            afbAdapter = AfbAdapter(mutableListOf())
            rvAfb.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = afbAdapter
            }
        }
    }

}