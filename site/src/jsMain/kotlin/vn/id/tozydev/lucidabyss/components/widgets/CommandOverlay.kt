package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLHeadingElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import vn.id.tozydev.lucidabyss.generated.Posts
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.pagefind.PagefindSearchClient
import vn.id.tozydev.lucidabyss.utils.pagefind.PagefindSearchResult
import vn.id.tozydev.lucidabyss.utils.tw

private data class OverlayCommand(
    val id: String,
    val label: String,
    val icon: (@Composable () -> Unit)? = null,
    val keywords: List<String>,
    val searchText: String,
    val action: () -> Unit,
)

private data class TocEntry(
    val id: String,
    val title: String,
)

private data class CommandGroup(
    val title: String,
    val commands: List<OverlayCommand>,
)

private fun buildSearchText(
    label: String,
    keywords: List<String>,
): String =
    buildList {
        add(label)
        addAll(keywords)
    }.joinToString(separator = " ").lowercase()

@Composable
fun CommandOverlay(
    isOpen: Boolean,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (!isOpen) return

    val ctx = rememberPageContext()
    val currentOnClose by rememberUpdatedState(onClose)

    var themeMode by ThemeMode.currentState
    var query by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(0) }

    var isSearchingPosts by remember { mutableStateOf(false) }
    var searchedPosts by remember { mutableStateOf<List<PagefindSearchResult>>(emptyList()) }
    var tocEntries by remember { mutableStateOf<List<TocEntry>>(emptyList()) }

    var searchInputElement by remember { mutableStateOf<HTMLInputElement?>(null) }
    var commandListElement by remember { mutableStateOf<HTMLElement?>(null) }
    val commandButtonsById = remember { mutableMapOf<String, HTMLButtonElement>() }

    val normalizedQuery = query.trim().lowercase()
    val hasQuery = normalizedQuery.isNotBlank()

    val (quickActions, navigationCommands, tocCommands) =
        remember(themeMode, tocEntries) {
            val quick =
                listOf(
                    OverlayCommand(
                        id = "action-toggle-theme",
                        label = Strings.commons.actions.toggleTheme,
                        icon = {
                            when (themeMode) {
                                ThemeMode.Light -> LightModeIcon()
                                ThemeMode.Dark -> DarkModeIcon()
                                ThemeMode.System -> RoutineIcon()
                            }
                        },
                        keywords = listOf("theme", "dark", "light"),
                        searchText =
                            buildSearchText(
                                Strings.commons.actions.toggleTheme,
                                listOf("theme", "dark", "light"),
                            ),
                        action = {
                            themeMode = themeMode.cycle
                            currentOnClose()
                        },
                    ),
                    OverlayCommand(
                        id = "action-scroll-top",
                        label = Strings.commons.actions.scrollToTop,
                        icon = { ArrowUpwardIcon() },
                        keywords = listOf("top", "scroll"),
                        searchText = buildSearchText(Strings.commons.actions.scrollToTop, listOf("top", "scroll")),
                        action = {
                            window.scrollTo(0.0, 0.0)
                            currentOnClose()
                        },
                    ),
                )

            val nav =
                listOf(
                    OverlayCommand(
                        id = "nav-home",
                        label = Strings.commons.navigation.home,
                        icon = { HomeIcon() },
                        keywords = listOf("home", "index"),
                        searchText = buildSearchText(Strings.commons.navigation.home, listOf("home", "index")),
                        action = {
                            ctx.router.navigateTo(SiteRoutes.home)
                            currentOnClose()
                        },
                    ),
                    OverlayCommand(
                        id = "nav-blog",
                        label = Strings.commons.navigation.blog,
                        icon = { ArticleIcon() },
                        keywords = listOf("blog", "posts"),
                        searchText = buildSearchText(Strings.commons.navigation.blog, listOf("blog", "posts")),
                        action = {
                            ctx.router.navigateTo(SiteRoutes.blog)
                            currentOnClose()
                        },
                    ),
                    OverlayCommand(
                        id = "nav-about",
                        label = Strings.commons.navigation.about,
                        icon = { PersonIcon() },
                        keywords = listOf("about", "profile"),
                        searchText = buildSearchText(Strings.commons.navigation.about, listOf("about", "profile")),
                        action = {
                            ctx.router.navigateTo(SiteRoutes.about)
                            currentOnClose()
                        },
                    ),
                )

            val toc =
                tocEntries.map { entry ->
                    OverlayCommand(
                        id = "toc-${entry.id}",
                        label = entry.title,
                        icon = { FormatListBulletedIcon() },
                        keywords = listOf(entry.title, Strings.commons.labels.toc),
                        searchText = buildSearchText(entry.title, listOf(entry.title, Strings.commons.labels.toc)),
                        action = {
                            window.location.hash = entry.id
                            currentOnClose()
                        },
                    )
                }

            Triple(quick, nav, toc)
        }

    val postCommands =
        remember(normalizedQuery, searchedPosts) {
            if (normalizedQuery.isBlank()) {
                Posts.take(5).map { post ->
                    OverlayCommand(
                        id = "recent-${post.slug}",
                        label = post.title,
                        icon = { UpdateIcon() },
                        keywords = listOf(post.title, post.description),
                        searchText = buildSearchText(post.title, listOf(post.title, post.description)),
                        action = {
                            ctx.router.navigateTo(post.route)
                            currentOnClose()
                        },
                    )
                }
            } else {
                searchedPosts.map { result ->
                    OverlayCommand(
                        id = "search-${result.route}",
                        label = result.title,
                        icon = { ArticleIcon() },
                        keywords = listOf(result.title, result.excerpt),
                        searchText = buildSearchText(result.title, listOf(result.title, result.excerpt)),
                        action = {
                            ctx.router.navigateTo(result.route)
                            currentOnClose()
                        },
                    )
                }
            }
        }

    val filteredGroups =
        remember(normalizedQuery, quickActions, navigationCommands, tocCommands, postCommands) {
            val filterFunc = { cmd: OverlayCommand -> !hasQuery || cmd.matchesQuery(normalizedQuery) }

            listOf(
                CommandGroup(Strings.commandOverlay.sections.quickActions, quickActions.filter(filterFunc)),
                CommandGroup(Strings.commandOverlay.sections.navigation, navigationCommands.filter(filterFunc)),
                CommandGroup(Strings.commons.labels.toc, tocCommands.filter(filterFunc)),
                CommandGroup(
                    if (!hasQuery) Strings.commandOverlay.sections.recentPosts else Strings.commandOverlay.sections.searchResults,
                    postCommands,
                ),
            ).filter { it.commands.isNotEmpty() }
        }

    val visibleCommands = remember(filteredGroups) { filteredGroups.flatMap { it.commands } }
    val selectedCommandId = visibleCommands.getOrNull(selectedIndex)?.id

    LaunchedEffect(isOpen) {
        if (isOpen) {
            selectedIndex = 0
            query = ""
            searchedPosts = emptyList()
            tocEntries = findPostTocEntries()
            searchInputElement?.focus()
        }
    }

    LaunchedEffect(normalizedQuery) {
        if (normalizedQuery.isBlank()) {
            isSearchingPosts = false
            searchedPosts = emptyList()
            return@LaunchedEffect
        }

        isSearchingPosts = true

        val pagefindResults = runCatching { PagefindSearchClient.search(normalizedQuery) }.getOrElse { emptyList() }
        if (pagefindResults == null) return@LaunchedEffect

        searchedPosts =
            pagefindResults.ifEmpty {
                Posts
                    .filter {
                        it.title.contains(normalizedQuery, ignoreCase = true) ||
                            it.description.contains(
                                normalizedQuery,
                                ignoreCase = true,
                            )
                    }.take(5)
                    .map { PagefindSearchResult(title = it.title, route = it.route, excerpt = it.description) }
            }
        selectedIndex = 0
        isSearchingPosts = false
    }

    LaunchedEffect(visibleCommands.size) {
        if (selectedIndex !in visibleCommands.indices) selectedIndex = 0
    }

    LaunchedEffect(selectedCommandId) {
        val commandId = selectedCommandId ?: return@LaunchedEffect
        val selectedButton = commandButtonsById[commandId] ?: return@LaunchedEffect
        keepElementVisibleInContainer(container = commandListElement, element = selectedButton)
    }

    val currentVisibleCommands by rememberUpdatedState(visibleCommands)
    val currentSelectedIndex by rememberUpdatedState(selectedIndex)

    DisposableEffect(isOpen) {
        if (!isOpen) return@DisposableEffect onDispose { }

        val handleKeyDown: (Event) -> Unit = handler@{ event ->
            val keyboardEvent = event as? KeyboardEvent ?: return@handler
            when (keyboardEvent.key) {
                "ArrowDown" -> {
                    if (currentVisibleCommands.isNotEmpty()) {
                        keyboardEvent.preventDefault()
                        selectedIndex = (currentSelectedIndex + 1) % currentVisibleCommands.size
                    }
                }

                "ArrowUp" -> {
                    if (currentVisibleCommands.isNotEmpty()) {
                        keyboardEvent.preventDefault()
                        selectedIndex =
                            (currentSelectedIndex - 1).let { if (it < 0) currentVisibleCommands.size - 1 else it }
                    }
                }

                "Enter" -> {
                    if (currentVisibleCommands.isNotEmpty()) {
                        keyboardEvent.preventDefault()
                        currentVisibleCommands[currentSelectedIndex].action()
                    }
                }

                "Escape" -> {
                    keyboardEvent.preventDefault()
                    currentOnClose()
                }
            }
        }

        window.addEventListener("keydown", handleKeyDown)
        onDispose { window.removeEventListener("keydown", handleKeyDown) }
    }

    Div(
        Modifier
            .tw("fixed inset-0 z-100 flex items-stretch md:items-center justify-center p-0 md:p-6 bg-scrim/40 backdrop-blur-md")
            .then(modifier)
            .toAttrs {
                id("command-overlay")
                onClick { currentOnClose() }
            },
    ) {
        Div(
            {
                tw(
                    "w-full h-full md:h-auto md:max-w-160 bg-surface-container-lowest md:rounded-2xl shadow-soft overflow-hidden flex flex-col border-0 md:border md:border-outline/10",
                )
                onClick { it.stopPropagation() } // Prevent overlay click
            },
        ) {
            CommandHeader(
                query = query,
                onQueryChange = { query = it },
                onCancel = currentOnClose,
                onInputRef = { searchInputElement = it },
            )

            Div(
                {
                    tw("overflow-y-auto flex-1 min-h-0 p-2 space-y-2 md:max-h-[60vh]")
                    ref {
                        commandListElement = it as? HTMLElement
                        onDispose { }
                    }
                },
            ) {
                filteredGroups.forEach { group ->
                    CommandSectionView(
                        title = group.title,
                        commands = group.commands,
                        selectedCommandId = selectedCommandId,
                        onCommandButtonRefChange = { id, btn ->
                            if (btn == null) commandButtonsById.remove(id) else commandButtonsById[id] = btn
                        },
                    )
                }

                if (isSearchingPosts) {
                    Div({ tw("px-3 py-2 text-xs text-on-surface-variant") }) { Text(Strings.commandOverlay.loading) }
                }

                if (visibleCommands.isEmpty() && !isSearchingPosts) {
                    Div({ tw("px-3 py-2 text-xs text-on-surface-variant") }) { Text(Strings.commandOverlay.noResults) }
                }
            }

            CommandFooter()
        }
    }
}

