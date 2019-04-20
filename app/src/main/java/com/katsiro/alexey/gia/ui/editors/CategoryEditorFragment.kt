package com.katsiro.alexey.gia.ui.editors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.katsiro.alexey.gia.R
import com.katsiro.alexey.gia.data.EditorMode
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.databinding.FragmentCategoryEditorBinding
import com.katsiro.alexey.gia.ui.picker.TextPickDialogFragment
import com.katsiro.alexey.gia.ui.picker.TextPickDialogViewModel
import com.katsiro.alexey.gia.utils.extensions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryEditorFragment : Fragment() {

    companion object {
        const val REQUEST_CATEGORY = 0
    }

    private val args by navArgs<CategoryEditorFragmentArgs>()
    private val category: Category? by lazyFast { args.category }
    private val editorMode: EditorMode
        get() = if (category == null) EditorMode.Create else EditorMode.Edit

    private lateinit var binding: FragmentCategoryEditorBinding

    private val pickViewModel: TextPickDialogViewModel by viewModel()
    private val viewModel: CategoryEditorViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryEditorBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            mode = editorMode
            category = this@CategoryEditorFragment.category

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            buttonSave.setOnClickListener {
                val category = Category(
                    textName.text,
                    viewModel.category.parentId
                )
                val action = when (editorMode) {
                    EditorMode.Create -> viewModel::addCategory
                    EditorMode.Edit -> viewModel::editCategory
                }
                action.invoke(category)
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCategories()

        viewModel.categories.observeValue(viewLifecycleOwner) { categories ->
            binding.textParent.text = categories.find { it.parentId == -1L }?.name ?: ""

            val listener = View.OnClickListener {
                showPickDialog(categories)
            }
            binding.textParent.editText?.setOnClickListener(listener)
            binding.textParent.setEndIconOnClickListener(listener)
        }

        pickViewModel.onItemClicked.observeEvent(viewLifecycleOwner) {
            when (it.requestCode) {
                REQUEST_CATEGORY -> {
                    val item = it as Category
                    binding.textParent.text = item.name
                    viewModel.category.parentId = item.id
                }
            }
        }

        viewModel.onSaveCategory.observeValue(viewLifecycleOwner) {
            toast(R.string.editor_category_saved)
            findNavController().navigateUp()
        }
    }


    private fun showPickDialog(categories: List<Category>) {
        val dialog = TextPickDialogFragment
            .newInstance(categories, getString(R.string.editor_category_parent_hint)) { it.name }
        dialog.setTargetFragment(this, REQUEST_CATEGORY)
        dialog.show(requireFragmentManager(), "pick_category")
    }
}