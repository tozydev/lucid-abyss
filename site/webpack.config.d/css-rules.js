(function (config) {
    const MiniCssExtractPlugin = require("mini-css-extract-plugin");
    const isProd = config.mode === "production";

    config.module.rules.push({
        test: /\.css$/,
        use: [
            MiniCssExtractPlugin.loader,
            "css-loader",
            {
                loader: "@tailwindcss/webpack",
                options: {
                    optimize: isProd
                }
            }
        ]
    })

    config.plugins.push(new MiniCssExtractPlugin({
        filename: "_la/css/lucid-abyss.css"
    }))

    const cssPath = require('path').resolve(__dirname, "kotlin/styles.css");
    config.entry.main.push(cssPath);
})(config);
