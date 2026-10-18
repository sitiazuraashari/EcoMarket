package com.example.azura_project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.azura_project.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_IMAGE_RES = "extra_image_res"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_DESC = "extra_desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // Populasikan tampilan dari Intent jika ada
        val title = intent.getStringExtra(EXTRA_TITLE)
        val price = intent.getStringExtra(EXTRA_PRICE)
        val imageRes = intent.getIntExtra(EXTRA_IMAGE_RES, 0)

        if (!title.isNullOrEmpty()) binding.txtDetailTitle.text = title
        if (!price.isNullOrEmpty()) binding.btnAddToCart.text = "Add to Cart | $price"
        if (imageRes != 0) binding.imgProduct.setImageResource(imageRes)

        // Tombol kembali ke halaman sebelumnya
        binding.btnBackDetail.setOnClickListener {
            finish()
        }
    }
}
