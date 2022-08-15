package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class MainActivityViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    private val _addedShoe = MutableLiveData<Boolean>()
    val addedShoe: LiveData<Boolean>
        get() = _addedShoe

    init {
        var shoeListInit = arrayListOf(
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("W957", 44.0, "Adidas", "Comfortable Wide shoes"),
            Shoe("F123", 42.0, "Nike", "Comfortable flexible shoes"),
            Shoe("S515", 40.0, "Nike", "Comfortable sports shoes"),
            Shoe("F123", 40.0, "Macron", "Comfortable flat shoes"),
            Shoe("F123", 39.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 48.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("W957", 44.0, "Adidas", "Comfortable Wide shoes"),
            Shoe("F123", 42.0, "Nike", "Comfortable flexible shoes"),
            Shoe("S515", 40.0, "Nike", "Comfortable sports shoes"),
            Shoe("F123", 40.0, "Macron", "Comfortable flat shoes"),
            Shoe("F123", 39.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 48.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("W957", 44.0, "Adidas", "Comfortable Wide shoes"),
            Shoe("F123", 42.0, "Nike", "Comfortable flexible shoes"),
            Shoe("S515", 40.0, "Nike", "Comfortable sports shoes"),
            Shoe("F123", 40.0, "Macron", "Comfortable flat shoes"),
            Shoe("F123", 39.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 48.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("W957", 44.0, "Adidas", "Comfortable Wide shoes"),
            Shoe("F123", 42.0, "Nike", "Comfortable flexible shoes"),
            Shoe("S515", 40.0, "Nike", "Comfortable sports shoes"),
            Shoe("F123", 40.0, "Macron", "Comfortable flat shoes"),
            Shoe("F123", 39.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 40.0, "Nike", "Comfortable flat shoes"),
            Shoe("F123", 48.0, "Nike", "Comfortable flat shoes"),
        )
        _shoeList.value = shoeListInit
    }

    fun getShoeList(): List<Shoe>? {
        return _shoeList.value
    }

    fun addNewShoe(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)
        _addedShoe.value = true
    }
    fun finishAdding(){
        _addedShoe.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("cleared")
    }
}