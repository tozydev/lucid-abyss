package vn.id.tozydev.lucidabyss.strings

@Suppress("ktlint:standard:property-naming")
object ViStrings : SiteStrings {
    override val page_home_title = "Trang chủ"
    override val page_home_description = "Trang chủ của blog cá nhân của tôi"

    override val page_blog_title = "Blog"
    override val page_blog_description = "Blog cá nhân của tôi nơi tôi chia sẻ kiến thức và kinh nghiệm."

    override val page_about_title = "Về tôi"
    override val page_about_description = "Một chút về bản thân tôi, hành trình của tôi và những gì tôi đang làm."

    override val page_products_title = "Sản phẩm"
    override val page_products_description = "Nơi trưng bày các sản phẩm và dự án của tôi."

    override val section_hero_title_first = "Xin chào, tôi là"
    override val section_hero_title_second = "Thanh Tân"
    override val section_hero_description_first =
        "Hay còn được gọi là tozydev, một developer. Với tôi, code là một đam mê, nó tuyệt vời hơn khi tôi code với "
    override val section_hero_description_second = ". Và blog này, nơi tôi chia sẽ nhưng câu chuyện của mình..."
    override val section_hero_image_alt = "Ảnh đại diện"
    override val section_hero_learn_more = "Tìm hiểu thêm"
    override val section_hero_view_blog = "Xem blog"

    override val section_techstack_title = "Tech Stack"
    override val section_techstack_badge_java = "Java"
    override val section_techstack_badge_kotlin = "Kotlin"
    override val section_techstack_badge_kobweb = "Kobweb"

    override val section_socials_label_github = "GitHub"
    override val section_socials_label_linkedin = "LinkedIn"
    override val section_socials_label_bluesky = "Bluesky"
    override val section_socials_label_email = "Email"

    override val section_footer_copyright = "© 2026 tozydev. "
    override val section_footer_built_with = "Xây dựng với Kobweb."
    override val section_footer_rights = "Mọi quyền được bảo lưu"
    override val section_footer_link_github = "GitHub"
    override val section_footer_link_bluesky = "Bluesky"
    override val section_footer_link_email = "Email"
    override val section_footer_link_rss = "RSS"

    override val section_header_menu_home = "Trang chủ"
    override val section_header_menu_me = "Về tôi"
    override val section_header_menu_blog = "Blog"
    override val section_header_menu_products = "Sản phẩm"

    override val widget_bottom_navbar_home = "Trang chủ"
    override val widget_bottom_navbar_me = "Về tôi"
    override val widget_bottom_navbar_blog = "Blog"
    override val widget_bottom_navbar_products = "Sản phẩm"

    override val widget_discussion_title = "Thảo luận"
    override val widget_discussion_description = "Chức năng thảo luận sẽ sớm được ra mắt!"

    override val widget_featured_project_badge = "Nổi bật"
    override val widget_featured_project_description =
        "Website cá nhân của tôi, được xây dựng bằng Kotlin/JS và Kobweb."
    override val widget_featured_project_view_details = "Xem chi tiết"
    override val widget_featured_project_author_avatar_alt = "Ảnh đại diện tác giả dự án"

    override val widget_latest_post_badge = "Mới nhất"
    override val widget_latest_post_read_more = "Đọc thêm"

    override val widget_location_title = "Hồ Chí Minh, Việt Nam"
    override val widget_location_description = "Đang học tại UTH"

    override val widget_quote_text = "“I know that I know nothing”"
    override val widget_quote_author = "— Socrates"

    override val widget_share_post_title = "Chia sẻ bài viết"

    override val widget_table_of_contents_title = "Mục lục"

    override val widget_back_to_top_button_label = "Về đầu trang"

    override val widget_next_prev_posts_next = "Bài tiếp theo"
    override val widget_next_prev_posts_prev = "Bài trước đó"

    override val page_blog_header_title_first = "Chia sẻ kiến thức"
    override val page_blog_header_title_second = "Lập trình và cuộc sống"
    override val page_blog_header_description =
        "Nơi lưu lại những bài học, kinh nghiệm và những điều thú vị trong hành trình phát triển bản thân để trở nên tốt hơn."

    override val widget_post_cover_alt: (String) -> String = { "Ảnh bìa bài viết $it" }
}
