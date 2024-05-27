package com.example.serinotechexam.lists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serinotechexam.databinding.FragmentProductListBinding
import com.example.serinotechexam.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.launch

class ProductsListFragment: Fragment(), ProductsListAdapter.Listener {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductsListFragmentViewModel by viewModel()
    private val adapter = ProductsListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentProductListBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservers()
    }

    private fun setupView() {
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProducts.adapter = adapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.products.observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onProductClicked(product: Product) {
        findNavController().navigate(ProductsListFragmentDirections.actionProductsListFragmentToProductDetailFragment(product.id))
    }
}