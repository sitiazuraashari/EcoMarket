package com.example.azura_project.Kategori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.azura_project.DetailActivity
import com.example.azura_project.MainActivity
import com.example.azura_project.Message.MessageFragment
import com.example.azura_project.R
import com.example.azura_project.databinding.FragmentRumahBinding

class RumahFragment : Fragment() {

    private var _binding: FragmentRumahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRumahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Category Toolbar
        binding.toolbarCategory.layoutCategoryToolbar.setBackgroundResource(R.color.blossom_petal)
        binding.toolbarCategory.txtCategoryTitle.text = "Kategori Rumah"
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

        binding.cardKursiRumah.setOnClickListener {
            openDetail(
                "Kursi Kayu",
                "Rp80.000",
                R.drawable.kursi,
                "4.6 (540 ulasan)",
                "Kursi kayu jati bekas kokoh dengan finishing halus dan estetik."
            )
        }

        binding.cardMejaRumah.setOnClickListener {
            openDetail(
                "Meja Belajar",
                "Rp150.000",
                R.drawable.meja,
                "4.9 (2.100 ulasan)",
                "Meja belajar minimalis kayu solid lengkap dengan laci penyimpanan."
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