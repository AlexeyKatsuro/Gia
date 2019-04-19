package com.katsiro.alexey.gia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katsiro.alexey.gia.R
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.databinding.FragmentMainBinding
import com.katsiro.alexey.gia.databinding.ItemCategoryBinding
import com.katsiro.alexey.gia.ui.common.adapters.DataBoundAdapter
import com.katsiro.alexey.gia.utils.extensions.observeValue
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var categoryAdapter: DataBoundAdapter<Category, ItemCategoryBinding>
    val viewModel: CategoryViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        categoryAdapter = DataBoundAdapter(R.layout.item_category,Category::id)
        binding.recyclerView.adapter = categoryAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCategories()
        viewModel.categories.observeValue(viewLifecycleOwner){
            categoryAdapter.submitList(it)
        }
    }

}