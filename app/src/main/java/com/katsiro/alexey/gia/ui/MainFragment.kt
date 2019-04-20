package com.katsiro.alexey.gia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.emptyDataSourceTyped
import com.afollestad.recyclical.setup
import com.katsiro.alexey.gia.R
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.databinding.FragmentMainBinding
import com.katsiro.alexey.gia.ui.common.holders.CategoryHolder
import com.katsiro.alexey.gia.utils.extensions.observeValue
import com.katsiro.alexey.gia.utils.extensions.onBindingBind
import com.katsiro.alexey.gia.utils.extensions.withData
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    val viewModel: CategoryViewModel by viewModel()
    val dataSource: DataSource<Category> = emptyDataSourceTyped()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            buttonAdd.setOnClickListener {
                val direction = MainFragmentDirections.showCategoryEditorFragment()
                findNavController().navigate(direction)
            }

            recyclerView.setup {
                withDataSource(dataSource)
                withData<Category>(R.layout.item_category){
                    onBindingBind(::CategoryHolder)

                    onClick {
                        val direction = MainFragmentDirections.showCategoryEditorFragment(item)
                        findNavController().navigate(direction)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCategories()
        viewModel.categories.observeValue(viewLifecycleOwner) {
            dataSource.set(it, areTheSame = ::areItemsTheSame)
        }
    }

    private fun areItemsTheSame(left: Any, right: Any): Boolean {
        return when (left) {
            is Category -> {
                right is Category && right.id == left.id
            }
            else -> false
        }
    }

}