package com.katsiro.alexey.gia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.entities.Purchase
import com.katsiro.alexey.gia.data.repositories.PurchaseRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val purchaseRepository: PurchaseRepository
) : BaseViewModel() {

    private val _purches = MutableLiveData<Purchase>()
    val purchase: LiveData<Purchase>
        get() = _purches

    private val _addedPurches = MutableLiveData<Purchase>()
    val addepPurchase: LiveData<Purchase>
        get() = _addedPurches

    private val _allPurches = MutableLiveData<List<Purchase>>()
    val allPurchase: LiveData<List<Purchase>>
        get() = _allPurches


    fun getPurchase(id: Long) {
        loadData(_purches) {
            purchaseRepository.getPurchase(id)
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

    fun update(purchase: Purchase) {
        scope.launch {
            purchaseRepository.update(purchase)
        }
    }

}