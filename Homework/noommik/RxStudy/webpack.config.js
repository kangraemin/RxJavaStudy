const path = require('path')
const webpack = require('webpack')
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const 
HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
	mode: 'production',
	entry: './src/index.ts',
	output: {
		path: path.resolve(__dirname, 'dist'),
		filename: '[name].js'
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

	devtool: 'eval',

	devServer: {
		magicHtml: true,
		port: 8080,
		hot: true,
		historyApiFallback: true,
	},

	plugins: [
		new webpack.ProgressPlugin(),
		new CleanWebpackPlugin(),
		new HtmlWebpackPlugin({
      template: 'index.html',
    }),
	],
}