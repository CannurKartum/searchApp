package com.example.searchapp.search


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel : ViewModel() {

    private val tree = Tree()

    private val _words = MutableLiveData<List<String>>()
    val words: LiveData<List<String>> = _words


    init {
        listOf("car", "carpet", "java", "javascript", "internet").forEach {word ->
            tree.insertWord(word)
        }
        loadingAllWords()
    }

    fun loadingAllWords(){
        _words.value = tree.searchWord("")
    }

    fun addingWord(word: String) {
        tree.insertWord(word)
        _words.value = tree.searchWord(word)
        loadingAllWords()
    }

    fun searchWord(query: String) {
        _words.value = tree.searchWord(query)
    }
}