package com.example.capstonesportapprevoke.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capstonesportapprevoke.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

//    private val favoriteViewModel: FavoriteViewModel by viewModel()

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

//        if (activity != null) {
//            with(binding) {
//                val teamFavoriteSportAdapter = TeamFavoriteSportAdapter()
//
//                teamFavoriteSportAdapter.onItemClick = { selectedData ->
//                    val intent = Intent(activity, DetailActivity::class.java)
//
//                    intent.putExtra(DetailActivity.TEAM_DATA, selectedData)
//                    startActivity(intent)
//                }
//
//                teamFavoriteSportAdapter.onFavClick = { selectedData ->
//                    favoriteViewModel.removeFavoriteSport(selectedData)
//                }
//
//                favoriteViewModel.favoriteTeam.observe(viewLifecycleOwner) { teamData ->
//                    teamFavoriteSportAdapter.setData(teamData)
//
//                    if (teamData.isNotEmpty()) {
//                        incFavoriteFragmentEmpty.emptyFavoriteData.visibility = View.GONE
//                        MapsActivity.teamData = teamData
//                        if (teamData.size > 1) {
//                            mapButton.setOnClickListener {
//                                val intent = Intent(activity, MapsActivity::class.java)
//                                startActivity(intent)
//                            }
//                        } else {
//                            mapButton.visibility = View.GONE
//                            disabledButton.visibility = View.VISIBLE
//                        }
//                    } else {
//                        incFavoriteFragmentEmpty.emptyFavoriteData.visibility = View.VISIBLE
//                        mapButton.visibility = View.GONE
//                        disabledButton.visibility = View.GONE
//                    }
//
//                    recyclerView.layoutManager = GridLayoutManager(context, 1)
//                    recyclerView.adapter = teamFavoriteSportAdapter
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
