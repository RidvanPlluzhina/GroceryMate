package it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.ReviewDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReviewViewModel(private val dao: ReviewDao) : ViewModel() {

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    init {
        getAllReviews()
    }

    private fun getAllReviews() {
        viewModelScope.launch {
            dao.getAllReviews().collect {
                _reviews.value = it
            }
        }
    }

    fun addReview(review: Review) {
        viewModelScope.launch {
            dao.upsert(review)
        }
    }

    fun deleteReview(review: Review) {
        viewModelScope.launch {
            dao.delete(review)
        }
    }
}
