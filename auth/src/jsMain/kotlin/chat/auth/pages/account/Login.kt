package chat.auth.pages.account

import androidx.compose.runtime.*
import chat.auth.model.auth.Account
import chat.auth.model.auth.LoginResponse
import chat.auth.model.auth.LoginState
import chat.core.components.layouts.PageLayoutData
import chat.core.components.sections.CenteredColumnContent
import chat.core.components.sections.TitleStyle
import chat.core.components.sections.TitleStyle1
import chat.core.components.widgets.TextButton
import chat.core.components.widgets.TitledTextInput
import chat.core.styles.ErrorTextStyle
import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.navigation.UpdateHistoryMode
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import kotlinx.browser.window
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initLoginPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Login"))
}

@Page
// @Layout("chat.core.components.layouts.PageLayout")
@Composable
fun LoginPage(ctx: PageContext) {
    console.info("info : Begin LoginPage")
    Column( modifier = Modifier
        .padding(left=20.px)
        ) {
        CenteredColumnContent {
            val coroutine = rememberCoroutineScope()
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var errorText by remember { mutableStateOf("") }

            errorText = when {
                username.any { it.isWhitespace() } -> "Username cannot contain whitespace."
                password.any { it.isWhitespace() } -> "Password cannot contain whitespace."
                else -> errorText
            }

            fun isValid() = username.isNotEmpty() && username.none { it.isWhitespace() }
                    && password.isNotEmpty() && password.none { it.isWhitespace() }

            fun tryLogin() {
                if (!isValid()) return

                val account = Account(username, password)
                val accountBytes = Json.encodeToString(account).encodeToByteArray()
                coroutine.launch {
                    val response = window.api.post("/account/login", body = accountBytes)
                        .decodeToString()
                        .let { Json.decodeFromString(LoginResponse.serializer(), it) }

                    if (response.succeeded) {
                        LoginState.current = LoginState.LoggedIn(account)
                        ctx.router.navigateTo("/chat", UpdateHistoryMode.REPLACE)
                        //ctx.router.navigateTo("/HomeScreenPage")

                    } else {
                        errorText = "Login failed. Invalid username / password?"
                    }
                }
            }

            var display by remember { mutableStateOf(true) }

            /*Image(
                modifier = Modifier
                    .height(110.px)
                    .align(Alignment.Start)
                    .padding(topBottom = 4.px),
                //.clickable(onClick = onLogoClick),
                src = Asset.Icon.LOGO_SHOWCASE_1,
            )*/

     /*       Box(TitleStyle1.toModifier().align(Alignment.Start)) {
                Text("Online Trading Platform \ud83c\udf3a")
            }*/
            Box(modifier = Modifier.height(30.px))

                Column(modifier = Modifier.fillMaxWidth()) {
                    /*Box(
                        modifier = Modifier
                            .border(1.px, LineStyle.Solid, color = Colors.SkyBlue)
                            .borderRadius(15.43.px)
                            .fillMaxWidth(470.px)
                            .padding(leftRight = 24.px, topBottom = 20.px)
                            .whiteSpace(WhiteSpace.PreLine)
                    ) {*/
                        Column() {
                            Box(modifier = Modifier.height(30.px))
                            Column(modifier = Modifier.padding(left = 10.px)) {
                                TitledTextInput(
                                    "Username",
                                    username,
                                    { errorText = ""; username = it },
                                    ref = ref { it.focus() },
                                    onCommit = ::tryLogin
                                )
                            }
                            Column(modifier = Modifier.padding(left = 10.px)) {
                                Box(modifier = Modifier.height(8.px))
                                TitledTextInput(
                                    "Password",
                                    password,
                                    { errorText = ""; password = it },
                                    masked = true,
                                    onCommit = ::tryLogin
                                )
                            }
                            Box(modifier = Modifier.height(14.px))
                            TextButton("Login", enabled = isValid(), onClick = ::tryLogin)
                            Box(modifier = Modifier.height(8.px))

                            if (errorText.isNotBlank()) {
                                Column(modifier = Modifier.padding(left = 10.px)) {
                                    SpanText(errorText, ErrorTextStyle.toModifier())
                                }
                            }
                            Box(modifier = Modifier.height(20.px))
                        }

                }
        }
    }
}
