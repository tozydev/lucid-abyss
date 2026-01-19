package vn.id.tozydev.lucidabyss.strings

@Suppress("ktlint:standard:property-naming")
object EnStrings : SiteStrings {
    override val page_home_title = "Home"
    override val page_home_description = "Home page of my personal blog"

    override val page_blog_title = "Blog"
    override val page_blog_description = "My personal blog where I share my knowledge and experiences."

    override val page_about_title = "About Me"
    override val page_about_description = "A bit about myself, my journey and what I'm doing."

    override val page_products_title = "Products"
    override val page_products_description = "Showcase of my products and projects."

    override val section_hero_title_first = "Hello, I'm"
    override val section_hero_title_second = "Thanh Tan"
    override val section_hero_description_first =
        "Also known as tozydev, a developer. For me, coding is a passion, it's even better when I code with "
    override val section_hero_description_second = ". And this blog, where I share my stories..."
    override val section_hero_image_alt = "Profile picture"
    override val section_hero_learn_more = "Learn more"
    override val section_hero_view_blog = "View blog"

    override val section_techstack_title = "Tech Stack"
    override val section_techstack_badge_java = "Java"
    override val section_techstack_badge_kotlin = "Kotlin"
    override val section_techstack_badge_kobweb = "Kobweb"

    override val section_socials_label_github = "GitHub"
    override val section_socials_label_linkedin = "LinkedIn"
    override val section_socials_label_bluesky = "Bluesky"
    override val section_socials_label_email = "Email"

    override val section_footer_copyright = "© 2026 tozydev. "
    override val section_footer_built_with = "Built with Kobweb."
    override val section_footer_rights = "All rights reserved"
    override val section_footer_link_github = "GitHub"
    override val section_footer_link_bluesky = "Bluesky"
    override val section_footer_link_email = "Email"
    override val section_footer_link_rss = "RSS"

    override val section_header_menu_home = "Home"
    override val section_header_menu_me = "About Me"
    override val section_header_menu_blog = "Blog"
    override val section_header_menu_products = "Products"

    override val widget_bottom_navbar_home = "Home"
    override val widget_bottom_navbar_me = "About Me"
    override val widget_bottom_navbar_blog = "Blog"
    override val widget_bottom_navbar_products = "Products"

    override val widget_discussion_title = "Discussion"
    override val widget_discussion_description = "Discussion feature is coming soon!"

    override val widget_featured_project_badge = "Featured"
    override val widget_featured_project_description = "My personal website, built with Kotlin/JS and Kobweb."
    override val widget_featured_project_view_details = "View details"
    override val widget_featured_project_author_avatar_alt = "Project author avatar"

    override val widget_latest_post_badge = "Latest"
    override val widget_latest_post_read_more = "Read more"

    override val widget_location_title = "Ho Chi Minh, Vietnam"
    override val widget_location_description = "Studying at UTH"

    override val widget_quote_text = "“I know that I know nothing”"
    override val widget_quote_author = "— Socrates"

    override val widget_share_post_title = "Share this post"

    override val widget_table_of_contents_title = "Table of Contents"

    override val widget_back_to_top_button_label = "Back to top"

    override val widget_next_prev_posts_next = "Next post"
    override val widget_next_prev_posts_prev = "Previous post"

    override val page_blog_header_title_first = "Sharing knowledge"
    override val page_blog_header_title_second = "Programming and life"
    override val page_blog_header_description =
        "Where I save lessons, experiences and interesting things in my self-development journey to become better."

    override val widget_post_cover_alt: (String) -> String = { "Cover image for $it" }
}
