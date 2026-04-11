(/** @param {import('webpack').Configuration} config */ function (config) {
    const isProd = config.mode === "production";
    const subDir = "_la/";

    if (isProd) {
        const dummyPath = require('path').resolve(__dirname, "dummy.js");
        require('fs').writeFileSync(dummyPath, "// This file is a dummy to make Kobweb happy");
        config.entry.dummy = [dummyPath];
    }

    config.output.filename = (chunkData) => {
        switch (chunkData.chunk.name) {
            case 'dummy':
                return subDir + "js/lucid-abyss.js";
            case 'main':
                return subDir + "js/lucid-abyss." + (isProd ? "[contenthash:8].js" : "js");
            default:
                return subDir + "js/[name]." + (isProd ? "[contenthash:8].js" : "js");
        }
    };

    config.output.publicPath = '/';
    config.output.assetModuleFilename = subDir + "[name][ext][query]";

    config.output.asyncChunks = true;
    config.output.chunkFilename = subDir + "js/[name]." + (isProd ? "[contenthash:8].js" : "js");

    config.output.environment = config.output.environment || {};
    config.output.environment.dynamicImport = true;
})(config);
