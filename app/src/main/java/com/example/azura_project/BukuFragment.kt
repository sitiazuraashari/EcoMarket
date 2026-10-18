package com.example.azura_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.azura_project.databinding.FragmentBukuBinding

class BukuFragment : Fragment() {

    private var _binding: FragmentBukuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Category Toolbar
        binding.toolbarCategory.layoutCategoryToolbar.setBackgroundResource(R.color.golden_sunflower)
        binding.toolbarCategory.txtCategoryTitle.text = "Kategori Buku"
        binding.toolbarCategory.txtCategoryTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.rich_soil))
        binding.toolbarCategory.btnCategoryMessage.setColorFilter(ContextCompat.getColor(requireContext(), R.color.rich_soil))

        binding.toolbarCategory.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.toolbarCategory.btnCategoryMessage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MessageFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.cardNovelBuku.setOnClickListener {
            openDetail(
                "Novel Bekas",
                "Rp25.000",
                R.drawable.novel,
                "4.7 (890 ulasan)",
                "Novel fiksi terlaris kondisi mulus tanpa lipatan berlebih."
            )
        }

        binding.cardSainsBuku.setOnClickListener {
            openDetail(
                "Buku Sains",
                "Rp40.000",
                R.drawable.novel,
                "4.8 (320 ulasan)",
                "Ensiklopedia sains populer bergambar lengkap dan edukatif."
            )
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.showMainToolbar(false)
    }

    private fun openDetail(
        title: String,
        price: String,
        imageRes: Int,
        rating: String,
        desc: String
    ) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TITLE, title)
            putExtra(DetailActivity.EXTRA_PRICE, price)
            putExtra(DetailActivity.EXTRA_IMAGE_RES, imageRes)
            putExtra(DetailActivity.EXTRA_RATING, rating)
            putExtra(DetailActivity.EXTRA_DESC, desc)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
