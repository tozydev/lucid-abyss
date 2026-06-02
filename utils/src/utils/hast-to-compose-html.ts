import type {Node, Text, Comment, Root, Element} from 'hast'

export function escapeKotlinString(str: string): string {
    return str
        .replace(/\\/g, '\\\\')
        .replace(/"/g, '\\"')
        .replace(/\$/g, '\\$')
        .replace(/\r/g, '\\r')
        .replace(/\n/g, '\\n')
        .replace(/\t/g, '\\t')
}

const TEXT_NODE = 'text';
const COMMENT_NODE = 'comment';
const DOCTYPE_NODE = 'doctype';
const ELEMENT_NODE = 'element';
const ROOT_NODE = 'root';

export function hastToComposeHtml(node: Node, indentLevel = 0): string {
    const indent = '    '.repeat(indentLevel)

    switch (node.type) {
        case TEXT_NODE:
            return handleTextNode(node as Text, indent)
        case COMMENT_NODE:
            return handleCommentNode(node as Comment, indent)
        case DOCTYPE_NODE:
            return handleDoctypeNode(indent)
        case ELEMENT_NODE:
            return handleElementNode(node as Element, indentLevel)
        case ROOT_NODE:
            return handleRootNode(node as Root, indentLevel)
        default:
            return `${indent}// Unknown HAST node type: ${node.type}\n`
    }
}

const JB_COMPOSE_WEB_DOM = "org.jetbrains.compose.web.dom."

function handleTextNode(node: Text, indent: string): string {
    const val = node.value || ''
    if (val === '') return ''
    return `${indent}${JB_COMPOSE_WEB_DOM}Text("${escapeKotlinString(val)}")\n`
}

function handleCommentNode(node: Comment, indent: string): string {
    const val = node.value || ''
    return `${indent}// ${val.replace(/\n/g, `\n${indent}// `)}\n`
}

function handleDoctypeNode(indent: string): string {
    return `${indent}// DOCTYPE html\n`
}

function handleRootNode(node: Root, indentLevel: number): string {
    let rootCode = ''
    if (node.children) {
        for (const child of node.children) {
            rootCode += hastToComposeHtml(child, indentLevel)
        }
    }
    return rootCode
}

function handleElementNode(node: Element, indentLevel: number): string {
    const indent = '    '.repeat(indentLevel)
    const tagName = node.tagName || ''
    const attrs = buildAttributes(node.properties)
    const attrStr = attrs.length > 0 ? `{ ${attrs.join('; ')} }` : ''

    let childCode = ''
    if (node.children && node.children.length > 0) {
        for (const child of node.children) {
            childCode += hastToComposeHtml(child, indentLevel + 1)
        }
    }

    if (tagName.includes('-')) {
        return buildTagElementFallback(tagName, attrs, childCode, indent)
    }

    let code = `${indent}${JB_COMPOSE_WEB_DOM}${getKotlinTagName(tagName)}`
    if (attrStr !== '') {
        code += `(${attrStr})`
    }
    if (childCode !== '') {
        code += ` {\n${childCode}${indent}}\n`
    }
    if (childCode === '' && attrStr === '') {
        code += `()`
    }
    return code + '\n'
}

function buildAttributes(properties: Element['properties']): string[] {
    const attrs: string[] = []
    if (!properties) return attrs

    // Handle class
    const classVal = properties.class || properties.className
    if (classVal) {
        const classes = Array.isArray(classVal)
            ? classVal
            : (typeof classVal === 'string' ? classVal.split(/\s+/) : [String(classVal)])
        if (classes.length > 0) {
            attrs.push(`classes(${classes.map((c: string) => `"${c}"`).join(', ')})`)
        }
    }

    // Handle style
    if (properties.style) {
        attrs.push(`attr("style", "${escapeKotlinString(String(properties.style))}")`)
    }

    // Handle all other properties
    for (const [key, val] of Object.entries(properties)) {
        if (key === 'class' || key === 'className' || key === 'style') continue
        const htmlKey = key.toLowerCase()
        attrs.push(`attr("${htmlKey}", "${escapeKotlinString(String(val))}")`)
    }

    return attrs
}

function getKotlinTagName(tagName: string): string {
    const lowerTagName = tagName.toLowerCase()
    if (lowerTagName === 'a') return 'A'
    if (lowerTagName === 'br') return 'Br'
    if (lowerTagName === 'hr') return 'Hr'
    if (lowerTagName === 'img') return 'Img'
    if (lowerTagName === 'p') return 'P'
    if (/^h[1-6]$/.test(lowerTagName)) return lowerTagName.toUpperCase()

    return tagName.charAt(0).toUpperCase() + tagName.slice(1)
}

function buildTagElementFallback(tagName: string, attrs: string[], childCode: string, indent: string): string {
    const escapedTagName = escapeKotlinString(tagName)
    const attrsInit = attrs.length > 0 ? `{ ${attrs.join('; ')} }` : 'null'

    let elementStr = `${indent}TagElement(\n` +
        `${indent}    elementBuilder = object : ElementBuilder<org.w3c.dom.HTMLElement> {\n` +
        `${indent}        override fun create(): org.w3c.dom.HTMLElement = kotlinx.browser.document.createElement("${escapedTagName}") as org.w3c.dom.HTMLElement\n` +
        `${indent}    },\n` +
        `${indent}    applyAttrs = ${attrsInit}\n` +
        `${indent})`

    if (childCode !== '') {
        return elementStr + ` {\n${childCode}${indent}}\n`
    } else {
        return elementStr + '\n'
    }
}
