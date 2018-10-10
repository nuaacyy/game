const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const projectRootPath = path.join(__dirname, '..');
const sourcePath = path.join(projectRootPath, 'src');
const assetsPath = path.join(projectRootPath, 'static');

const tslintConfig = require('../tslint.json');

const config = {
  mode: 'development',

  // 内联调试信息
  devtool: 'inline-source-map',

  context: projectRootPath,

  // 入口
  entry: {
    app: [
      // 'webpack/hot/dev-server',
      // 'webpack-dev-server/client?http://localhost:' + port,
      './src/client.tsx',
      // './src/index.js'
    ],
  },

  // 输出
  output: {
    path: assetsPath, // 保存到文件系统的哪里
    filename: '[name]-[hash].js', // 输出文件名
    chunkFilename: '[name]-[chunkhash].js', // 非entry的文件名
    publicPath: '', // 指定公共URL地址
  },

  module: {
    rules: [
      {
        enforce: 'pre',
        test: /\.tsx?$/,
        exclude: /node_modules/,
        use: {
          loader: 'tslint-loader',
          options: {
            failOnHint: true,
            configuration: tslintConfig,
          },
        },
      }, {
        test: /\.tsx?$/,
        use: 'awesome-typescript-loader',
        include: [
          sourcePath,
        ],
        exclude: /node_modules/,
      }, {
        test: /\.css$/,
        use: [
          {
            loader: 'style-loader',
          },
          {
            loader: 'css-loader',
          },
        ],
      }, {
        test: /\.scss$/,
        use: [
          {
            loader: 'style-loader',
          },
          {
            loader: 'css-loader',
            options: {
              sourceMap: true,
            },
          },
          {
            loader: 'sass-loader',
            options: {
              outputStyle: 'expanded',
              sourceMap: true,
            },
          },
        ],
      }, {
        test: /(\.gif|\.png)/,
        use: {
          loader: 'url-loader',
          options: {
            limit: 10000,
          },
        },
        exclude: [/node_modules/],
      },
    ],
  },

  // 哪些后缀的文件会被解析为模块文件
  resolve: {
    modules: [
      sourcePath,
      'node_modules',
    ],
    extensions: ['.json', '.js', '.jsx', '.ts', '.tsx', '.scss'],
  },

  optimization: {
    splitChunks: {
      chunks: 'initial',
      cacheGroups: {
        commons: {
          chunks: 'all',
          test: /[\\/]node_modules[\\/]/,
          name: 'vendors',
        },
      },
    },
  },

  plugins: [
    // 热加载
    new webpack.HotModuleReplacementPlugin(),

    new webpack.IgnorePlugin(/webpack-stats\.json$/), // 忽略匹配的文件

    new webpack.DefinePlugin({
      __SERVER_ADDR__: '""',
      __PUBLIC_PATH__: '"/"',
      __CLIENT__: true,
      __SERVER__: false,
      __DEVELOPMENT__: true,
      __DEVTOOLS__: true, // <-------- DISABLE redux-devtools HERE
    }),
    new HtmlWebpackPlugin({
      title: 'ns-mgr',
      basename: '',
      template: './src/index.html', // Load a custom template
      inject: false, // Inject all scripts into the body
    }),
    // new webpack.optimize.CommonsChunkPlugin({
    //     name: 'vendor',
    //     minChunks: (module) => {
    //         // 该配置假定你引入的 vendor 存在于 node_modules 目录中
    //         return module.context && module.context.indexOf('node_modules') !== -1;
    //     }
    // }),
    // new webpack.optimize.CommonsChunkPlugin({
    //     name: 'manifest' // But since there are no more common modules between them we end up with just the runtime code included in the manifest file
    // }),
  ],
};

module.exports = config;
