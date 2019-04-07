package com.katsiro.alexey.gia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.katsiro.alexey.gia.R
import com.katsiro.alexey.gia.data.entities.Purchase
import com.katsiro.alexey.gia.databinding.FragmentMainBinding
import com.katsiro.alexey.gia.databinding.ItemPurchaseBinding
import com.katsiro.alexey.gia.ui.common.adapters.DataBoundAdapter
import com.katsiro.alexey.gia.utils.extensions.string
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var purchaseAdapter: DataBoundAdapter<Purchase, ItemPurchaseBinding>

    val args by navArgs<CategoryFragmentArgs>()
    val viewModel: CategoryViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val date = Calendar.getInstance().time
            val purchase = Purchase(10f, date, binding.text.string)
            viewModel.addLinkWithReturn(args.categoryId, purchase)
        }

        purchaseAdapter = DataBoundAdapter(R.layout.item_purchase, Purchase::id)
        binding.categoryRecyclerView.adapter = purchaseAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPurchasesByCategory(args.categoryId)
        viewModel.purcheses.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            purchaseAdapter.submitList(it)
        })

    }

}