@Composable
private fun CommandHeader(
    query: String,
    onQueryChange: (String) -> Unit,
    onCancel: () -> Unit,
    onInputRef: (HTMLInputElement) -> Unit,
) {
    Div({ tw("flex items-center px-5 py-4 border-b border-outline/10") }) {
        Span({ tw("text-primary mr-3") }) { Text("/") }
        Input(
            type = InputType.Text,
            attrs = {
                id("command-overlay-input")
                value(query)
                attr("placeholder", Strings.commandOverlay.placeholder)
                tw(
                    "w-full bg-transparent border-none outline-none text-lg font-label text-on-surface placeholder:text-on-surface-variant/60",
                )
                ref {
                    onInputRef(it)
                    onDispose { }
                }
                onInput { event -> onQueryChange(event.value) }
            },
        )
        Button(
            {
                tw("sm:hidden ml-3 text-sm font-label font-semibold text-on-surface-variant")
                onClick { onCancel() }
            },
        ) {
            Text(Strings.commandOverlay.cancel)
        }
        KeyboardHint(
            keyText = "ESC",
            labelText = null,
            keyPillClasses = "px-2 py-1 bg-surface-container rounded-md border border-outline/10 shadow-sm",
            modifier = Modifier.tw("hidden sm:flex ml-4"),
        )
    }
}

