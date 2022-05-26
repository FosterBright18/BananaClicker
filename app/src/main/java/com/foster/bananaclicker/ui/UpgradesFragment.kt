package com.foster.bananaclicker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.foster.bananaclicker.MainActivity
import com.foster.bananaclicker.R
import com.foster.bananaclicker.databinding.FragmentUpgradesBinding

class UpgradesFragment : Fragment() {

    private var _binding: FragmentUpgradesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mainActivity : MainActivity
    private var bananaUp = mainActivity.bananaClick
    private var monkeyUp = mainActivity.monkeyUp
    private var treeUp = mainActivity.treeUp
    private var cowUp = mainActivity.cowUp
    private var bananaUpPrice = mainActivity.bananaUpPrice
    private var monkeyUpPrice = mainActivity.monkeyUpPrice
    private var treeUpPrice = mainActivity.treeUpPrice
    private var cowUpPrice = mainActivity.cowUpPrice

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpgradesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mainActivity = requireActivity() as MainActivity


        binding.imageViewUpgradesBanana.setOnClickListener{
            if((mainActivity.bananas >= (bananaUpPrice).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesBanana.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.bananas -= bananaUpPrice
                mainActivity.bananaClick++
                bananaUp++
                bananaUpPrice *= bananaUp
                updateTextViews()
            }
        }
        binding.imageViewUpgradesMonkey.setOnClickListener{
            if((mainActivity.bananas >= (monkeyUpPrice).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesMonkey.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.bananas -= monkeyUpPrice
                mainActivity.monkeyUp++
                monkeyUp++
                monkeyUpPrice *= monkeyUp
                updateTextViews()
            }
        }
        binding.imageViewUpgradesCow.setOnClickListener{
            if((mainActivity.bananas >= (cowUpPrice).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesCow.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.bananas -= cowUpPrice
                mainActivity.cowUp++
                cowUp++
                cowUpPrice *= cowUp
                updateTextViews()
            }
        }
        binding.imageViewUpgradesTree.setOnClickListener{
            if((mainActivity.bananas >= (20000 * treeUpPrice).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesTree.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.bananas -= treeUpPrice
                mainActivity.treeUp++
                treeUp++
                treeUpPrice *= treeUp
                updateTextViews()
            }
        }


        return root
    }

    private fun updateTextViews() {
        binding.textViewUpgradesBananaDesc.text = "Banana clicks give $bananaUp" +
                " times as many bananas as usual\nCost ${bananaUpPrice} bananas"
        mainActivity.bananaClick = bananaUpPrice
        binding.textViewUpgradesMonkeyDesc.text = "Monkeys give $monkeyUp" +
                " times as many bananas as usual\nCost ${monkeyUpPrice} bananas"
        mainActivity.monkeyUpPrice = monkeyUpPrice
        binding.textViewUpgradesCowDesc.text = "Banana cow gives $cowUp" +
                " times as many bananas as usual\nCost ${cowUpPrice} bananas"
        mainActivity.cowUpPrice = cowUpPrice
        binding.textViewUpgradesTreeDesc.text = "Banana tree gives $treeUp" +
                " times as many bananas as usual\nCost ${treeUpPrice} bananas"
        mainActivity.treeUpPrice = treeUpPrice
    }


    override fun onResume() {
        super.onResume()
        var mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        updateTextViews()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}