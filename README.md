# 🕸️ lucid-abyss

[![Deploy](https://github.com/tozydev/lucid-abyss/actions/workflows/deploy.yml/badge.svg)](https://github.com/tozydev/lucid-abyss/actions/workflows/deploy.yml)
[![Website](https://img.shields.io/badge/tozydev.id.vn-376945)](https://tozydev.id.vn)

This is my personal website, built with Kotlin and Kobweb.
It serves as a portfolio and a blog where I share my thoughts on programming, technology, and other topics of interest.

## ✨ Features

- Markdown-based blog posts (content in `blog/` directory) with YAML front matter for metadata
- Responsive design with Tailwind CSS
- Static site generation for SEO and performance
- Code syntax highlighting with shiki
- Indexing and search functionality using pagefind

## 🛠️ Tech Stack

- **Language**: Kotlin (Kotlin/JS)
- **Framework**: Kobweb (built on top of Compose HTML)
- **Styling**: Tailwind CSS (configured via Webpack)
- **Build Tool**: Gradle (Kotlin DSL)
- **Deployment**: Cloudflare Workers (Static layout export)

## 📂 Project Structure

- `blog/<slug>/vi.md`: Raw Markdown content for blog posts.
- `build-logic/`: Gradle convention plugins for build logic and custom tasks.
- `dev-server-plugin/`: Custom Kobweb server plugin that serves webpacked-assets during development.
- `site/`: The main Kobweb application sources
    - `src/jsMain/kotlin/...`: Kotlin source files for pages, components,...
    - `src/jsMain/resources/`: Static assets, styles, and localization files.
    - `webpack.config.d/`: Webpack configuration snippets

## 🚀 Development

### Prerequisites

- JDK 21 or higher
- Git
- [Kobweb CLI](https://kobweb.varabyte.com/docs/getting-started/getting-kobweb) (recommended)
- Node.js 20 or Bun 1.0 or higher (optional, for Tailwind CSS code completion)

### Getting Started

1. Clone the repository:
   ```shell
   git clone https://github.com/tozydev/lucid-abyss.git
   cd lucid-abyss
    ```

2. Run the development server:
   ```shell
   kobweb run -p site
    ```

3. Open your browser and navigate to `http://localhost:8080` to see the site in action.

### Building for Production

To build the project for production, run:

```shell
kobweb export -p site -l static
```

This will generate the static site in `site/.kobweb/site/`,
which can be deployed to Cloudflare Workers or any static hosting service.

### Setup Tailwind CSS Code Completion (Optional)

1. Use `npm` or `bun` to install the TailwindCSS dependencies at the root of the project:

    ```shell
    bun add tailwindcss @tailwindcss/typography
    ```

2. Install or enable the [`Tailwind CSS`](https://plugins.jetbrains.com/plugin/15321-tailwind-css) IntelliJ plugin in
   your IDE.

## 🤝 Contributing

For now, the blog content is not open for contributions, but the site source code is!
If you want to contribute, please follow the guidelines in [AGENTS.md](AGENTS.md) and submit a pull request.

## 📄 License

This repository is licensed under two licenses:

- The source code is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
- The blog content is licensed under the Creative Commons Attribution Share Alike 4.0 International License.
  See the [blog/LICENSE](blog/LICENSE) file for details.

## 🎁 Acknowledgements

I would like to thank the following: projects, libraries, tools, and resources that made this project possible:

- [Kotlin](https://kotlinlang.org/) – My favorite programming language, providing `fun` and productivity.
- [Kobweb](https://kobweb.varabyte.com/) – The framework used to build this site.
- [Tailwind CSS](https://tailwindcss.com/) – For styling the site.
- [Shiki](https://shiki.style/) – For code syntax highlighting.
- [Pagefind](https://pagefind.app/) – For indexing and search functionality.
- [Cloudflare Workers](https://workers.cloudflare.com/) – For hosting the static site.
- [Nutito](https://github.com/googlefonts/nunito), [BeVietnamePro](https://github.com/bettergui/BeVietnamPro),
  and [JetBrains Mono](https://www.jetbrains.com/lp/mono/) – For the fonts used on the site.
- And all the libraries and tools that made this project possible!
