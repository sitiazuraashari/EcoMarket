
package com.example.azura_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.azura_project.databinding.ActivityLoginBinding
import com.example.azura_project.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val density = resources.displayMetrics.density
            val horizontalPadding = (28 * density).toInt()
            v.setPadding(
                systemBars.left + horizontalPadding,
                systemBars.top,
                systemBars.right + horizontalPadding,
                systemBars.bottom
            )
            insets
        }

        val sharedPref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString().trim()
            val pass = binding.editPassword.text.toString()

            if (email.isNotEmpty() && email == pass) {
                sharedPref.edit()
                    .putBoolean("isLogin", true)
                    .putString("email", email)
                    .apply()

                startActivity(
                    Intent(this, MainActivity::class.java)
                )
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silakan coba lagi.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}