package com.example.onlinestoreapp.presentation.catalog

import android.graphics.Paint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.base.ViewPagerCallback
import com.example.onlinestoreapp.databinding.CollapsedTextBinding
import com.example.onlinestoreapp.databinding.FragmentProductPageBinding
import com.example.onlinestoreapp.presentation.ImagePagerAdapter
import javax.inject.Inject


class ProductFragment : Fragment(R.layout.fragment_product_page) {

    private val menuHost: MenuHost by lazy { requireActivity() }
    private val binding by viewBinding(FragmentProductPageBinding::bind)
    private val viewModel by viewModels<CatalogViewModel>()
    private val args: ProductFragmentArgs by navArgs()

    @Inject
    lateinit var imagePagerAdapter: ImagePagerAdapter

    @Inject
    lateinit var characteristicsAdapter: CharacteristicsAdapter

    @Inject
    lateinit var viewPagerCallback: ViewPagerCallback

    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            val dotsImage = Array(2) { ImageView(binding.root.context) }
            dotsImage.forEach {
                it.setImageResource(
                    R.drawable.ic_page_big_default_10
                )
                imageSlider.slideDotLL.addView(it, viewPagerCallback.paramsDots())
            }
            dotsImage[0].setImageResource(R.drawable.ic_page_big_10)
            pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    dotsImage.mapIndexed { index, imageView ->
                        if (position == index) {
                            imageView.setImageResource(
                                R.drawable.ic_page_big_10
                            )
                        } else {
                            imageView.setImageResource(R.drawable.ic_page_big_default_10)
                        }
                    }
                    super.onPageSelected(position)
                }
            }
            viewModel.catalogLiveData.observe(viewLifecycleOwner) {

                val data = it.items.first { product ->
                    product.id == args.productIndex
                }
                imagePagerAdapter.submitList(data.images)
                imageSlider.viewpager2.adapter = imagePagerAdapter
                imageSlider.viewpager2.registerOnPageChangeCallback(pageChangeListener)
                stars.rating = data.feedback.rating
                characteristicsAdapter.submitList(data.info)
                recyclerViewCharacteristics.adapter = characteristicsAdapter
                textviewStars.text = getString(
                    R.string.text_stars,
                    data.feedback.rating.toString(),
                    "${data.feedback.count} ${
                        viewModel.wordDeclension(
                            data.feedback.count,
                            resources.getStringArray(R.array.review).toList()
                        )
                    }"
                )
                if (data.like) {
                    buttonLiked.setIconResource(R.drawable.ic_heart_active_24)
                } else
                    buttonLiked.setIconResource(R.drawable.ic_heart_default_24)
                buttonLiked.setOnClickListener {
                    viewModel.likeProduct(data)
                }
                textDescription.textviewDescription.text = data.description
                textViewTitle.text = data.title
                textViewOldPrice.paintFlags =
                    textViewTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textViewSubtitle.text = data.subtitle
                buttonBrand.text = data.title
                textviewTotal.text = getString(
                    R.string.available_for_order, data.available, viewModel.wordDeclension(
                        data.available,
                        resources.getStringArray(R.array.thing).toList()
                    )
                )
                textComposition.textviewDescription.text = data.ingredients
                textViewOldPrice.text = "${data.price.price} ${data.price.unit}"
                textviewPrice.text = "${data.price.priceWithDiscount} ${data.price.unit}"
                cardPrice.textViewDiscount.text =
                    getString(R.string.discount_procent, data.price.discount)
                buttonPrice.setPrice("${data.price.priceWithDiscount} ${data.price.unit}")
                buttonPrice.setOldPrice("${data.price.price} ${data.price.unit}")
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

    override fun onDestroy() {
        super.onDestroy()
        binding.imageSlider.viewpager2.unregisterOnPageChangeCallback(pageChangeListener)
    }

    private fun hideText(collapsedText: CollapsedTextBinding) {
        if (collapsedText.buttonHide.text == getString(R.string.hide)) {
            collapsedText.buttonHide.text = getString(R.string.more)
            collapsedText.textviewDescription.maxLines = 2
        } else {
            collapsedText.buttonHide.text = getString(R.string.hide)
            collapsedText.textviewDescription.maxLines = Int.MAX_VALUE
        }
    }
}