package chat.site.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import chat.auth.model.auth.LoginState
import chat.chat.components.layouts.MainLayout
import chat.chat.utils.LocalNavigator
import chat.core.components.sections.CenteredColumnContent
import chat.core.components.widgets.TextButton
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext

import chat.chat.utils.Navigator
import chat.chat.utils.Route
import kotlinx.browser.window

@Page("404")
@Composable
fun FallbackPage() {
    HomePage()
}

@Page
@Composable
fun HomePage () {

    val directToIShowcase = true
    val scope = rememberCoroutineScope()
    val context = rememberPageContext()
    var isLoading by remember { mutableStateOf(true) }

    console.info( "HomePage Begin in Site" )

    if (LoginState.current is LoginState.LoggedIn) {

        if (!::navigator.isInitialized) {
            val route = Route.valueOf(context.route.pathQueryAndFragment)
            navigator = Navigator(initialRoute = route ?: Route.Home)
            if (route == null) window.history.replaceState(null, "", Route.Home.path)
        }

        CompositionLocalProvider(LocalNavigator provides navigator) {
            MainLayout()
        }
    } else {

        val ctx = rememberPageContext()
        CenteredColumnContent {

            TextButton("Create Account") { ctx.router.navigateTo("/account/create") }
            TextButton("Login") { ctx.router.navigateTo("/account/login") }
        }
    }
}

private lateinit var navigator: Navigator

