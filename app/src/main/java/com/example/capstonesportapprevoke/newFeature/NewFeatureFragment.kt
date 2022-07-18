package com.example.capstonesportapprevoke.newFeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.capstonesportapprevoke.databinding.FragmentNewFeatureBinding
import com.example.capstonesportapprevoke.favorite.NewFeatureViewModel

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
            Toast.makeText(context, "new feature will be available", Toast.LENGTH_SHORT)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
