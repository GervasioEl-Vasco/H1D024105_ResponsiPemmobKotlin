package com.example.responsipemmobkotlin.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.responsipemmobkotlin.ui.screen.DetailScreen
import com.example.responsipemmobkotlin.ui.screen.HomeScreen
import com.example.responsipemmobkotlin.viewmodel.BookViewModel

object AppRoutes {
    const val HOME = "home"
    const val DETAIL = "detail"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    bookViewModel: BookViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(route = AppRoutes.HOME) {
            HomeScreen(
                onBookClick = { selectedBook ->
                    bookViewModel.selectBook(selectedBook)
                    navController.navigate(AppRoutes.DETAIL)
                },
                viewModel = bookViewModel
            )
        }

        composable(route = AppRoutes.DETAIL) {
            val book = bookViewModel.selectedBook.value
            if (book != null) {
                DetailScreen(
                    book = book,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
