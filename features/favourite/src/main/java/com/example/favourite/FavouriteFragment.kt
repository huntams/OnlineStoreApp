package com.example.favourite

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.favourite.databinding.FragmentFavouriteBinding
import javax.inject.Inject


class FavouriteFragment : Fragment(R.layout.fragment_favourite) {
    @Inject
    lateinit var catalogAdapter: CatalogAdapter
    @Inject
    lateinit var viewModel: ProfileViewModel
    private val menuHost: MenuHost by lazy { requireActivity() }
    private val binding by viewBinding(FragmentFavouriteBinding::bind)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<CatalogComponentViewModel>().favouriteComponent.inject(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            catalogAdapter.apply {
                submitList(it)
                setCallback {
                    val deepLink = NavDeepLinkRequest.Builder
                        .fromUri("features://product/${it.id}".toUri())
                        .build()
                    findNavController().navigate(deepLink)
                    /*
                    findNavController().navigate(
                        FavouriteFragmentDirections.actionFavouriteFragmentToProductFragment(
                            it.id
                        )
                    )

                     */
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
