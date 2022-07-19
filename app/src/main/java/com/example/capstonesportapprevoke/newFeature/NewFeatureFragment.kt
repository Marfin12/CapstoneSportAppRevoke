package com.example.capstonesportapprevoke.newFeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstonesportapprevoke.core.factory.ViewModelFactory
import com.example.capstonesportapprevoke.core.ui.TeamSeenSportAdapter
import com.example.capstonesportapprevoke.databinding.FragmentNewFeatureBinding

class NewFeatureFragment : Fragment() {

    private lateinit var newFeatureViewModel: NewFeatureViewModel

    private var _binding: FragmentNewFeatureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewFeatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            with(binding) {
                val teamSeenSportAdapter = TeamSeenSportAdapter()

                val factory = ViewModelFactory.getInstance(requireActivity())
                newFeatureViewModel = ViewModelProvider(requireActivity(), factory)[NewFeatureViewModel::class.java]

                newFeatureViewModel.seenTeam.observe(viewLifecycleOwner) { teamData ->
                    teamSeenSportAdapter.setData(teamData)

                    if (teamData.isNotEmpty()) {
                        incNewFragmentEmpty.emptyFavoriteData.visibility = View.GONE
                    } else {
                        incNewFragmentEmpty.emptyFavoriteData.visibility = View.VISIBLE
                    }

                    recyclerView.layoutManager = GridLayoutManager(context, 1)
                    recyclerView.adapter = teamSeenSportAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
