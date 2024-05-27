package com.example.serinotechexam.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.serinotechexam.R
import com.example.serinotechexam.databinding.FragmentProductDetailBinding
import com.example.serinotechexam.model.Product
import com.example.serinotechexam.utils.StateResult
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment: Fragment() {

    companion object {
        const val EXTRA_PRODUCT_ID = "EXTRA_PRODUCT_ID"
    }

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailFragmentViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentProductDetailBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        val productId = arguments?.getInt(EXTRA_PRODUCT_ID) ?: 0
        lifecycleScope.launch {
            viewModel.getProductDetails(productId).collect {
                when(it) {
                    is StateResult.Success -> {
                        bindProduct(it.data)
                    }
                    is StateResult.Error -> {
                        // Handle error
                    }
                    is StateResult.Loading -> {
                        // Handle loading
                    }
                }
            }
        }
    }

    private fun bindProduct(product: Product) {
        binding.textviewProductName.text = product.title
        binding.textviewProductPrice.text = "Php ${product.price.toString()}"
        binding.textviewProductDescription.text = product.description
        val imageUrl = try {
            product.images.get(0)
        } catch (e: Exception) {
            product.thumbnail
        }

        Glide.with(binding.imageviewProduct)
            .load(imageUrl)
            .placeholder(R.color.light_grey)
            .error(R.color.light_grey)
            .into(binding.imageviewProduct)
    }
}