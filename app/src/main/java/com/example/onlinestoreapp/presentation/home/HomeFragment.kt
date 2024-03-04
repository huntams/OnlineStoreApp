package com.example.onlinestoreapp.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.appComponent
import com.example.onlinestoreapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home),WheelOfFortuneView.OnSegmentChangeListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wheel.setOnSegmentChangeListener(this)
    }

    // Implement the onSegmentChange method
    override fun onSegmentChange(segmentIndex: Int) {
        // Update the TextView with the information about the segment
        binding.textViewData.text = "Segment: $segmentIndex"
    }
}
