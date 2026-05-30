import cac from 'cac'
import { shikiCommand } from './commands/shiki'

const cli = cac()

cli
    .command("shiki [code]", "Highlight code with shiki and output Kotlin Compose Web HTML code")
    .option("-l, --lang <lang>", "The language of the code to highlight", {default: "text"})
    .action(shikiCommand)

cli.help()
cli.parse()
