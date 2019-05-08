const path = require('path')
const webpack = require('webpack')
module.exports = {
    mode: 'development',
    entry: './src/main.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, './dist')
    },
    module: {
        rules: [
            { test: /\.css$/, use: ['style-loader', 'css-loader'] },
            { test: /\.(eot|svg|ttf|woff|woff2)$/, use: 'url-loader' },
            { test: /\.vue$/, use: 'vue-loader' },
            {test:/\.js$/,use:'babel-loader',exclude:/node_modules/}
        ]
    },
    resolve: {
        alias: {
            'jquery': 'jquery'
        }
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "windows.jQuery": "jquery"
        })
    ]
}