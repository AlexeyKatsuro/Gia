package com.katsiro.alexey.gia.ui.editors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.repositories.CategoryRepository


class CategoryEditorViewModel(
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _onSaveCategory = MutableLiveData<Long>()
    val onSaveCategory: LiveData<Long>
        get() = _onSaveCategory

    var category: Category = Category("",1)



    fun getAllCategories() {
        load( call = { categoryRepository.getAllCategories()}){
            _categories.value = it
        }
    }

    fun addCategory(category: Category){
        load(call = { categoryRepository.add(category)}){
           _onSaveCategory.value = it
        }
    }

    fun editCategory(category: Category){
        load(call = { categoryRepository.update(category)}){
            _onSaveCategory.value = it
        }
    }
}