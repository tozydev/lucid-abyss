import {createHighlighter} from 'shiki'
import {hastToComposeHtml} from '../utils/hast-to-compose-html'

interface ShikiOptions {
    lang: string
}

export async function shikiCommand(code: string | undefined, options: ShikiOptions) {
    try {
        let finalCode = code
        if (!finalCode || finalCode === '-') {
            finalCode = await Bun.stdin.text()
        }

        const highlighter = await createHighlighter({
            langs: [options.lang],
            themes: ['one-light', 'one-dark-pro']
        })

        const hast = highlighter.codeToHast(finalCode, {
            lang: options.lang,
            themes: {
                light: 'one-light',
                dark: 'one-dark-pro'
            },
            defaultColor: false,
            colorReplacements: {
                "#fafafa": "var(--color-surface-container-high)",
                "#282c34": "var(--color-surface-container-high)",
            }
        })

        const kotlinCode = hastToComposeHtml(hast)
        await Bun.stdout.write(kotlinCode)
    } catch (error) {
        console.error("Error highlighting code with Shiki:", error)
        process.exit(1)
    }
}
