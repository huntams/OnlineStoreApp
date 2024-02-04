package com.example.onlinestoreapp.presentation.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentFavouriteBinding
import com.example.onlinestoreapp.presentation.catalog.CatalogAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment: Fragment(R.layout.fragment_favourite) {
    @Inject
    lateinit var catalogAdapter: CatalogAdapter
    private val viewModel by viewModels<ProfileViewModel>()
    val menuHost: MenuHost by lazy { requireActivity() }
    private val binding by viewBinding(FragmentFavouriteBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.productsLiveData.observe(viewLifecycleOwner){
            catalogAdapter.apply {
                submitList(it)
                setCallback {
                    findNavController().navigate(
                        FavouriteFragmentDirections.actionFavouriteFragmentToProductFragment(
                            it.id
                        )
                    )
                }
                setLike {
                    viewModel.deleteProduct(it)
                }
            }
            binding.recyclerViewCatalog.apply {
                adapter = catalogAdapter
                layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            }
        }

        super.onViewCreated(view, savedInstanceState)
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    else -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}