@Composable
private fun CommandFooter() {
    Div({ tw("hidden sm:flex px-5 py-3 bg-surface-container-low border-t border-outline/10 items-center justify-between") }) {
        Div({ tw("flex items-center gap-4") }) {
            KeyboardHint(
                keyText = "UP/DOWN",
                labelText = Strings.commandOverlay.hints.move,
                keyPillClasses = "p-0.5 bg-surface-container-lowest border border-outline/10 rounded shadow-sm",
            )
            KeyboardHint(
                keyText = "ENTER",
                labelText = Strings.commandOverlay.hints.select,
                keyPillClasses = "p-0.5 px-1 bg-surface-container-lowest border border-outline/10 rounded shadow-sm",
            )
        }
        Div({ tw("text-[10px] text-on-surface-variant font-label italic") }) {
            Text(Strings.commandOverlay.hints.close)
        }
    }
}

@Composable
private fun KeyboardHint(
    keyText: String,
    modifier: Modifier = Modifier,
    keyPillClasses: String? = null,
    labelText: String? = null,
) {
    Div(
        Modifier
            .tw("flex items-center gap-1 text-[10px] text-on-surface-variant font-label")
            .then(modifier)
            .toAttrs(),
    ) {
        Span(
            {
                tw("font-mono font-bold uppercase tracking-tight")
                keyPillClasses?.let { tw(it) }
            },
        ) {
            Text(keyText)
        }
        labelText?.let { Text(it) }
    }
}

