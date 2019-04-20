package com.katsiro.alexey.gia.ui.picker

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelStoreOwner
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.katsiro.alexey.gia.R
import com.katsiro.alexey.gia.data.Picked
import com.katsiro.alexey.gia.databinding.DialogPickItemBinding
import com.katsiro.alexey.gia.ui.common.holders.TextViewHolder
import com.katsiro.alexey.gia.utils.extensions.lazyArg
import com.katsiro.alexey.gia.utils.extensions.withData
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.Serializable

class TextPickDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_LAYOUT = "arg_layout"
        private const val ARG_ITEMS = "arg_items"

        fun <T : Parcelable> newInstance(
            items: Iterable<T>,
            title: String = "", @LayoutRes layout: Int = R.layout.item_text,
            mapper: (T) -> String
        ): TextPickDialogFragment {
            val map: Map<T, String> = items.associateWith(mapper)

            val bundle = bundleOf(
                ARG_TITLE to title,
                ARG_LAYOUT to layout,
                ARG_ITEMS to map
            )
            return TextPickDialogFragment().apply {
                arguments = bundle
            }
        }

        fun <T : Serializable> newInstanceS(
            items: Iterable<T>,
            title: String = "", @LayoutRes layout: Int = R.layout.item_text,
            mapper: (T) -> String
        ): TextPickDialogFragment {
            val map: Map<T, String> = items.associateWith(mapper)

            val bundle = bundleOf(
                ARG_TITLE to title,
                ARG_LAYOUT to layout,
                ARG_ITEMS to map
            )
            return TextPickDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    val viewModel: TextPickDialogViewModel by sharedViewModel(from = { targetFragment as ViewModelStoreOwner })

    private val title: String by lazyArg {
        it.getString(ARG_TITLE)
    }

    private val itemLayout: Int by lazyArg {
        it.getInt(ARG_LAYOUT)
    }

    private val itemsMap: Map<Any, String> by lazyArg {
        @Suppress("UNCHECKED_CAST")
        it.getSerializable(ARG_ITEMS) as Map<Any, String>
    }

    private lateinit var binding: DialogPickItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DialogPickItemBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            title = this@TextPickDialogFragment.title
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setup {
            withDataSource(dataSourceTypedOf(itemsMap.toList()))
            withData<Pair<Any, String>>(R.layout.item_text) {
                onBind(::TextViewHolder) { index, item -> bind(index, item.second) }
                onClick {
                    viewModel.onItemClick(Picked(targetRequestCode, item.first, item.second))
                    dismiss()
                }
            }
        }

    }
}
