package com.katsiro.alexey.gia.ui.editors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.katsiro.alexey.gia.data.EditorMode
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.databinding.FragmentCategoryEditorBinding
import com.katsiro.alexey.gia.utils.extensions.lazyFast

class CategoryEditorFragment : Fragment(){

    private val args by navArgs<CategoryEditorFragmentArgs>()
    private val category: Category? by lazyFast { args.category }
    private val editorMode: EditorMode
        get() = if (category==null) EditorMode.Create else EditorMode.Edit

    private lateinit var binding: FragmentCategoryEditorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryEditorBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            mode = editorMode

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        return binding.root
    }
}