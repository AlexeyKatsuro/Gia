package com.katsiro.alexey.gia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.katsiro.alexey.gia.data.entities.Purchase
import com.katsiro.alexey.gia.databinding.FragmentMainBinding
import com.katsiro.alexey.gia.utils.extensions.observeValue
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber
import java.util.*


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    val viewModel: MainViewModel by sharedViewModel(from = { parentFragment as ViewModelStoreOwner })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val date = Calendar.getInstance().time
            val purchase = Purchase((2..20).random().toFloat(), date, "Click ${(2..20).random()}")
            viewModel.addWithReturn(purchase)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allPurchase.observeValue(viewLifecycleOwner) {
            Timber.d("$it")
        }

        viewModel.addepPurchase.observeValue(viewLifecycleOwner) {
            Timber.d("$it")
        }
    }

}