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

    config.output.chunkFilename = subDir + "[name]-[chunkhash].js";
    config.output.assetModuleFilename = subDir + "[name][ext][query]";

    config.output.publicPath = '/';
})(config);
