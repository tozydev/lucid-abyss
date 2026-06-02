import {createHighlighter, type Highlighter} from 'shiki'
import {hastToComposeHtml} from '../utils/hast-to-compose-html'

let highlighter: Highlighter | null = null

async function getHighlighter() {
    if (!highlighter) {
        highlighter = await createHighlighter({
            themes: ['one-light', 'one-dark-pro'],
            langs: []
        })
    }
    return highlighter
}

export async function highlightCode(code: string, lang: string): Promise<string> {
    const h = await getHighlighter()
    let finalLang = lang || 'text'

    if (finalLang !== 'text' && !h.getLoadedLanguages().includes(finalLang)) {
        try {
            // @ts-ignore
            await h.loadLanguage(finalLang)
        } catch (e) {
            finalLang = 'text'
        }
    }

    const hast = h.codeToHast(code || '', {
        lang: finalLang,
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

    return hastToComposeHtml(hast)
}
