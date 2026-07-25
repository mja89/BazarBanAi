package ir.mjahmadi.bazarban.ai.ui.iranmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ir.mjahmadi.bazarban.ai.databinding.FragmentIranMarketBinding

class IranMarketFragment : Fragment() {

    private var _binding: FragmentIranMarketBinding? = null
    private val binding get() = _binding!!
    private val viewModel: IranMarketViewModel by viewModels()
    private lateinit var adapter: IranMarketAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIranMarketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupObservers()
        setupSwipeRefresh()
        
        viewModel.loadIranMarketData()
    }

    private fun setupRecyclerView() {
        adapter = IranMarketAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadIranMarketData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
