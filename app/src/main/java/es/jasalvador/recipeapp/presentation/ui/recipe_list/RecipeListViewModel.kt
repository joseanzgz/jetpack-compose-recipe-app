package es.jasalvador.recipeapp.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jasalvador.recipeapp.domain.model.Recipe
import es.jasalvador.recipeapp.repository.RecipeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = "avocado",
            )
            Log.d("RecipeListViewModel", "Hola")
            Log.d("RecipeListViewModel", result.count().toString())
            recipes.value = result
        }
    }
}