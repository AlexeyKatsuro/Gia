package com.katsiro.alexey.gia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.entities.Purchase
import com.katsiro.alexey.gia.data.repositories.LinkRepository

class CategoryViewModel(
    private val linkRepository: LinkRepository
) : BaseViewModel() {


    private val _addedPurches = MutableLiveData<Purchase>()
    val addepPurchase: LiveData<Purchase>
        get() = _addedPurches

    private val _purcheses = MutableLiveData<List<Purchase>>()
    val purcheses: LiveData<List<Purchase>>
        get() = _purcheses


    fun addLinkWithReturn(categoryId: Long, purchase: Purchase) {
        loadData(_addedPurches) {
            linkRepository.addLinkWithReturn(categoryId, purchase).also {
                _purcheses.value = (_purcheses.value ?: emptyList()) + it
            }
        }
    }

    fun getPurchasesByCategory(categoryId: Long) {
        loadData(_purcheses) {
            linkRepository.getPurchasesByCategory(categoryId)
        }
    }
}