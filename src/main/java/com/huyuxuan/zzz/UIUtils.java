package com.huyuxuan.zzz;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.glyphfont.Glyph;

public class UIUtils {

    public static Button createStyledButton(String text, String textColor, String iconCode) {
        Button button = new Button(text);
        button.getStyleClass().add("btn");
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: " + textColor + "; -fx-font-size: 24px; -fx-font-family: 'Segoe UI', Arial, sans-serif; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 15 25 15 25; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        button.setPrefSize(180, 60);

        Glyph icon = new Glyph("FontAwesome", iconCode);
        icon.setFontSize(30);
        icon.setColor(Color.web(textColor));
        button.setGraphic(icon);

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-text-fill: " + textColor + "; -fx-font-size: 28px; -fx-font-weight: bold;");
            FadeTransition fade = new FadeTransition(Duration.millis(200), button);
            fade.setFromValue(1.0);
            fade.setToValue(0.8);
            fade.play();
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: " + textColor + "; -fx-font-size: 24px; -fx-font-weight: bold;");
        });

        return button;
    }

    public static ComboBox<String> createStyledComboBox(String promptText, String textColor) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(promptText);
        comboBox.setStyle("-fx-background-color: transparent; -fx-border-color: " + textColor + "; -fx-border-radius: 5; -fx-text-fill: " + textColor + "; -fx-font-size: 18px; -fx-min-width: 200px; -fx-min-height: 40px; -fx-prompt-text-fill: " + textColor + ";");
        return comboBox;
    }

    public static TextField createStyledTextField(String promptText, String textColor) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-border-color: " + textColor + "; -fx-border-radius: 5; -fx-text-fill: " + textColor + "; -fx-font-size: 16px;");
        return textField;
    }

    public static TextArea createStyledTextArea(String promptText, String textColor) {
        TextArea textArea = new TextArea();
        textArea.setPromptText(promptText);
        textArea.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-border-color: " + textColor + "; -fx-border-radius: 5; -fx-text-fill: " + textColor + "; -fx-font-size: 16px;");
        return textArea;
    }
}