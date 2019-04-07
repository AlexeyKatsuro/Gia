package com.katsiro.alexey.gia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.entities.Purchase
import com.katsiro.alexey.gia.data.repositories.CategoryRepository
import com.katsiro.alexey.gia.data.repositories.PurchaseRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val purchaseRepository: PurchaseRepository,
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    private val _purchas = MutableLiveData<Purchase>()
    val purchases: LiveData<Purchase>
        get() = _purchas

    private val _addedPurches = MutableLiveData<Purchase>()
    val addepPurchase: LiveData<Purchase>
        get() = _addedPurches

    private val _allPurches = MutableLiveData<List<Purchase>>()
    val allPurchase: LiveData<List<Purchase>>
        get() = _allPurches

    private val _addedCategory = MutableLiveData<Category>()
    val addepCategory: LiveData<Category>
        get() = _addedCategory

    private val _allCategories = MutableLiveData<List<Category>>()
    val allCategories: LiveData<List<Category>>
        get() = _allCategories


    fun getPurchase(id: Long) {
        loadData(_purchas) {
            purchaseRepository.getPurchase(id)
        }
    }

    fun getAllCategories() {
        loadData(_allCategories) {
            categoryRepository.getAllCategories()
        }
    }

    fun getAllPurchases() {
        loadData(_allPurches) {
            purchaseRepository.getAllPurchases()
        }
    }

    fun add(purchase: Purchase) {
        scope.launch {
            purchaseRepository.add(purchase)
        }
    }

    fun addWithReturn(purchase: Purchase) {
        loadData(_addedPurches) {
            purchaseRepository.insertWithReturn(purchase)
        }
    }

    fun addWithReturn(category: Category) {
        loadData(_addedCategory) {
            categoryRepository.insertWithReturn(category).also {
                _allCategories.value = (_allCategories.value ?: emptyList()) + it
            }
        }
    }

    fun update(purchase: Purchase) {
        scope.launch {
            purchaseRepository.update(purchase)
        }
    }

}