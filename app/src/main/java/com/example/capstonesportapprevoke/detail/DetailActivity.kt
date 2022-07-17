package com.example.capstonesportapprevoke.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.example.capstonesportapprevoke.R
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.factory.ViewModelFactory
import com.example.capstonesportapprevoke.databinding.ActivityDetailBinding
import kotlin.properties.Delegates


class DetailActivity : AppCompatActivity() {

    private val args : DetailActivityArgs by navArgs()

    private var isFavorite by Delegates.notNull<Boolean>()

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailMenu: Menu
    private lateinit var teamDetail: Team
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = ""

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        teamDetail = args.teamData
        showSportDetail()
    }

    private fun showSportDetail() {
        teamDetail.let {
            Glide.with(this@DetailActivity)
                .load(teamDetail.image)
                .into(binding.incSportImage.imgSport)

            binding.incSportContent.txtTitle.text = teamDetail.name
            binding.incSportContent.txtContent.text = teamDetail.description
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        detailMenu = menu
        isFavorite = teamDetail.isFavorite

        if (!teamDetail.isSeen) {
            detailMenu.getItem(1).isVisible = false
            detailViewModel.setSeenTeam(teamDetail)
        }
        setStatusFavorite(isFavorite)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navFavorite -> {
                isFavorite = !isFavorite

                detailViewModel.setFavoriteSport(teamDetail, isFavorite)
                setStatusFavorite(isFavorite)
            }
            else -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setStatusFavorite(isFavorite: Boolean) {
        if (isFavorite) detailMenu.getItem(0).setIcon(R.drawable.ic_favorite_full)
        else detailMenu.getItem(0).setIcon(R.drawable.ic_favorite)
    }
}