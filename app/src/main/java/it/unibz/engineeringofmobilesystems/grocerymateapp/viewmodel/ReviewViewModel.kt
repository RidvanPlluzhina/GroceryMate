package it.unibz.engineeringofmobilesystems.grocerymateapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.Review
import it.unibz.engineeringofmobilesystems.grocerymateapp.model.data.ReviewDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ReviewViewModel manages the state and operations for reviews
class ReviewViewModel(private val dao: ReviewDao) : ViewModel() {

    // Private mutable state flow holding the list of reviews
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    // Public immutable state flow exposing the list of reviews to observers
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    // Initialization block called when the ViewModel is created
    init {
        getAllReviews() // Fetches all reviews from the database
    }

    // Retrieves all reviews from the database and updates the state flow
    private fun getAllReviews() {
        viewModelScope.launch { // Launches a coroutine tied to the ViewModel lifecycle
            dao.getAllReviews().collect {  // Collects flow from DAO
                _reviews.value = it // Updates the state flow with the retrieved reviews
            }
        }
    }
    // Adds a new review to the database
    fun addReview(review: Review) {
        viewModelScope.launch {
            dao.upsert(review) // Inserts or updates the review in the database
        }
    }

    fun deleteReview(review: Review) {   // Deletes a review from the database
        viewModelScope.launch {
            dao.delete(review) // Removes the review from the database
        }
    }
}
