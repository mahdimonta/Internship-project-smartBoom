package org.example.project.navigation



sealed class Routes(val route: String) {
    data object Splash : Routes("splash")
    data object LoginOrSignUp : Routes("loginOrSignUp")
    data object Login : Routes("login")
    data object SignUp : Routes("signUp")
    data object ForgetPass : Routes("forgetPass")

    data object Verification : Routes("verification")
}