# Tools CLI Development Guidelines

This directory contains the **Tools CLI** subproject, a Bun-based utility package used by the parent Gradle project to
do some utility work like syntax highlighting.

## Project Overview

- **Runtime**: Bun (v1.3.14+)
- **Language**: TypeScript (targeting Bun runtime)
- **CLI Framework**: CAC
- **Syntax Highlighting**: Shiki

## Setup & Development Commands

- Install dependencies: `bun install`
- Run local development CLI: `bun run src/index.ts <command>`
- Build bytecode-compiled executable: `bun run build` (outputs to `dist/tools` or `dist/tools.exe`)

## Directory Structure

- `src/`: TypeScript sources.
    - `index.ts`: Entry point. Registers commands and parses input args.
    - `commands/`: CLI command action controllers.
        - `shiki.ts`: Handles Shiki highlighting and passes HAST nodes to the compiler.
    - `utils/`: Reusable formatting and compiling functions.
        - `hast-to-compose-html.ts`: The recursive HAST compiler converting HTML AST to Kotlin Compose HTML code.

## Coding Standards & Patterns

- Use TypeScript with strict typing.
- 'src/index.ts' should only handle CLI parsing and command registration. All logic should be delegated to command
  controllers in 'src/commands/'.
- Command controllers should be focused on a single responsibility (e.g., Shiki highlighting) and should not contain
  CLI parsing logic.
- Utility functions in 'src/utils/' should be pure and reusable across different commands if needed.
- Use `snak-case` for file names and `camelCase` for variables and functions.
