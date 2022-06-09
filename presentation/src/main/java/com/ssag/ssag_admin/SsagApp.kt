package com.ssag.ssag_admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ssag.ssag_admin.feature.changepassword.ChangePassword
import com.ssag.ssag_admin.feature.clean.CheckClean
import com.ssag.ssag_admin.feature.login.Login
import com.ssag.ssag_admin.ui.navigation.AppNavigationItem

@Composable
fun SsagApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigationItem.CheckClean.route) {
        composable(AppNavigationItem.Login.route) {
            Login(navController = navController)
        }

        composable(AppNavigationItem.ChangePassword.route) {
            ChangePassword(navController = navController)
        }

        composable(
            AppNavigationItem.CheckClean.route)
         {
//            val isManTeacher = it.arguments!!.getBoolean("isManTeacher")
            CheckClean(navController = navController, isManTeacher = true)
        }
    }
}