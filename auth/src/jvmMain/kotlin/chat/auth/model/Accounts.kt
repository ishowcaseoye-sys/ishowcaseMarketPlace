package chat.auth.model

import chat.auth.model.auth.Account
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import java.lang.System.console

@InitApi
fun initAccounts(ctx: InitApiContext) {
    println(  "AXA - initAccounts() InitApi Begin" )
    ctx.data.add(Accounts())
    println(  "AXA - initAccounts() InitApi " )

}

class Accounts {





    val set = mutableSetOf<Account>()
}

