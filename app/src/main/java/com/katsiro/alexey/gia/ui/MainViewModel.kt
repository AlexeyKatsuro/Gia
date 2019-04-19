package com.katsiro.alexey.gia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.entities.Purchase
import com.katsiro.alexey.gia.data.repositories.CategoryRepository
import com.katsiro.alexey.gia.data.repositories.PurchaseRepository

class MainViewModel(
    private val purchaseRepository: PurchaseRepository,
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    private val _purchasesCount = MutableLiveData<Int>()
    val purchasesCount: LiveData<Int>
        get() = _purchasesCount

    private val _categoriesCount = MutableLiveData<Int>()
    val categoriesCount: LiveData<Int>
        get() = _categoriesCount

    fun getCategoriesCount(){
        loadData(_categoriesCount){
            categoryRepository.getCount()
        }
    }

    fun getPurchaseCount(){
        loadData(_purchasesCount){
            purchaseRepository.getCount()
        }
    }
}