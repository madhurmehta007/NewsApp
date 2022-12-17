package dev.redfox.newsapp.ui.home

import androidx.lifecycle.*
import dev.redfox.newsapp.models.News
import dev.redfox.newsapp.networking.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _newsResponse = MutableLiveData<Response<News>>()
    val newsResponse:LiveData<Response<News>> get() = _newsResponse

    fun getNews(category: String){
        viewModelScope.launch {
            val nResponse = repository.getLatestNews(category)
            _newsResponse.value = nResponse
        }
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}