package com.huyuxuan.zzz;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
    Image backgroundImage = new Image(getClass().getResource("/com/huyuxuan/zzz/xuan.jpg").toExternalForm());
    BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    String iconPath = "zzz.png";
    Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
    stage.getIcons().add(icon);

    Label titleLabel = new Label("绝区零数据管理系统");
    titleLabel.setFont(Font.font("宋体", FontWeight.BOLD, 80));
    titleLabel.setStyle("-fx-text-fill: linear-gradient(to right, #FF4E50, #F9D423); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.8) , 5, 0.0 , 0 , 1 );");

    Label authorLabel = new Label("作者：网络2301 胡宇煊");
    authorLabel.setFont(Font.font("微软雅黑", FontWeight.BOLD, 30));
    authorLabel.setStyle("-fx-text-fill: linear-gradient(to right, #9C27B0, #E040FB); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.8) , 5, 0.0 , 0 , 1 );");

    Button queryButton = UIUtils.createStyledButton("查询", "#0000FF", FontAwesome.Glyph.SEARCH.toString());
    queryButton.setOnAction(e -> openNewWindow(new QueryInterface()));

    Button addButton = UIUtils.createStyledButton("添加", "#F9D423", FontAwesome.Glyph.PLUS.toString());
    addButton.setOnAction(e -> openNewWindow(new AddInterface()));

    Button modifyButton = UIUtils.createStyledButton("修改", "#009688", FontAwesome.Glyph.PENCIL.toString());
    modifyButton.setOnAction(e -> openNewWindow(new ModifyInterface()));

    Button deleteButton = UIUtils.createStyledButton("删除", "#FF6B6B", FontAwesome.Glyph.TRASH.toString());
    deleteButton.setOnAction(e -> openNewWindow(new DeleteInterface()));

    Button exitButton = UIUtils.createStyledButton("退出", "#FF0000", FontAwesome.Glyph.SIGN_OUT.toString());
    exitButton.setOnAction(e -> Platform.exit());

    VBox vbox = new VBox(30, titleLabel, authorLabel, queryButton, addButton, modifyButton, deleteButton, exitButton);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(70));

    StackPane root = new StackPane(vbox);
    root.setBackground(new Background(background));

    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("绝区零数据管理系统");
    stage.setScene(scene);
    stage.show();
}

private void openNewWindow(Application app) {
    Stage newStage = new Stage();
    try {
        app.start(newStage);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

public static void main(String[] args) {
        launch();
    }
}
