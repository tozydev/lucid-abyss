package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.core.init.InitKobweb

private const val STYLES_FILE = "./styles.css"

@InitKobweb
fun initTailwind() {
    js("""require("$STYLES_FILE")""")
}
