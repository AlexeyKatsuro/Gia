package com.katsiro.alexey.gia.ui.editors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.repositories.CategoryRepository
import com.katsiro.alexey.gia.utils.extensions.trigger


class CategoryEditorViewModel(
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _onSaveCategory = MutableLiveData<Unit>()
    val onSaveCategory: LiveData<Unit>
        get() = _onSaveCategory

    var category: Category = Category("",1)



    fun getAllCategories() {
        load( call = { categoryRepository.getAll()}){
            _categories.value = it
        }
    }

    fun addCategory(category: Category){
        load(call = { categoryRepository.insert(category)}){
           _onSaveCategory.trigger()
        }
    }

    fun editCategory(category: Category){
        load(call = { categoryRepository.update(category)}){
            _onSaveCategory.value = it
        }
    }
}