package com.foster.bananaclicker.ui

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow

class BananaFragment : Fragment() {

    private var _binding: FragmentBananaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var bananas = 0
    var monkeyPrice = 10.00
    var treePrice = 300.00
    var cowPrice = 2000.00
    lateinit var mainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val scope = MainScope() // could also use an other scope such as viewModelScope if available
        var job: Job? = null

        fun stopAuto() {
            job?.cancel()
            job = null
        }

        fun getAuto() {
            mainActivity.bananas += mainActivity.monkeys * 1 + mainActivity.trees * 5 + mainActivity.cows * 100
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        }

        fun startAuto() {
            stopAuto()
            job = scope.launch {
                while(true) {
                    getAuto() // the function that should be ran every second
                    delay(1000)
                }
            }
        }
        startAuto()


        _binding = FragmentBananaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mainActivity = requireActivity() as MainActivity

        binding.imageViewBananaBanana.setOnClickListener {
            var on = true
            if(on){
                on == false
                binding.imageViewBananaBanana.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                on == true
            }

            mainActivity.bananas += mainActivity.bananaClick
            val ssb = SpannableStringBuilder()
            ssb.append("banana")
            ssb.setSpan(
                ImageSpan(requireContext(), R.drawable.bananas),
                ssb.length - 1,
                ssb.length,
                0
            )
            ssb.append("$bananas")
            //mainActivity.supportActionBar?.title = "$ssb"
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
            bananas = mainActivity.bananas

        }

        binding.imageViewBananaMonkey.setOnClickListener{
            var on = true
            if(on){
                on == false
                binding.imageViewBananaMonkey.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                on == true
            }
            if(mainActivity.bananas >= monkeyPrice.toInt()){
                mainActivity.monkeys++
                mainActivity.bananas -= monkeyPrice.toInt()
                monkeyPrice += 2 * mainActivity.monkeys * mainActivity.monkeys
                updateTextViews()
            }
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        }

        binding.imageViewBananaBananaTree.setOnClickListener {
            var on = true
            if(on){
                on == false
                binding.imageViewBananaBananaTree.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                on == true
            }
            if(mainActivity.bananas >= treePrice){
                mainActivity.trees++
                mainActivity.bananas -= treePrice.toInt()
                treePrice += 2 * mainActivity.trees * mainActivity.trees
                updateTextViews()
            }
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        }

        binding.imageViewBananaBananaCow.setOnClickListener {
            var on = true
            if(on){
                on == false
                binding.imageViewBananaBananaCow.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                on == true
            }
            if(mainActivity.bananas >= cowPrice){
                mainActivity.cows++
                mainActivity.bananas -= cowPrice.toInt()
                cowPrice += 2 * mainActivity.cows * mainActivity.cows
                updateTextViews()
            }
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        }


        return root
    }

    private fun updateTextViews() {
        binding.textViewBananaIncome.text = "Bananas per second:\n" +
                "${mainActivity.monkeys * 1 + mainActivity.trees * 5 + mainActivity.cows * 100}" +
                "\nBananas per click:\n${mainActivity.bananaClick}"
        binding.textViewBananaMonkeyDesc.text = "Monkey - Cost ${monkeyPrice.toInt()} bananas" +
                "\nGives 1 banana per second\nYou currently have: ${mainActivity.monkeys}"
        binding.textViewBananaTreedesc.text = "Banana Tree - Cost ${treePrice.toInt()} bananas" +
                "\nGives 5 bananas per second\nYou currently have: ${mainActivity.trees}"
        binding.textViewBananaBananaCowDesc.text = "Banana Cow - Cost ${cowPrice.toInt()} bananas" +
                "\nGives 100 bananas per second\nYou currently have: ${mainActivity.cows}"

    }


    override fun onResume() {
        super.onResume()
        //mainActivity.supportActionBar?.title = "${R.drawable.bananas}${mainActivity.bananas} bananas"

        updateTextViews()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}