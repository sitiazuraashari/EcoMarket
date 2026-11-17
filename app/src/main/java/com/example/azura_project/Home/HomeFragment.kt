package com.example.azura_project.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.azura_project.CatalogActivity
import com.example.azura_project.Kategori.BukuFragment
import com.example.azura_project.DetailActivity
import com.example.azura_project.Kategori.FashionFragment
import com.example.azura_project.MainActivity
import com.example.azura_project.R
import com.example.azura_project.Kategori.RumahFragment
import com.example.azura_project.TipsFragment
import com.example.azura_project.WebActivity
import com.example.azura_project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Banner Promo & Button -> Open Web Article
        val openWebArticle = View.OnClickListener {
            val intent = Intent(requireContext(), WebActivity::class.java).apply {
                putExtra(
                    WebActivity.EXTRA_URL,
                    "https://bpd.telkomuniversity.ac.id/17-ide-kerajinan-barang-bekas-ubah-sampah-jadi-karya/"
                )
                putExtra(
                    WebActivity.EXTRA_TITLE,
                    "Ide Kerajinan Barang Bekas"
                )
            }
            startActivity(intent)
        }

        binding.cardBanner.setOnClickListener(openWebArticle)
        binding.btnBannerMulai.setOnClickListener(openWebArticle)

        // Catalog Button -> Open CatalogActivity (TabLayout & ViewPager2 & RecyclerView)
        binding.cardCatalog.setOnClickListener {
            startActivity(Intent(requireContext(), CatalogActivity::class.java))
        }

        // Tips Belanja Button
        binding.cardTips.setOnClickListener {
            openCategoryFragment(TipsFragment())
        }

        // Category Buttons Navigation
        binding.btnFashion.setOnClickListener {
            openCategoryFragment(FashionFragment())
        }

        binding.btnBuku.setOnClickListener {
            openCategoryFragment(BukuFragment())
        }

        binding.btnRumah.setOnClickListener {
            openCategoryFragment(RumahFragment())
        }

        // Product Cards -> Launch DetailActivity
        binding.cardTas.setOnClickListener {
            openDetail(
                "Tas Bekas",
                "Rp50.000",
                R.drawable.tas,
                "4.8 (1.230 ulasan)",
                "Tas selempang bekas berbahan berkualitas pilihan yang tahan lama, gaya ikonis, dan nyaman dipakai sehari-hari."
            )
        }

        binding.cardNovel.setOnClickListener {
            openDetail(
                "Novel Bekas",
                "Rp25.000",
                R.drawable.novel,
                "4.7 (890 ulasan)",
                "Novel fiksi populer dengan kondisi terawat baik, kertas bersih tanpa lipatan berlebih."
            )
        }

        binding.cardKursi.setOnClickListener {
            openDetail(
                "Kursi Kayu",
                "Rp80.000",
                R.drawable.kursi,
                "4.6 (540 ulasan)",
                "Kursi kayu jati bekas dengan desain ergonomis dan kokoh, sempurna untuk melengkapi ruangan rumah Anda."
            )
        }

        binding.cardMeja.setOnClickListener {
            openDetail(
                "Meja Belajar",
                "Rp150.000",
                R.drawable.meja,
                "4.9 (2.100 ulasan)",
                "Meja belajar kayu solid dengan slot penyimpanan luas, tahan beban berat dan sangat estetis."
            )
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.showMainToolbar(true)
    }

    private fun openCategoryFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
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
