package vn.id.tozydev.lucidabyss.strings

import vn.id.tozydev.lucidabyss.core.SiteLanguage

@Suppress("PropertyName")
interface SiteStrings {
    val page_home_title: String
    val page_home_description: String

    val page_blog_title: String
    val page_blog_description: String

    val page_about_title: String
    val page_about_description: String

    val page_products_title: String
    val page_products_description: String

    val section_hero_title_first: String
    val section_hero_title_second: String
    val section_hero_description_first: String
    val section_hero_description_second: String
    val section_hero_image_alt: String
    val section_hero_learn_more: String
    val section_hero_view_blog: String

    val section_techstack_title: String
    val section_techstack_badge_java: String
    val section_techstack_badge_kotlin: String
    val section_techstack_badge_kobweb: String

    val section_socials_label_github: String
    val section_socials_label_linkedin: String
    val section_socials_label_bluesky: String
    val section_socials_label_email: String

    val section_footer_copyright: String
    val section_footer_built_with: String
    val section_footer_rights: String
    val section_footer_link_github: String
    val section_footer_link_bluesky: String
    val section_footer_link_email: String
    val section_footer_link_rss: String

    val section_header_menu_home: String
    val section_header_menu_me: String
    val section_header_menu_blog: String
    val section_header_menu_products: String

    val widget_bottom_navbar_home: String
    val widget_bottom_navbar_me: String
    val widget_bottom_navbar_blog: String
    val widget_bottom_navbar_products: String

    val widget_discussion_title: String
    val widget_discussion_description: String

    val widget_featured_project_badge: String
    val widget_featured_project_description: String
    val widget_featured_project_view_details: String
    val widget_featured_project_author_avatar_alt: String

    val widget_latest_post_badge: String
    val widget_latest_post_read_more: String

    val widget_location_title: String
    val widget_location_description: String

    val widget_quote_text: String
    val widget_quote_author: String

    val widget_share_post_title: String

    val widget_table_of_contents_title: String

    val widget_back_to_top_button_label: String

    val widget_next_prev_posts_next: String
    val widget_next_prev_posts_prev: String

    val page_blog_header_title_first: String
    val page_blog_header_title_second: String
    val page_blog_header_description: String

    val widget_post_cover_alt: (title: String) -> String
}

fun SiteLanguage.strings(): SiteStrings =
    when (this) {
        SiteLanguage.Vi -> ViStrings
        SiteLanguage.En -> EnStrings
    }
