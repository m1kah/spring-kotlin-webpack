const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");

// mode is either "production" or "development"
module.exports = ({ mode }) => {
    return {
        mode,
        devtool: mode === "development"
            ? "source-map"
            : "none",
        entry: {
            app: ["./src/frontend/index.js"]
        },
        output: {
            path: path.join(__dirname, "target/classes/public"),
            filename: "bundle.js",
            publicPath: "/"
        },
        module: {
            rules: [
                {
                    test: /.js$/,
                    exclude: /node_modules/,
                    use: {
                        loader: "babel-loader",
                        options: {
                            presets: ["@babel/preset-env"]
                        }
                    }
                }
            ]
        },
        plugins: [
            // Make index.html from our template
            new HtmlWebpackPlugin({
                template: "./src/frontend/index.html"
            }),
            // Setup polyfills by copying them to output path to folder
            // webcomponents.
            new CopyWebpackPlugin([
                {
                    context: "node_modules/@webcomponents/webcomponentsjs",
                    from: "**/*.js",
                    to: "webcomponents"
                }
            ])
        ],
        devServer: {
            // contentBase: path.join(__dirname, "target/classes/public"),
            // https: true,
            port: 8081,
            historyApiFallback: true,
            watchOptions: {
                aggregateTimeout: 300,
                poll: 100
            }
        }
    }
};
