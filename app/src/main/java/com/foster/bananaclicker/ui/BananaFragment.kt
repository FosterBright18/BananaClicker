package com.foster.bananaclicker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.foster.bananaclicker.R
import com.foster.bananaclicker.databinding.FragmentBananaBinding
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.foster.bananaclicker.MainActivity

class BananaFragment : Fragment() {

    private var _binding: FragmentBananaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var bananas = 0
    lateinit var mainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBananaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mainActivity = requireActivity() as MainActivity

        binding.imageViewBananaBanana.setOnClickListener {

            binding.imageViewBananaBanana.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
            mainActivity.bananas++
            mainActivity.supportActionBar?.title = "${mainActivity.bananas} bananas"
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        mainActivity.supportActionBar?.title = "${R.drawable.bananas}${mainActivity.bananas} bananas"



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}