package com.example.capstonesportapprevoke.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstonesportapprevoke.factory.ViewModelFactory
import com.example.capstonesportapprevoke.core.ui.TeamFavoriteSportAdapter
import com.example.capstonesportapprevoke.utils.FragmentUtils.goToDetailScreen
import com.example.capstonesportapprevoke.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

//    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var favoriteViewModel: FavoriteViewModel

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
//        loadKoinModules(favoriteModule)
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

                val factory = ViewModelFactory.getInstance(requireActivity())
                favoriteViewModel = ViewModelProvider(requireActivity(), factory)[FavoriteViewModel::class.java]

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
