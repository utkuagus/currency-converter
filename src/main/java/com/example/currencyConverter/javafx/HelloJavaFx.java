package com.example.currencyConverter.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelloJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        WebView webView = new WebView();
        webView.getEngine().load("http://localhost:8080/myapp2/");

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("HTML Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}