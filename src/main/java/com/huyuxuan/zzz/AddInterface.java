package com.huyuxuan.zzz;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

import java.sql.Connection;
import java.sql.SQLException;

public class AddInterface extends Application {

    private Connection connection;
    private ComboBox<String> typeComboBox;
    private TextField nameField;
    private TextField weaponField;
    private TextField artifactField;
    private TextArea introduceArea;
    private TextField provideHpField;

    @Override
    public void start(Stage stage) {
        Image backgroundImage = new Image(getClass().getResource("/com/huyuxuan/zzz/xuan.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        String iconPath = "zzz.png";
        Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
        stage.getIcons().add(icon);

        Button backButton = UIUtils.createStyledButton("返回", "#F9D423", FontAwesome.Glyph.ARROW_LEFT.toString());
        backButton.setOnAction(e -> stage.close());

        typeComboBox = UIUtils.createStyledComboBox("类型", "#F9D423");
        typeComboBox.getItems().addAll("角色", "武器", "驱动盘");
        typeComboBox.setValue("角色");

        nameField = UIUtils.createStyledTextField("名称", "#F9D423");
        weaponField = UIUtils.createStyledTextField("武器", "#F9D423");
        artifactField = UIUtils.createStyledTextField("驱动盘", "#F9D423");
        introduceArea = UIUtils.createStyledTextArea("简要介绍", "#F9D423");
        provideHpField = UIUtils.createStyledTextField("提供生命值", "#F9D423");

        Button addButton = UIUtils.createStyledButton("添加", "#F9D423", FontAwesome.Glyph.PLUS.toString());
        addButton.setOnAction(e -> insertData(typeComboBox.getValue()));

        VBox vbox = new VBox(30, backButton, typeComboBox, nameField, weaponField, artifactField, introduceArea, provideHpField, addButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(70));

        StackPane root = new StackPane(vbox);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("添加");
        stage.setScene(scene);
        stage.show();

        initDatabaseConnection();

        typeComboBox.setOnAction(e -> updateFieldPrompts(typeComboBox.getValue(), weaponField, artifactField, introduceArea, provideHpField));
    }

    private void updateFieldPrompts(String type, TextField weaponField, TextField artifactField, TextArea introduceArea, TextField provideHpField) {
        if (type.equals("角色")) {
            weaponField.setPromptText("武器");
            artifactField.setPromptText("驱动盘");
            introduceArea.setVisible(true);
            provideHpField.setPromptText("提供生命值");
            artifactField.setVisible(true);
        } else if (type.equals("武器")) {
            weaponField.setPromptText("类型");
            artifactField.setVisible(false);
            introduceArea.setVisible(false);
            provideHpField.setPromptText("提供生命值");
        } else if (type.equals("驱动盘")) {
            weaponField.setPromptText("属性");
            artifactField.setVisible(false);
            introduceArea.setVisible(false);
            provideHpField.setPromptText("提供生命值");
        }
    }

    private void insertData(String type) {
        String tableName = getTableName(type);

        try {
            if (tableName.equals("characters")) {
                CharacterDao characterDao = new CharacterDao(connection);
                CharacterEntity character = new CharacterEntity();
                character.setName(nameField.getText());
                character.setWeaponName(weaponField.getText());
                character.setArtifactsName(artifactField.getText());
                character.setIntroduce(introduceArea.getText());
                character.setProvideHp(Integer.parseInt(provideHpField.getText()));
                characterDao.addCharacter(character);
            } else if (tableName.equals("weapons")) {
                WeaponDao weaponDao = new WeaponDao(connection);
                WeaponEntity weapon = new WeaponEntity();
                weapon.setName(nameField.getText());
                weapon.setType(weaponField.getText());
                weapon.setProvideHp(Integer.parseInt(provideHpField.getText()));
                weaponDao.addWeapon(weapon);
            } else if (tableName.equals("artifacts")) {
                ArtifactDao artifactDao = new ArtifactDao(connection);
                ArtifactEntity artifact = new ArtifactEntity();
                artifact.setName(nameField.getText());
                artifact.setIntroduce(introduceArea.getText());
                artifact.setProvideHp(Integer.parseInt(provideHpField.getText()));
                artifactDao.addArtifact(artifact);
            }
            showAlert("成功", "数据添加成功！");
        } catch (SQLException e) {
            showAlert("错误", "数据添加失败：" + e.getMessage());
        }
    }

    private String getTableName(String type) {
        switch (type) {
            case "角色":
                return "characters";
            case "武器":
                return "weapons";
            case "驱动盘":
                return "artifacts";
            default:
                return "characters";
        }
    }

    private void initDatabaseConnection() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}











