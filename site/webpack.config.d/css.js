(/** @param {import('webpack').Configuration} config */ function (config) {
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

    const cssFilePrefix = "_la/css/lucid-abyss.";
    const cssFileName = isProd ? cssFilePrefix + "[contenthash:8].css" : cssFilePrefix + "css";

    config.plugins.push(new MiniCssExtractPlugin({
        filename: cssFileName,
    }))

    const cssPath = require('path').resolve(__dirname, "kotlin/styles.css");
    // noinspection JSUnresolvedReference
    config.entry.main.push(cssPath);
})(/** noinspection JSUnresolvedReference */ config);
