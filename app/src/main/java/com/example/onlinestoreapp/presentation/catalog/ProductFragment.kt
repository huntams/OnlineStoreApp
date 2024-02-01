package com.example.onlinestoreapp.presentation.catalog

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.CollapsedTextBinding
import com.example.onlinestoreapp.databinding.FragmentProductPageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product_page) {

    val menuHost: MenuHost by lazy { requireActivity() }
    private val binding by viewBinding(FragmentProductPageBinding::bind)
    private val viewModel by viewModels<CatalogViewModel>()
    private val args: ProductFragmentArgs by navArgs()
    @Inject
    lateinit var characteristicsAdapter: CharacteristicsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            viewModel.catalogLiveData.observe(viewLifecycleOwner) {

                val data = it.items.first{product->
                    product.id == args.productIndex
                }

                characteristicsAdapter.submitList(data.info)
                recyclerViewCharacteristics.adapter = characteristicsAdapter
                imageViewProduct.setImageDrawable( R.drawable.ic_bag_24.toDrawable())
                textDescription.textviewDescription.text = data.description
                textViewTitle.text = data.title
                textViewSubtitle.text = data.subtitle
                buttonBrand.text = data.title
                textviewTotal.text = data.available.toString()
                textComposition.textviewDescription.text = data.ingredients
            }
            textDescription.buttonHide.setOnClickListener {
                hideText(textDescription)
            }
            textComposition.buttonHide.setOnClickListener {
                hideText(textComposition)
            }
        }
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.product_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.Share -> {
                        true
                    }

                    else -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
    private fun hideText(collapsedText: CollapsedTextBinding){
        if(collapsedText.buttonHide.text==getString(R.string.hide)){
            collapsedText.buttonHide.text = getString(R.string.more)
            collapsedText.textviewDescription.maxLines = 2
        }else{
            collapsedText.buttonHide.text = getString(R.string.hide)
            collapsedText.textviewDescription.maxLines = Int.MAX_VALUE
        }
    }
}