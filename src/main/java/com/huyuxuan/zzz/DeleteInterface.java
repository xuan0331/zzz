package com.huyuxuan.zzz;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class DeleteInterface extends Application {

    private Connection connection;
    private ComboBox<String> typeComboBox;
    private TextField idField;
    private TextField nameField;

    @Override
    public void start(Stage stage) {
        Image backgroundImage = new Image(getClass().getResource("/com/huyuxuan/zzz/xuan.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        String iconPath = "zzz.png";
        Image icon = new Image(Objects.requireNonNull(getClass().getResource(iconPath)).toExternalForm());
        stage.getIcons().add(icon);

        Button backButton = UIUtils.createStyledButton("返回", "#FF6B6B", FontAwesome.Glyph.ARROW_LEFT.toString());
        backButton.setOnAction(e -> stage.close());

        typeComboBox = UIUtils.createStyledComboBox("类型", "#FF6B6B");
        typeComboBox.getItems().addAll("角色", "武器", "驱动盘");
        typeComboBox.setValue("角色");

        idField = UIUtils.createStyledTextField("输入编号", "#FF6B6B");
        nameField = UIUtils.createStyledTextField("输入名称", "#FF6B6B");

        Button deleteButton = UIUtils.createStyledButton("删除", "#FF6B6B", FontAwesome.Glyph.TRASH.toString());
        deleteButton.setOnAction(e -> deleteData(typeComboBox.getValue()));

        VBox vbox = new VBox(30, backButton, typeComboBox, idField, nameField, deleteButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(70));

        StackPane root = new StackPane(vbox);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("删除");
        stage.setScene(scene);
        stage.show();

        initDatabaseConnection();

        typeComboBox.setOnAction(e -> updateFieldPrompts(typeComboBox.getValue(), nameField));
    }

    private void updateFieldPrompts(String type, TextField nameField) {
        if (type.equals("角色")) {
            nameField.setPromptText("角色名");
        } else if (type.equals("武器")) {
            nameField.setPromptText("武器名");
        } else if (type.equals("驱动盘")) {
            nameField.setPromptText("驱动盘名");
        }
    }

    private void deleteData(String type) {
        String tableName = getTableName(type);
        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        if (id.isEmpty() && name.isEmpty()) {
            showAlert("错误", "编号和名称不能同时为空！");
            return;
        }

        boolean deleted = false;

        try {
            switch (tableName) {
                case "characters" -> {
                    CharacterDao characterDao = new CharacterDao(connection);
                    List<CharacterEntity> characters = characterDao.getAllCharacters();
                    for (CharacterEntity character : characters) {
                        if (matchesFilter(character, id, name)) {
                            characterDao.deleteCharacter(character.getId());
                            deleted = true;
                        }
                    }
                }
                case "weapons" -> {
                    WeaponDao weaponDao = new WeaponDao(connection);
                    List<WeaponEntity> weapons = weaponDao.getAllWeapons();
                    for (WeaponEntity weapon : weapons) {
                        if (matchesFilter(weapon, id, name)) {
                            weaponDao.deleteWeapon(weapon.getId());
                            deleted = true;
                        }
                    }
                }
                case "artifacts" -> {
                    ArtifactDao artifactDao = new ArtifactDao(connection);
                    List<ArtifactEntity> artifacts = artifactDao.getAllArtifacts();
                    for (ArtifactEntity artifact : artifacts) {
                        if (matchesFilter(artifact, id, name)) {
                            artifactDao.deleteArtifact(artifact.getId());
                            deleted = true;
                        }
                    }
                }
            }

            if (deleted) {
                showAlert("成功", "数据删除成功！");
            } else {
                showAlert("错误", "未找到匹配的数据！");
            }
        } catch (SQLException e) {
            showAlert("错误", "数据删除失败：" + e.getMessage());
        }
    }

    private boolean matchesFilter(Object entity, String id, String name) {
        if (entity instanceof CharacterEntity character) {
            return (id.isEmpty() || character.getId().toString().equals(id)) &&
               (name.isEmpty() || character.getName().equals(name));
        } else if (entity instanceof WeaponEntity weapon) {
            return (id.isEmpty() || weapon.getId().toString().equals(id)) &&
               (name.isEmpty() || weapon.getName().equals(name));
        } else if (entity instanceof ArtifactEntity artifact) {
            return (id.isEmpty() || artifact.getId().toString().equals(id)) &&
               (name.isEmpty() || artifact.getName().equals(name));
        }
        return false;
    }

    private void initDatabaseConnection() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        launch();
    }
}