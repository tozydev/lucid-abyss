package vn.id.tozydev.lucidabyss.utils.pagefind

import js.import.importAsync
import js.objects.unsafeJso
import js.promise.Promise
import js.promise.await

data class PagefindSearchResult(
    val title: String,
    val route: String,
    val excerpt: String,
)

private external interface PagefindMeta {
    val title: String?
}

private external interface PagefindResultData {
    val url: String
    val meta: PagefindMeta?
    val excerpt: String?
}

private external interface PagefindResultRef {
    fun data(): Promise<PagefindResultData>
}

private external interface PagefindResponse {
    val results: Array<PagefindResultRef>
}

private external interface PagefindModule {
    fun init(): Promise<Unit>

    fun options(options: dynamic): Promise<Unit>

    fun search(term: String): Promise<PagefindResponse>
}

object PagefindSearchClient {
    private const val PAGEFIND_SCRIPT_PATH = "/_la/pagefind/pagefind.js"
    private const val PAGEFIND_BUNDLE_PATH = "/_la/pagefind/"

    private var pagefindModule: PagefindModule? = null

    suspend fun search(
        query: String,
        limit: Int = 5,
    ): List<PagefindSearchResult> {
        if (query.isBlank()) return emptyList()

        val module = loadModule()
        val response = module.search(query).await()

        return response.results
            .asList()
            .take(limit)
            .mapNotNull { reference ->
                runCatching { reference.data().await() }.getOrNull()?.let { data ->
                    val title =
                        data.meta
                            ?.title
                            ?.trim()
                            .orEmpty()
                            .ifBlank { data.url }
                    PagefindSearchResult(
                        title = title,
                        route = data.url,
                        excerpt = data.excerpt.orEmpty().trim(),
                    )
                }
            }
    }

    private suspend fun loadModule(): PagefindModule {
        pagefindModule?.let { return it }

        val module = importAsync<dynamic>(PAGEFIND_SCRIPT_PATH).await().unsafeCast<PagefindModule>()
        module
            .options(
                unsafeJso {
                    baseUrl = "/"
                    bundlePath = PAGEFIND_BUNDLE_PATH
                },
            ).await()
        module.init().await()

        pagefindModule = module
        return module
    }
}
