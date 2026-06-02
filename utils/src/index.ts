import readline from 'readline'
import {highlightCode} from './commands/shiki'

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
})

rl.on('line', async (line) => {
    try {
        const trimmed = line.trim()
        if (!trimmed) return

        const request = JSON.parse(trimmed)
        const {id, command} = request

        switch (command) {
            case "shiki":
                const {code, lang} = request
                const result = await highlightCode(code || '', lang)
                process.stdout.write(JSON.stringify({id, result}) + '\n')
                break;
            default:
                process.stdout.write(JSON.stringify({id, error: `Unknown command: ${command}`}) + '\n')
                break;
        }
    } catch (error) {
        const message = error instanceof Error ? error.message : String(error)
        process.stdout.write(JSON.stringify({error: message}) + '\n')
    }
})
