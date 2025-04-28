package com.huyuxuan.zzz;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QueryInterface extends Application {

    private Connection connection;
    private TableView<QueryResult> tableView;
    private TextField idField;
    private TextField nameField;
    private ImageView imageView;

    @Override
    public void start(Stage stage) {
        Image backgroundImage = new Image(getClass().getResource("/com/huyuxuan/zzz/xuan.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        String iconPath = "zzz.png";
        Image icon = new Image(getClass().getResource("/com/huyuxuan/zzz/" + iconPath).toExternalForm());
        stage.getIcons().add(icon);

        Button backButton = UIUtils.createStyledButton("返回", "#0000FF", FontAwesome.Glyph.ARROW_LEFT.toString());
        backButton.setOnAction(e -> stage.close());

        ComboBox<String> typeComboBox = UIUtils.createStyledComboBox("类型", "#0000FF");
        typeComboBox.getItems().addAll("角色", "武器", "驱动盘");
        typeComboBox.setValue("角色");

        idField = UIUtils.createStyledTextField("输入编号", "#0000FF");
        nameField = UIUtils.createStyledTextField("输入名称", "#0000FF");

        imageView = new ImageView();
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setVisible(false);

        VBox inputBox = new VBox(10, backButton, typeComboBox, idField, nameField);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(20));

        Button queryButton = UIUtils.createStyledButton("查询", "#0000FF", FontAwesome.Glyph.SEARCH.toString());
        queryButton.setOnAction(e -> loadTableData(typeComboBox.getValue()));

        HBox queryButtonBox = new HBox(queryButton);
        queryButtonBox.setAlignment(Pos.CENTER);
        queryButtonBox.setPadding(new Insets(10, 0, 0, 0));

        tableView = new TableView<>();
        tableView.setEditable(false);

        TableColumn<QueryResult, String> idColumn = new TableColumn<>(" ");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<QueryResult, String> nameColumn = new TableColumn<>(" ");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<QueryResult, String> field1Column = new TableColumn<>(" ");
        field1Column.setCellValueFactory(new PropertyValueFactory<>("field1"));

        TableColumn<QueryResult, String> field2Column = new TableColumn<>(" ");
        field2Column.setCellValueFactory(new PropertyValueFactory<>("field2"));

        TableColumn<QueryResult, String> field3Column = new TableColumn<>(" ");
        field3Column.setCellValueFactory(new PropertyValueFactory<>("field3"));

        TableColumn<QueryResult, String> field4Column = new TableColumn<>(" ");
        field4Column.setCellValueFactory(new PropertyValueFactory<>("field4"));

        
        tableView.getColumns().addAll(idColumn, nameColumn, field1Column, field2Column, field3Column, field4Column);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new VBox(inputBox, queryButtonBox));
        borderPane.setCenter(tableView);
        borderPane.setRight(imageView);
        BorderPane.setAlignment(imageView, Pos.TOP_RIGHT);
        BorderPane.setMargin(imageView, new Insets(20));

        StackPane root = new StackPane(borderPane);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("查询");
        stage.setScene(scene);
        stage.show();

        initDatabaseConnection();
    }

    private void loadTableData(String queryType) {
        new Thread(() -> {
            try {
                ObservableList<QueryResult> data = FXCollections.<QueryResult>observableArrayList();
                String tableName = getTableName(queryType);
                String id = idField.getText();
                String name = nameField.getText();


                Platform.runLater(() -> {
                    tableView.getColumns().clear();
                    if (tableName.equals("characters")) {
                        TableColumn<QueryResult, String> idColumn = new TableColumn<>("编号");
                        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                        TableColumn<QueryResult, String> nameColumn = new TableColumn<>("角色名");
                        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                        TableColumn<QueryResult, String> weaponColumn = new TableColumn<>("武器");
                        weaponColumn.setCellValueFactory(new PropertyValueFactory<>("field1"));

                        TableColumn<QueryResult, String> artifactColumn = new TableColumn<>("驱动盘");
                        artifactColumn.setCellValueFactory(new PropertyValueFactory<>("field2"));

                        TableColumn<QueryResult, String> introduceColumn = new TableColumn<>("简要介绍");
                        introduceColumn.setCellValueFactory(new PropertyValueFactory<>("field3"));

                        TableColumn<QueryResult, String> hpColumn = new TableColumn<>("基础生命值");
                        hpColumn.setCellValueFactory(new PropertyValueFactory<>("field4"));

                        tableView.getColumns().addAll(idColumn, nameColumn, weaponColumn, artifactColumn, introduceColumn, hpColumn);
                    } else if (tableName.equals("weapons")) {
                        TableColumn<QueryResult, String> idColumn = new TableColumn<>("编号");
                        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                        TableColumn<QueryResult, String> nameColumn = new TableColumn<>("武器名");
                        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                        TableColumn<QueryResult, String> typeColumn = new TableColumn<>("武器类型");
                        typeColumn.setCellValueFactory(new PropertyValueFactory<>("field1"));

                        TableColumn<QueryResult, String> hpColumn = new TableColumn<>("提供生命值");
                        hpColumn.setCellValueFactory(new PropertyValueFactory<>("field2"));

                        tableView.getColumns().addAll(idColumn, nameColumn, typeColumn, hpColumn);
                    } else if (tableName.equals("artifacts")) {

                        TableColumn<QueryResult, String> idColumn = new TableColumn<>("编号");
                        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                        TableColumn<QueryResult, String> nameColumn = new TableColumn<>("驱动盘名");
                        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                        TableColumn<QueryResult, String> attributeColumn = new TableColumn<>("属性");
                        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("field1"));

                        TableColumn<QueryResult, String> hpColumn = new TableColumn<>("提供生命值");
                        hpColumn.setCellValueFactory(new PropertyValueFactory<>("field2"));

                        tableView.getColumns().addAll(idColumn, nameColumn, attributeColumn, hpColumn);
                    }
                });

                switch (tableName) {
                    case "characters" -> {
                        CharacterDao characterDao = new CharacterDao(connection);
                        List<CharacterEntity> characters = characterDao.getAllCharacters();
                        for (CharacterEntity character : characters) {
                            if (matchesFilter(character, id, name)) {
                                data.add(new QueryResult(
                                        character.getId().toString(),
                                        character.getName(),
                                        character.getWeaponName(),
                                        character.getArtifactsName(),
                                        character.getIntroduce(),
                                        character.getProvideHp().toString(),
                                        character.getImageUrl()
                                ));

                                Platform.runLater(() -> {
                                    if (character.getImageUrl() != null && !character.getImageUrl().isEmpty()) {
                                        String imageUrl = character.getImageUrl();
                                        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
                                        imageView.setImage(image);
                                        imageView.setVisible(true);
                                    } else {
                                        imageView.setVisible(false);
                                    }
                                });
                            }
                        }

                        if (data.isEmpty()) {
                            Platform.runLater(() -> imageView.setVisible(false));
                        }
                    }
                    case "weapons" -> {
                        WeaponDao weaponDao = new WeaponDao(connection);
                        List<WeaponEntity> weapons = weaponDao.getAllWeapons();
                        for (WeaponEntity weapon : weapons) {
                            if (matchesFilter(weapon, id, name)) {
                                data.add(new QueryResult(
                                        weapon.getId().toString(),
                                        weapon.getName(),
                                        weapon.getType(),
                                        weapon.getProvideHp().toString()
                                ));
                            }
                        }

                        Platform.runLater(() -> imageView.setVisible(false));
                    }
                    case "artifacts" -> {
                        ArtifactDao artifactDao = new ArtifactDao(connection);
                        List<ArtifactEntity> artifacts = artifactDao.getAllArtifacts();
                        for (ArtifactEntity artifact : artifacts) {
                            if (matchesFilter(artifact, id, name)) {
                                data.add(new QueryResult(
                                        artifact.getId().toString(),
                                        artifact.getName(),
                                        artifact.getIntroduce(),
                                        artifact.getProvideHp().toString()
                                ));
                            }
                        }

                        Platform.runLater(() -> imageView.setVisible(false));
                    }
                }

                Platform.runLater(() -> tableView.setItems(data));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean matchesFilter(Object entity, String id, String name) {
        if (entity instanceof CharacterEntity character) {
            return (id.isEmpty() || character.getId().toString().equals(id)) &&
                   (name.isEmpty() || character.getName().contains(name));
        } else if (entity instanceof WeaponEntity weapon) {
            return (id.isEmpty() || weapon.getId().toString().equals(id)) &&
                   (name.isEmpty() || weapon.getName().contains(name));
        } else if (entity instanceof ArtifactEntity artifact) {
            return (id.isEmpty() || artifact.getId().toString().equals(id)) &&
                   (name.isEmpty() || artifact.getName().contains(name));
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

    private String getTableName(String queryType) {
        switch (queryType) {
            case "武器":
                return "weapons";
            case "驱动盘":
                return "artifacts";
            case "角色":
            default:
                return "characters";
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static class QueryResult {
        private final String id;
        private final String name;
        private final String field1;
        private final String field2;
        private final String field3;
        private final String field4;
        private final String imageUrl;

        public QueryResult(String id, String name, String field1, String field2, String field3, String field4, String imageUrl) {
            this.id = id;
            this.name = name;
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.imageUrl = imageUrl;
        }

        public QueryResult(String id, String name, String field1, String field2) {
            this(id, name, field1, field2, null, null, null);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }

        public String getField3() {
            return field3;
        }

        public String getField4() {
            return field4;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}