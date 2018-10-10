const path = require('path');
const webpack = require('webpack');
const CleanPlugin = require('clean-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const tslintConfig = require('../tslint.json');

const CopyWebpackPlugin = require('copy-webpack-plugin');

const projectRootPath = path.join(__dirname, '..');

const sourcePath = path.join(projectRootPath, 'src');
const assetsPath = path.join(projectRootPath, 'static', 'static');

module.exports = {
  mode: 'production',

  // 构建时输出多少信息
  stats: {
    errorDetails: true,
    colors: true,
  },

  devtool: 'source-map',

  context: projectRootPath,

  entry: {
    app: [
      './src/client.tsx',
    ],
  },

  output: {
    path: assetsPath, // 保存到文件系统的哪里
    filename: '[name]-[chunkhash:8].js',
    chunkFilename: '[name]-[chunkhash:8].js',
    publicPath: '',
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
      }, {
        test: /\.css$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader',
        }),
      }, {
        test: /\.scss$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader?sourceMap!sass-loader?outputStyle=expanded&sourceMap',
        }),
      }, {
        test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
        use: {
          loader: 'url-loader',
          options: {
            limit: 10000,
            mimetype: 'application/font-woff',
          },
        },
      }, {
        test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
        use: {
          loader: 'url-loader',
          options: {
            limit: 10000,
            mimetype: 'application/font-woff',
          },
        },
      }, {
        test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
        use: {
          loader: 'url-loader',
          options: {
            limit: 10000,
            mimetype: 'application/octet-stream',
          },
        },
      }, {
        test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
        use: {
          loader: 'file-loader',
        },
      }, {
        test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
        use: {
          loader: 'url-loader',
          options: {
            limit: 10000,
            mimetype: 'image/svg+xml',
          },
        },
      }, {
        test: /(\.gif|\.png)/,
        exclude: [/node_modules/],
        use: {
          loader: 'url-loader',
          options: {
            limit: 10000,
          },
        },
      },
    ],
  },

  resolve: {
    modules: [
      sourcePath,
      'node_modules',
    ],
    extensions: ['.json', '.js', '.jsx', '.scss', '.ts', '.tsx', '.scss'],
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
    new CleanPlugin([assetsPath], {
      root: projectRootPath,
    }),

    // css files from the extract-text-plugin loader
    new ExtractTextPlugin({
      filename: '[name]-[chunkhash:8].css',
      allChunks: true,
    }),

    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: '"production"',
      },

      __SERVER_ADDR__: '""',
      __PUBLIC_PATH__: '/static/',
      __CLIENT__: true,
      __SERVER__: false,
      __DEVELOPMENT__: false,
      __DEVTOOLS__: false,
    }),

    // ignore dev config
    new webpack.IgnorePlugin(/\.\/dev/, /\/config$/),

    // optimizations
    new webpack.optimize.OccurrenceOrderPlugin(),

    new HtmlWebpackPlugin({
      title: 'GM后台',
      basename: '',
      template: 'src/index.html', // Load a custom template
      inject: false, // Inject all scripts into the body
    }),

    // 把指定文件夹的文件复制到指定的目录
    new CopyWebpackPlugin([
      {
        from: './src/styles',
        to: 'styles',
      },
    ]),

  ],
};
