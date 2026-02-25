# Project Development Guidelines

The **Lucid Abyss** project is a personal blog and portfolio site.
This project using **Kotlin Multiplatform** (KMP) and **Kobweb**.

**Role:** Kotlin Multiplatform (KMP) and Kobweb expert developer.

## Tech Stack

- **Language:** Kotlin 2.3.0 (JVM & JS targets).
- **Framework:** Kobweb 0.23.3 (based on JetBrains Compose HTML).
- **Styling:** Tailwind CSS 4.x (configured via PostCSS) and DaisyUI 5.x (Component library).
- **Build Tool:** Gradle (Kotlin DSL).
- **Internationalization:** YAML localization files (default: Vietnamese `vi`)
- **Deployment:** Cloudflare Workers (Static layout export).

## Git Commits & Branches

- **Commits:**
    - Use **Conventional Commits** (e.g., `feat: add navbar`, `fix: styling bug`, `chore: update dependencies`).
    - Commit messages should be short and descriptive.
- **Branches:** Use descriptive names like `feature/new-page` or `fix/nav-issue`.

## Key Files & Directories

- `site/`: The main Kobweb application.
    - `src/jsMain/kotlin/.../pages/`: Page entry points.
    - `src/jsMain/kotlin/.../components/`: Reusable UI components.
    - `src/jsMain/kotlin/.../layouts/`: Layouts for pages.
    - `src/jsMain/resources/public/`: Static assets (Images, Fonts, etc).
    - `src/jsMain/resources/styles.css`: Tailwind CSS configuration.
    - `src/jsMain/resources/strings/`: Localization files (`en.yaml`, `vi.yaml`).

- `blog/`: Raw Markdown content for blog posts (DO NOT TOUCH).
- `build-logic/`: Gradle convention plugins (e.g blog post processing).
- `core/`: Shared logic and models (`BlogPost` data class, `SiteLanguage` enum).

## Notable Commands

- Compile the project (quick check): `./gradlew site:jsDevelopmentExecutableCompileSync`
- Full project build: `./gradlew build`
- Export static site to `site/.kobweb/site/`: `kobweb export -p site -l static --notty`
- Start development server: `kobweb run -p site --notty`
  _*This command listens on port 8080 by default, is a blocking command, and requires about 1–2 minutes to start._
- Format code (`*.kt`, `*.kts`): `./gradlew spotlessApply`

## Boundaries & Rules

- **NEVER** modify `blog` content except for mocking up new posts.
- **ALWAYS** update `en.yaml` and `vi.yaml` when adding new text.
- **ALWAYS** run `./gradlew build` after making significant changes.
- **ALWAYS** run `./gradlew spotlessApply` after finalizing code changes.
- **ASK FIRST** before creating new Gradle modules or changing `build-logic`.
- **ASK FIRST** before adding new dependencies.
    - **ALWAYS** define dependencies in `gradle/libs.versions.toml` or `gradle/npm.versions.toml`.
    - **NEVER** hardcode versions in `build.gradle.kts`.
- **PREFER** using `{ }` attrs block over `Modifier.toAttrs()` for Compose HTML composables.
- Refer to [DaisyUI llms.txt](https://daisyui.com/llms.txt) when working with DaisyUI.

## Coding Standards & Patterns

### 1\. Styling (Tailwind \+ DaisyUI)

**ALWAYS** use the custom `tw` utility for styling.
**NEVER** use standard CSS classes or inline styles unless absolutely necessary.

- **Good:**
  ```kotlin
  Div({ tw("card bg-base-100") }) { /* ... */ }
  // OR
  Div(Modifier.tw("card bg-base-100 shadow-xl").then(modifier).toAttrs()) { /* ... */ }
  ```

- **Bad:**
  ```kotlin
  Div(attrs = { classes("card", "bg-base-100") }) { /* ... */ }
  ```

### 2\. Internationalization

**NEVER** hardcode UI strings.
**ALWAYS** use `Res.string`.

- **Bad:** `Text("Home Page")`
- **Good:** `Text(Res.string.home_page)`

### 3\. Navigation

Prefer `Anchor` over `A` for navigation links.

- **Good:** `Anchor(href = "...", attrs = { ... }) { ... }`
- **Bad:** `A(...)`

### 4\. UI Component

Create a new component for reusable UI elements. New components should have:

- Always add `modifier: Modifier = Modifier` to parameter.
- Apply root modifiers using `.then(modifier)`.

```kotlin
// Example:
@Composable
fun MyComponent(modifier: Modifier = Modifier) {
    Div(Modifier.tw("card bg-base-100 shadow-xl").then(modifier).toAttrs()) {

    }
}
```
