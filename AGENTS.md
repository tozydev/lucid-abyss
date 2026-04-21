# Project Development Guidelines

The **Lucid Abyss** project is a personal blog and portfolio site.
This project using **Kotlin Multiplatform** (KMP) and **Kobweb**.

**Role:** Kotlin Multiplatform (KMP) and Kobweb expert developer.

## Tech Stack

- **Language:** Kotlin JS 2.3.20.
- **Framework:** Kobweb 0.24.0 (based on JetBrains Compose HTML).
- **Styling:** Tailwind CSS 4.x (configured via Webpack) and a custom design system (based on Material Design 3 tokens).
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
    - `src/jsMain/kotlin/.../components/pages/`: Reusable layouts for pages.
    - `src/jsMain/kotlin/.../components/sections/`: Reusable UI components for sections (e.g., header, footer, sidebar).
    - `src/jsMain/kotlin/.../components/widgets/`: Low-level UI elements use across the site (e.g., buttons, cards).
    - `src/jsMain/resources/public/`: Static assets (Images, Fonts, etc.).
    - `src/jsMain/resources/styles.css`: Tailwind CSS 4 configuration.
    - `src/jsMain/resources/strings/`: Localization files (`vi.yaml`).

- `blog/`: Raw Markdown content for blog posts (DO NOT TOUCH).
- `build-logic/`: Gradle convention plugins (e.g., blog post-processing, string type-safe generation).

## Notable Commands

- Compile the project (quick check): `./gradlew site:jsDevelopmentExecutableCompileSync`
- Full project build: `./gradlew build`
- Export static site to `site/.kobweb/site/`: `kobweb export -p site -l static --notty`
- Start development server: `kobweb run -p site --notty`
  _*This command listens on port 8080 by default, is a blocking command, and requires about 1–2 minutes to start._
- Format code (`*.kt`, `*.kts`): `./gradlew spotlessApply`

## Boundaries & Rules

- **NEVER** modify `blog` content.
- **ALWAYS** update `vi.yaml` when adding new text.
- **ALWAYS** run `./gradlew build` after making significant changes.
- **ALWAYS** run `./gradlew spotlessApply` after finalizing code changes.
- **ASK FIRST** before creating new Gradle modules or changing `build-logic`.
- **ASK FIRST** before adding new dependencies.
- **ASK FIRST** before modifying `dev-server-plugin/` or the custom tasks in `site/build.gradle.kts` (e.g. `transformSiteHtml`, `copyProductionWebpackAssets`, `pagefindIndex`, `cleanupDist`). These tasks influence the exported output and deployment pipeline.
- **ALWAYS** define dependencies in `gradle/libs.versions.toml` or `gradle/npm.versions.toml`.
- **NEVER** hardcode versions in `build.gradle.kts`.
- **PREFER** using `{ }` attrs block over `Modifier.toAttrs()` for Compose HTML composables.

## Common Workflows (UI Development)

1. Take the request and determine the user intent.
2. Load relevant files, documentation, or memories to understand the context.
3. Design the UI based on the user intent and context.
4. Implement the UI using Kobweb and Tailwind CSS.
5. Export the static site to `site/.kobweb/site/` and verify the UI.
6. Show the screenshot if applicable.

## Coding Standards & Patterns

### 1\. Styling (Tailwind)

**ALWAYS** use the custom `tw` utility for styling.
**NEVER** use standard CSS classes or inline styles unless absolutely necessary.

- **Good:**
  ```kotlin
  Div({ tw("m-4 text-lg bg-primary-container") }) { /* ... */ } // When use without Modifier.
  // OR
  Div(Modifier.tw("m-4 text-lg bg-primary-container").then(modifier).toAttrs()) { /* ... */ } // When use or mix with Modifier.
  ```

- **Bad:**
  ```kotlin
  Div({ classes("m-4", "text-lg") }) { /* ... */ }
  // OR
  Div(Modifier.tw("m-4 text-lg").toAttrs()) { /* ... */ } // Only use `tw` attr, don't need to use Modifier and convert to Attrs.
  ```

### 2\. Internationalization

**NEVER** hardcode UI strings.
**ALWAYS** use `Strings`.

- **Bad:** `Text("Home Page")`
- **Good:** `Text(Strings.home_page)`

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
    Div(Modifier.tw("m-4 text-lg bg-primary-container").then(modifier).toAttrs()) {

    }
}
```
