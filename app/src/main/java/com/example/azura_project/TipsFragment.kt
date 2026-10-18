package com.example.azura_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.azura_project.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol Back pada Toolbar Tips -> Kembali ke fragment sebelumnya / HomeFragment
        binding.btnBackTips.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStack()
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
        }

        // Data tips belanja barang bekas
        val tipsList = listOf(
            mapOf(
                "title" to "1. Periksa Kondisi Barang",
                "desc" to "Pastikan barang masih layak digunakan."
            ),
            mapOf(
                "title" to "2. Cek Foto Produk",
                "desc" to "Perhatikan detail barang sebelum membeli."
            ),
            mapOf(
                "title" to "3. Bandingkan Harga",
                "desc" to "Pilih barang dengan harga yang sesuai."
            ),
            mapOf(
                "title" to "4. Tanyakan Detail Barang",
                "desc" to "Tanyakan kekurangan barang kepada penjual."
            ),
            mapOf(
                "title" to "5. Kurangi Sampah",
                "desc" to "Gunakan kembali barang yang masih layak."
            )
        )

        // Menggunakan SimpleAdapter dengan android.R.layout.simple_list_item_2
        val adapter = SimpleAdapter(
            requireContext(),
            tipsList,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewTips.adapter = adapter

        // Listener ketika item ListView diklik
        binding.listViewTips.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = tipsList[position]
            val title = selectedItem["title"]
            Toast.makeText(requireContext(), "Anda memilih: $title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Menyembunyikan Main Toolbar agar hanya Toolbar Tips yang tampil
        (activity as? MainActivity)?.showMainToolbar(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
