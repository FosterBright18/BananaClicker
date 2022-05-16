package com.foster.bananaclicker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.foster.bananaclicker.MainActivity
import com.foster.bananaclicker.databinding.FragmentLeaderboardBinding


class LeaderboardFragment : Fragment() {

    private var _binding: FragmentLeaderboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var mainActivity = activity as MainActivity
        mainActivity.getScores()

        return root
    }

    override fun onResume() {
        super.onResume()
        var mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.title = "${mainActivity.bananas} bananas"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}