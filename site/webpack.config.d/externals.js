(/** @param {import('webpack').Configuration} config */ function (config) {
    config.externals = config.externals || {};
    config.externals["/_la/pagefind/pagefind.js"] = "module /_la/pagefind/pagefind.js";
})(config);
