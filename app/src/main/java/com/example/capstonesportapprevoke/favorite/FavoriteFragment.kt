package com.example.capstonesportapprevoke.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstonesportapprevoke.core.ui.TeamFavoriteSportAdapter
import com.example.capstonesportapprevoke.core.utils.FragmentUtils.goToDetailScreen
import com.example.capstonesportapprevoke.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            with(binding) {
                val teamFavoriteSportAdapter = TeamFavoriteSportAdapter()

                teamFavoriteSportAdapter.onItemClick = { selectedData ->
                    goToDetailScreen(selectedData, this@FavoriteFragment)
                }

                teamFavoriteSportAdapter.onFavClick = { selectedData ->
                    favoriteViewModel.removeFavoriteSport(selectedData)
                }

                favoriteViewModel.favoriteTeam.observe(viewLifecycleOwner) { teamData ->
                    teamFavoriteSportAdapter.setData(teamData)

                    if (teamData.isNotEmpty()) {
                        incFavoriteFragmentEmpty.emptyFavoriteData.visibility = View.GONE
                    } else {
                        incFavoriteFragmentEmpty.emptyFavoriteData.visibility = View.VISIBLE
                    }

                    recyclerView.layoutManager = GridLayoutManager(context, 1)
                    recyclerView.adapter = teamFavoriteSportAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
