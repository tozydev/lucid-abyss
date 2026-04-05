(function (config) {
    config.output = config.output || {};

    const subDir = "_la/";

    const originalFilename = config.output.filename;
    if (typeof originalFilename === 'function') {
        config.output.filename = (chunkData) => {
            return subDir + originalFilename(chunkData);
        };
    } else {
        config.output.filename = subDir + "[name].js";
    }

    config.output.publicPath = '/';
    config.output.assetModuleFilename = subDir + "[name][ext][query]";

    config.output.asyncChunks = true;
    config.output.chunkFilename = subDir + "[name].[contenthash:8].js";

    config.output.environment = config.output.environment || {};
    config.output.environment.dynamicImport = true;
})(config);
