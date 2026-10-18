package com.example.azura_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.azura_project.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Toko Thrift Eco", "Halo ka! Tas bekas warna cokelatnya masih ready ya.", "https://i.pravatar.cc/150?img=47"),
        MessageModel("Budi Pratama", "Apakah kursi kayunya bisa dikirim hari ini?", "https://i.pravatar.cc/150?img=12"),
        MessageModel("Siti Rahma", "Untuk novel bekasnya apa bisa nego Rp20.000?", "https://i.pravatar.cc/150?img=44"),
        MessageModel("Rian Febrian", "Bahan meja belajarnya dari kayu jati solid kan?", "https://i.pravatar.cc/150?img=13"),
        MessageModel("Andi Wijaya", "Saya sudah bayar pesanan jaket denimnya ya kak.", "https://i.pravatar.cc/150?img=49"),
        MessageModel("Lani Marlina", "Barang sudah sampai dalam kondisi mulus, makasih!", "https://i.pravatar.cc/150?img=45"),
        MessageModel("Toko Buku Bekas", "Stok buku sainsnya masih ada 2 eksemplar kak.", "https://i.pravatar.cc/150?img=48"),
        MessageModel("Dedi Kurniawan", "Apakah lokasi COD bisa di daerah Jakarta Selatan?", "https://i.pravatar.cc/150?img=15"),
        MessageModel("Eka Permata", "Resi pengiriman sepatu vintage sudah diupdate ya.", "https://i.pravatar.cc/150?img=16"),
        MessageModel("Fajar Nugraha", "Kondisi meja belajarnya masih 95% bagus kak.", "https://i.pravatar.cc/150?img=14")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackMessage.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStack()
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        (activity as? MainActivity)?.showMainToolbar(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
