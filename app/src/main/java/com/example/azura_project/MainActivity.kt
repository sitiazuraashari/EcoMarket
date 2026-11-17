package com.example.azura_project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.azura_project.Home.HomeFragment
import com.example.azura_project.Message.MessageFragment
import com.example.azura_project.Profile.ProfileFragment
import com.example.azura_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                0
            )
            insets
        }

        // Setup Toolbar Menu Listener (Message, Cart & Logout)
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_message -> {
                    replaceFragment(MessageFragment(), addToBackStack = true)
                    true
                }
                R.id.action_cart -> {
                    Toast.makeText(this, "Membuka Keranjang Belanja", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_logout -> {
                    showLogoutDialog()
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Setup Bottom Navigation Listener
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_favorit -> {
                    replaceFragment(FavoritFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Helper untuk menampilkan atau menyembunyikan Main Toolbar
    fun showMainToolbar(show: Boolean) {
        binding.toolbar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Yakin mau logout?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                sharedPref.edit().clear().apply()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}
