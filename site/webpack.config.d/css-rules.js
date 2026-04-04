(function (config) {
    const CssMinimizerPlugin = require("css-minimizer-webpack-plugin");
    const MiniCssExtractPlugin = require("mini-css-extract-plugin");
    config.module.rules.push({
        test: /\.css$/,
        use: [
            MiniCssExtractPlugin.loader,
            "css-loader",
            {
                loader: "postcss-loader",
                options: {
                    postcssOptions: {
                        plugins: {
                            "@tailwindcss/postcss": {},
                        }
                    }
                }
            }
        ]
    })

    config.plugins.push(new MiniCssExtractPlugin({
        filename: "_la/lucid-abyss.css"
    }))
    config.optimization = {
        minimizer: [
            `...`,
            new CssMinimizerPlugin()
        ]
    }

    const cssPath = require('path').resolve(__dirname, "kotlin/styles.css");
    config.entry.main.push(cssPath);
})(config);
