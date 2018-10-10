// import WebpackDevServer from 'webpack-dev-server';
// import webpack from "webpack";
// import path from "path";

process.env.NODE_ENV = 'development';
process.env.APIPORT = 8081;
process.env.PORT = 3000;

const WebpackDevServer = require('webpack-dev-server');
const webpack = require('webpack');
const path = require('path');

const config = require('./webpack.renderer.dev.config');

const apiPort = process.env.APIPORT;
const port = process.env.PORT;

const compiler = webpack(config);
const server = new WebpackDevServer(compiler, {
    contentBase: [path.join(__dirname, '..', 'src')], // 使用绝对路径
    watchContentBase: true,
    // contentBase: false,
    hot: true, // 启用模块热替换特性
    historyApiFallback: true,
    quiet: false,
    noInfo: false,
    lazy: false, // 不使用惰性模式
    publicPath: config.output.publicPath,
    stats: {
        colors: true
    },
    proxy: {
        '/v1': {
            target: 'http://localhost:' + apiPort,
            ws: true
        }
    },
});

process.traceDeprecation = true;

// 开始监听变化
server.listen(port, (err) => {
    if (err) {
        console.error(err);
    } else {
        console.info('==> Webpack development server listening on port %s', port);
    }
});
