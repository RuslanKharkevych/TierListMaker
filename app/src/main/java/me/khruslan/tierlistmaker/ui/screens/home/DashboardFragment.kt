package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import me.khruslan.tierlistmaker.databinding.FragmentDashboardBinding
import me.khruslan.tierlistmaker.ui.adapters.TierListPreviewAdapter
import me.khruslan.tierlistmaker.viewmodels.DashboardViewModel

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddNewList.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionFragmentDashboardToActivityTierList(
                    title = "Dummy title"
                )
            )
        }

        viewModel.tierListPreviewsLiveData.observe(viewLifecycleOwner) {
            with(binding.listTierLists) {
                adapter = TierListPreviewAdapter(it)
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}