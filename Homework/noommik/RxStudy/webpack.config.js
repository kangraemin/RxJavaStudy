const path = require('path')
const webpack = require('webpack')
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
	mode: 'production',
	entry: {
		vendor: './src/vendor.ts',
		index: './src/index-6.ts',
	},
	output: {
		path: path.resolve(__dirname, 'dist'),
		filename: '[name].bundle.js'
	},

	optimization: {
		splitChunks: {
			chunks: 'all',
			cacheGroups: {
				defaultVendors: {
					test: /[\\/]node_modules[\\/]/,
					priority: -10,
					reuseExistingChunk: true,
				},
			},
		},
	},

	resolve: {
		alias: {
			'@src': path.resolve(__dirname, 'src')
		},
		extensions: ['.ts', '.tsx', '.js', '.jsx'],
	},

	module: {
		rules: [
			{
				test: /\.tsx?$/,
        use: 'ts-loader',
        exclude: /node_modules/,
			}
		]
	},

	devtool: 'eval-cheap-source-map',

	devServer: {
		magicHtml: true,
		port: 8080,
		hot: true,
		historyApiFallback: true,
	},

	performance: {
		hints: process.env.NODE_ENV === 'production' ? "warning" : false
	},

	plugins: [
		new webpack.ProgressPlugin(),
		new CleanWebpackPlugin(),
		new HtmlWebpackPlugin({
			filename: 'index.html',
      template: 'index.html',
    }),
	],
}
