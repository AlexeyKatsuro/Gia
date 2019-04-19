package com.katsiro.alexey.gia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.katsiro.alexey.gia.R
import com.katsiro.alexey.gia.utils.extensions.observeK
import com.katsiro.alexey.gia.utils.extensions.observeValue
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getPurchaseCount()
        viewModel.getCategoriesCount()

        viewModel.categoriesCount.observeValue(this){
            Timber.d("Categories Count: $it")
        }

        viewModel.purchasesCount.observeK(this){
            Timber.d("Purchases Count: $it")
        }
    }
}
