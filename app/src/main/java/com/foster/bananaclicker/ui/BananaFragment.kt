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
    private var monkeys = 0
    private var trees = 0
    private var cows = 0
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
            mainActivity.bananas += monkeys * 1 + trees * 5 + cows * 100
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

            mainActivity.bananas++
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
            var monkeyPrice = 10.00
            var on = true
            if(on){
                on == false
                binding.imageViewBananaMonkey.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                on == true
            }
            if(mainActivity.bananas >= monkeyPrice.toInt()){
                monkeys++
                mainActivity.bananas -= monkeyPrice.toInt()
                monkeyPrice += 2 * monkeys * monkeys
                binding.textViewBananaMonkeyDesc.text = "Monkey - Cost ${monkeyPrice.toInt()} bananas" +
                        "\nGives 1 banana per second\nYou currently have: $monkeys"
            }
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        }

        binding.imageViewBananaBananaTree.setOnClickListener {
            var treePrice = 200.00
            var on = true
            if(on){
                on == false
                binding.imageViewBananaBananaTree.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                on == true
            }
            if(mainActivity.bananas >= treePrice){
                trees++
                mainActivity.bananas -= treePrice.toInt()
                treePrice += 2 * trees * trees
                binding.textViewBananaTreedesc.text = "Banana Tree - Cost ${treePrice.toInt()} bananas" +
                        "\nGives 5 bananas per second\nYou currently have: $trees"
            }
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        }

        binding.imageViewBananaBananaCow.setOnClickListener {
            var cowPrice = 1000.00
            var on = true
            if(on){
                on == false
                binding.imageViewBananaBananaCow.startAnimation(AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.button_click))
                on == true
            }
            if(mainActivity.bananas >= cowPrice){
                cows++
                mainActivity.bananas -=cowPrice.toInt()
                cowPrice += 2 * cows * cows
                binding.textViewBananaBananaCowDesc.text = "Banana Cow - Cost ${cowPrice.toInt()} bananas" +
                        "\nGives 100 bananas per second\nYou currently have: $cows"
            }
            mainActivity.supportActionBar?.title = "You have ${mainActivity.bananas} bananas"
        }


        return root
    }


    override fun onResume() {
        super.onResume()
        //mainActivity.supportActionBar?.title = "${R.drawable.bananas}${mainActivity.bananas} bananas"



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}