import { ReactNode } from "react"
import type { Metadata } from "next"
import { ColorSchemeScript, mantineHtmlProps, MantineProvider } from "@mantine/core"
import "@mantine/core/styles.css"
import "./globals.css"

export const metadata: Metadata = {
  title: "Lucid Abyss",
  description: "tozydev's personal website",
}

export default function RootLayout({ children }: Readonly<{ children: ReactNode }>) {
  // noinspection HtmlRequiredTitleElement
  return (
    <html lang="en" {...mantineHtmlProps}>
      <head>
        <ColorSchemeScript />
      </head>
      <body>
        <MantineProvider>{children}</MantineProvider>
      </body>
    </html>
  )
}
