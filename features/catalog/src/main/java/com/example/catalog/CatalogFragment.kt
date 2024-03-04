package com.example.catalog

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.net.toUri
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.catalog.databinding.FragmentCatalogBinding
import com.example.model.Product
import com.google.android.material.chip.Chip
import javax.inject.Inject


class CatalogFragment : Fragment(R.layout.fragment_catalog) {


    private val binding by viewBinding(FragmentCatalogBinding::bind)

    @Inject
    lateinit var catalogAdapter: CatalogAdapter
    @Inject
    lateinit var viewModel: CatalogViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<CatalogComponentViewModel>().catalogComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            group.chipGroupFilter.check(group.chipGroupFilter.children.toList()[0].id)

            viewModel.catalogLiveData.observe(viewLifecycleOwner) { items ->
                var data = items.items
                group.chipGroupFilter.setOnCheckedStateChangeListener { _, checkedId ->
                    if (checkedId.isNotEmpty()) {
                        val titleOrNull =
                            group.chipGroupFilter.findViewById<Chip>(checkedId[0])?.text.toString()
                        data = viewModel.filterData(items.items, titleOrNull)
                    } else group.chipGroupFilter.check(group.chipGroupFilter.children.toList()[0].id)
                    catalogAdapter.submitList(data)
                }
                filterTab.buttonMenu.setOnClickListener {
                    showMenu(it, R.menu.filter_menu, data)
                }
                viewModel.filterLiveData.observe(viewLifecycleOwner) {
                    catalogAdapter.submitList(it)
                }
                catalogAdapter.apply {
                    submitList(items.items)
                    setCallback {
                        val deepLink = NavDeepLinkRequest.Builder
                            .fromUri("features://product/${it.id}".toUri())
                            .build()
                        findNavController().navigate(deepLink)
                        /*
                        findNavController().navigate(
                            CatalogFragmentDirections.actionCatalogToProductFragment(
                                it.id
                            )
                        )

                         */
                    }
                    setLike {
                        viewModel.likeProduct(it)
                        group.chipGroupFilter.check(group.chipGroupFilter.children.toList()[0].id)
                    }
                }
                recyclerViewCatalog.apply {
                    adapter = catalogAdapter
                    layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                }
            }
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int, data: List<Product>) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.Popularity -> {
                    binding.filterTab.buttonMenu.text = getString(R.string.by_popularity)
                    catalogAdapter.submitList(data.sortedByDescending {
                        it.feedback.rating
                    })
                }

                R.id.Price -> {
                    binding.filterTab.buttonMenu.text = getString(R.string.by_price)
                    catalogAdapter.submitList(data.sortedByDescending {
                        it.price.priceWithDiscount.toInt()
                    })
                }

                R.id.Ascending -> {
                    binding.filterTab.buttonMenu.text = getString(R.string.by_ascending_price)
                    catalogAdapter.submitList(data.sortedBy {
                        it.price.priceWithDiscount.toInt()
                    })
                }
            }
            false
        }
        popup.show()
    }

}