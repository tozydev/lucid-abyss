(function (config) {
    config.module.rules.forEach(rule => {
        if (rule.use && rule.use.includes("source-map-loader")) {
            rule.exclude = [/node_modules/];
        }
    });
})(config);