@Composable
private fun CommandSectionView(
    title: String,
    commands: List<OverlayCommand>,
    selectedCommandId: String?,
    onCommandButtonRefChange: (String, HTMLButtonElement?) -> Unit,
) {
    if (commands.isEmpty()) return

    Div({ tw("px-3 pt-3 pb-1") }) {
        H3({ tw("text-[11px] font-label font-bold text-on-surface-variant uppercase tracking-[0.15em]") }) {
            Text(title)
        }
    }

    Div({ tw("space-y-0.5") }) {
        commands.forEach { command ->
            val isSelected = command.id == selectedCommandId
            Button(
                {
                    tw(
                        "w-full flex items-center justify-between px-3 py-2.5 rounded-xl group transition-colors text-left focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-primary cursor-pointer",
                    )
                    if (isSelected) {
                        tw("bg-primary-container text-on-primary-container ring-2 ring-primary/40")
                    } else {
                        tw("hover:bg-surface-container-high")
                    }
                    attr("tabindex", if (isSelected) "0" else "-1")
                    attr("data-command-id", command.id)
                    ref {
                        onCommandButtonRefChange(command.id, it)
                        onDispose { onCommandButtonRefChange(command.id, null) }
                    }
                    onClick { command.action() }
                },
            ) {
                Div({ tw("flex items-center gap-3") }) {
                    command.icon?.let { icon ->
                        Span(
                            {
                                if (isSelected) {
                                    tw("text-on-primary-container")
                                } else {
                                    tw("text-on-surface-variant group-hover:text-primary transition-colors")
                                }
                            },
                        ) {
                            icon()
                        }
                    }
                    Span(
                        {
                            tw("text-sm font-body font-medium")
                            if (isSelected) tw("text-on-primary-container") else tw("text-on-surface")
                        },
                    ) {
                        Text(command.label)
                    }
                }
            }
        }
    }
}

private fun OverlayCommand.matchesQuery(query: String): Boolean = searchText.contains(query)

private fun findPostTocEntries(): List<TocEntry> {
    val path = window.location.pathname
    if (!path.startsWith("/blog/") || path == "/blog/") return emptyList()

    return document
        .querySelectorAll("main article section h2[id]")
        .asList()
        .filterIsInstance<HTMLHeadingElement>()
        .mapNotNull { heading ->
            val id = heading.id.trim()
            val title = heading.textContent?.trim().orEmpty()
            if (id.isBlank() || title.isBlank()) null else TocEntry(id, title)
        }
}

private fun keepElementVisibleInContainer(
    container: HTMLElement?,
    element: HTMLElement,
) {
    val listContainer = container ?: return
    val containerRect = listContainer.getBoundingClientRect()
    val elementRect = element.getBoundingClientRect()

    if (elementRect.bottom > containerRect.bottom) {
        listContainer.scrollTop += elementRect.bottom - containerRect.bottom
    } else if (elementRect.top < containerRect.top) {
        listContainer.scrollTop -= containerRect.top - elementRect.top
    }
}
