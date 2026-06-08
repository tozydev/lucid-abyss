import {type CodeToHastOptions, createHighlighter} from "shiki";
import * as nodePath from "node:path";


const highlighter = await createHighlighter({
    langs: ["kotlin"],
    themes: ["one-light", "one-dark-pro"],
})

const highlightOptions = (lang: string): CodeToHastOptions => {
    return {
        lang: lang,
        themes: {
            light: "one-light",
            dark: "one-dark-pro",
        },
        defaultColor: false,
        colorReplacements: {
            "#fafafa": "var(--color-surface-container-high)",
            "#282c34": "var(--color-surface-container-high)",
        }
    }
}

const resolvePath = (path: string) => {
    const cwd = process.cwd()
    return cwd.endsWith("utils") ? nodePath.resolve(`../${path}`) : nodePath.resolve(`./${path}`);
}

const homeHeroCodeSnippets =
    // @formatter:off
    // language=kotlin
    `
val tozydev = developer {
  about {
    name = "Thanh Tân"
    username = "tozydev"
    role = Kotlin_Developer
  }
  technicalSkills {
    languages = setOf("Kotlin", "Java", "TypeScript")
    frameworks = setOf("Ktor", "Spring Boot", "Kobweb")
  }
  tools {
    ide = setOf("IntelliJ IDEA")
    codeEditor = setOf("VS Code")
    ai = setOf("Gemini", "GitHub Copilot")
  }
}`.trimStart()
// @formatter:on

const homeHeroCodeSnippetsOutputPath = "site/src/jsMain/resources/sections/HomeHeroCodeSnippets.md"

await Bun.write(
    Bun.file(resolvePath(homeHeroCodeSnippetsOutputPath)),
    `<!-- THIS IS A GENERATED FILE. PLEASE DO NOT EDIT THIS MANUALLY -->
${highlighter.codeToHtml(homeHeroCodeSnippets, highlightOptions("kotlin"))}`
)
