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
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var purchaseAdapter: DataBoundAdapter<Purchase, ItemPurchaseBinding>

    val args by navArgs<CategoryFragmentArgs>()
    val viewModel: CategoryViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.buttonAdd.setOnClickListener {
            val date = Calendar.getInstance().time
        }

        purchaseAdapter = DataBoundAdapter(R.layout.item_purchase, Purchase::id)
        binding.recyclerView.adapter = purchaseAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}