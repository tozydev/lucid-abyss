# Utils IPC Server Development Guidelines

This directory contains the **Utils IPC Server** subproject, a Bun-based utility package used by the parent Gradle project as a background process for syntax highlighting.

## Project Overview

- **Runtime**: Bun (v1.3.14+)
- **Language**: TypeScript (targeting Bun runtime)
- **Communication Protocol**: Standard I/O (Stdin/Stdout) line-based JSON IPC
- **Syntax Highlighting**: Shiki

## Setup & Development Commands

- Install dependencies: `bun install`
- Run local development server: `bun run src/index.ts`
- Build bytecode-compiled executable: `bun run build` (outputs to `dist/utils` or `dist/utils.exe`)

## Directory Structure

- `src/`: TypeScript sources.
    - `index.ts`: Entry point. Launches standard I/O listener and manages persistent Shiki highlighter instance.
    - `utils/`: Reusable formatting and compiling functions.
        - `hast-to-compose-html.ts`: The recursive HAST compiler converting HTML AST to Kotlin Compose HTML code.

## Coding Standards & Patterns

- Use TypeScript with strict typing.
- `src/index.ts` handles the JSON-RPC-like line protocol, manages the lifecycle of the process and Shiki cache, and delegates HTML AST conversion to utilities.
- Utility functions in `src/utils/` should be pure and reusable.
- Use `kebab-case` or `snake-case` for file names and `camelCase` for variables and functions.

