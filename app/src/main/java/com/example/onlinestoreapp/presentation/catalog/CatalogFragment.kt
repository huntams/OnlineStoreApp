package com.example.onlinestoreapp.presentation.catalog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentCatalogBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CatalogFragment : Fragment(R.layout.fragment_catalog) {


    private val binding by viewBinding(FragmentCatalogBinding::bind)

    @Inject
    lateinit var catalogAdapter: CatalogAdapter

    private val viewModel by viewModels<CatalogViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            group.chipGroupFilter.check(group.chipGroupFilter.children.toList()[0].id)

            viewModel.catalogLiveData.observe(viewLifecycleOwner) { items ->
                group.chipGroupFilter.setOnCheckedStateChangeListener { _, checkedId ->
                    if (checkedId.isNotEmpty()) {
                        val titleOrNull = group.chipGroupFilter.findViewById<Chip>(checkedId[0])?.text
                        viewModel.filterData(items.items,titleOrNull.toString())
                        Toast.makeText(requireContext(), titleOrNull, Toast.LENGTH_LONG).show()
                    } else group.chipGroupFilter.check(group.chipGroupFilter.children.toList()[0].id)
                }
                viewModel.filterLiveData.observe(viewLifecycleOwner){
                    catalogAdapter.submitList(it)
                }
                catalogAdapter.submitList(items.items)
                catalogAdapter.setCallback {
                    findNavController().navigate(
                        CatalogFragmentDirections.actionCatalogToProductFragment(
                            it.id
                        )
                    )
                }
                recyclerViewCatalog.apply {
                    adapter = catalogAdapter
                    layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
                }
            }
        }
    }


}