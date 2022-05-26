package com.foster.bananaclicker

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.foster.bananaclicker.databinding.ActivityMainBinding
import com.foster.bananaclicker.ui.Score

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var scores : MutableList<Score> = mutableListOf()
    var bananas = 0
    var monkeys = 0
    var trees = 0
    var cows = 0
    var bananaClick = 1
    var monkeyUp = 1
    var cowUp = 1
    var treeUp = 1
    var bananaUpPrice = 150
    var monkeyUpPrice = 700
    var treeUpPrice = 1500
    var cowUpPrice = 5000
    var ssb = SpannableStringBuilder()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ssb.append("long")
//        ssb.setSpan(
//            ImageSpan(this, R.drawable.bananas),
//            ssb.length - 1,
//            ssb.length,
//            0
//        )
//        ssb.append("$bananas")


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_banana, R.id.navigation_upgrades, R.id.navigation_stats, R.id.navigation_leaderboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun addScore(score : Score) {
        scores.add(score)
    }

    fun getScores() : List<Score> {
        return  scores
    }
}