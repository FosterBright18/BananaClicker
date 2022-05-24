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
    private var bananaUp = 1
    private var monkeyUp = 1
    private var treeUp = 1
    private var cowUp = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpgradesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mainActivity = requireActivity() as MainActivity


        binding.imageViewUpgradesBanana.setOnClickListener{
            if((mainActivity.bananas >= (150 * bananaUp).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesBanana.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.bananaClick++
                bananaUp++
                binding.textViewUpgradesBananaDesc.text = "Banana clicks give $bananaUp" +
                        " times as many bananas as usual\\nCost ${150 * bananaUp} bananas"
            }
        }
        binding.imageViewUpgradesMonkey.setOnClickListener{
            if((mainActivity.bananas >= (700 * monkeyUp).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesMonkey.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.monkeyUp++
                monkeyUp++
                binding.textViewUpgradesMonkeyDesc.text = "Monkeys give $monkeyUp" +
                        " times as many bananas as usual\\nCost ${700 * monkeyUp} bananas"
            }
        }
        binding.imageViewUpgradesCow.setOnClickListener{
            if((mainActivity.bananas >= (1500 * cowUp).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesCow.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.cowUp++
                cowUp++
                binding.textViewUpgradesCowDesc.text = "Banana cow gives $cowUp" +
                        " times as many bananas as usual\\nCost ${1500 * cowUp} bananas"
            }
        }
        binding.imageViewUpgradesTree.setOnClickListener{
            if((mainActivity.bananas >= (5000 * treeUp).toInt())){
                var on = true
                if(on){
                    on == false
                    binding.imageViewUpgradesTree.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                    on == true
                }
                mainActivity.treeUp++
                treeUp++
                binding.textViewUpgradesTreeDesc.text = "Banana tree gives $treeUp" +
                        " times as many bananas as usual\\nCost ${5000 * treeUp} bananas"
            }
        }


        return root
    }



    override fun onResume() {
        super.onResume()
        var mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}