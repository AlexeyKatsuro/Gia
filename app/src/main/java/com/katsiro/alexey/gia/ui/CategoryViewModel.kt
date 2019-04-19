package com.katsiro.alexey.gia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.repositories.CategoryRepository

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    fun getAllCategories(){
        loadData(_categories){
            categoryRepository.getAllCategories()
        }
    }
}