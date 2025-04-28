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

public class ModifyInterface extends Application {

    private Connection connection;
    private ComboBox<String> typeComboBox;
    private TextField idField;
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

        Button backButton = UIUtils.createStyledButton("返回", "#009688", FontAwesome.Glyph.ARROW_LEFT.toString());
        backButton.setOnAction(e -> stage.close());

        typeComboBox = UIUtils.createStyledComboBox("类型", "#009688");
        typeComboBox.getItems().addAll("角色", "武器", "驱动盘");
        typeComboBox.setValue("角色");

        idField = UIUtils.createStyledTextField("输入编号", "#009688");
        nameField = UIUtils.createStyledTextField("名称", "#F9D423");
        weaponField = UIUtils.createStyledTextField("武器", "#009688");
        artifactField = UIUtils.createStyledTextField("驱动盘", "#009688");
        introduceArea = UIUtils.createStyledTextArea("简要介绍", "#009688");
        provideHpField = UIUtils.createStyledTextField("提供生命值", "#009688");

        Button modifyButton = UIUtils.createStyledButton("修改", "#009688", FontAwesome.Glyph.PENCIL.toString());
        modifyButton.setOnAction(e -> updateData(typeComboBox.getValue()));

        VBox vbox = new VBox(30, backButton, typeComboBox, idField, nameField, weaponField, artifactField, introduceArea, provideHpField, modifyButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(70));

        StackPane root = new StackPane(vbox);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("修改");
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

    private void updateData(String type) {
        String tableName = getTableName(type);
        try {
            int provideHp = Integer.parseInt(provideHpField.getText());
            int id = Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            showAlert("错误", "请输入有效的数字！");
            return;
        }

        try {
            switch (tableName) {
                case "characters" -> {
                    CharacterDao characterDao = new CharacterDao(connection);
                    CharacterEntity character = characterDao.getCharacterById(Integer.parseInt(idField.getText()));
                    if (character != null) {
                        character.setName(nameField.getText());
                        character.setWeaponName(weaponField.getText());
                        character.setArtifactsName(artifactField.getText());
                        character.setIntroduce(introduceArea.getText());
                        character.setProvideHp(Integer.parseInt(provideHpField.getText()));
                        characterDao.updateCharacter(character);
                    } else {
                        showAlert("错误", "未找到对应的角色！");
                        return;
                    }
                }
                case "weapons" -> {
                    WeaponDao weaponDao = new WeaponDao(connection);
                    WeaponEntity weapon = weaponDao.getWeaponById(Integer.parseInt(idField.getText()));
                    if (weapon != null) {
                        weapon.setName(nameField.getText());
                        weapon.setType(weaponField.getText());
                        weapon.setProvideHp(Integer.parseInt(provideHpField.getText()));
                        weaponDao.updateWeapon(weapon);
                    } else {
                        showAlert("错误", "未找到对应的武器！");
                        return;
                    }
                }
                case "artifacts" -> {
                    ArtifactDao artifactDao = new ArtifactDao(connection);
                    ArtifactEntity artifact = artifactDao.getArtifactById(Integer.parseInt(idField.getText()));
                    if (artifact != null) {
                        artifact.setName(nameField.getText());
                        artifact.setIntroduce(introduceArea.getText());
                        artifact.setProvideHp(Integer.parseInt(provideHpField.getText()));
                        artifactDao.updateArtifact(artifact);
                    } else {
                        showAlert("错误", "未找到对应的驱动盘！");
                        return;
                    }
                }
            }

            showAlert("成功", "数据修改成功！");
        } catch (SQLException e) {
            showAlert("错误", "数据修改失败：" + e.getMessage());
        }
    }



    private String getTableName(String type) {
        return switch (type) {
            case "武器" -> "weapons";
            case "驱动盘" -> "artifacts";
            default -> "characters";
        };
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void initDatabaseConnection() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}