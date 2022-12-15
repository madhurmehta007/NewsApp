package dev.redfox.newsapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsDBViewModel(private val repository: NewsRepository):ViewModel() {
    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob+Dispatchers.Main)

    val allNewsLists:LiveData<MutableList<News>> = repository.allNewsLists

    fun insertNews(news: News) = scope.launch(Dispatchers.IO) {
        repository.insertNews(news)
    }

    fun deleteNews(news: News) = scope.launch(Dispatchers.IO) {
        repository.deleteNews(news)
    }

    fun updateNews(news: News) = scope.launch(Dispatchers.IO) {
        repository.deleteNews(news)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}

class NewsDBViewModelFactory(val repository: NewsRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return NewsDBViewModel(repository) as T
    }
}