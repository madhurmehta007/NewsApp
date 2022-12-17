package dev.redfox.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.redfox.newsapp.databinding.ActivityMainBinding
import dev.redfox.newsapp.ui.dashboard.DashboardFragment
import dev.redfox.newsapp.ui.home.HomeFragment
import dev.redfox.newsapp.ui.saved.SavedNewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setStatusBarColor(this.getResources().getColor(R.color.material_blue_light))
        replaceFragment(HomeFragment())

        binding.navView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.navigation_home -> replaceFragment(HomeFragment())
                R.id.navigation_notifications -> replaceFragment(SavedNewsFragment())

                else -> {}

            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}