package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;


public class Main extends Application {

    private TextArea messageTextField;
    private TextField shiftTextField;
    private Label resultLabel;
    Encryptor encryptor = new Encryptor();

    @Override
    public void start(Stage primaryStage) {
        Label shiftLabel = new Label("Wartość przesunięcia");
        shiftTextField = new TextField();
        Label promptLabel = new Label("Wpisz wiadomość do zaszyfrowania: ");
        messageTextField = new TextArea();
        messageTextField.setMaxSize(400, 60);
        Button encodeButton = new Button("Zaszyfruj");
        Button decodeButton = new Button("Odszyfruj");
        Button copyResult = new Button("Kopiuj");
        copyResult.setAlignment(Pos.BASELINE_RIGHT);
        encodeButton.setOnAction(new EncodeButtonHandler());
        decodeButton.setOnAction(new DecodeButtonHandler());
        copyResult.setOnAction(new CopyButtonHandler());
        resultLabel = new Label();
        HBox hShiftBox = new HBox(5, shiftLabel, shiftTextField);
        VBox vButtonBox = new VBox(5, encodeButton, decodeButton);
        vButtonBox.setAlignment(Pos.TOP_RIGHT);
        HBox hMessageBox = new HBox(10, promptLabel, messageTextField, vButtonBox);
        HBox hResultBox = new HBox(20, resultLabel, copyResult);
        hResultBox.setAlignment(Pos.BASELINE_RIGHT);

        VBox vBox = new VBox(10, hShiftBox, hMessageBox, hResultBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Szyfr cezara");
        primaryStage.show();
    }

    private boolean shiftTextValidator(TextField textField) {
        if (textField.getText().isBlank()) {
            resultLabel.setText("Prosze podać wartość przesunięcia");
            return false;
        } else if (!textField.getText().matches("[1-9]")) {
            resultLabel.setText("Wartość przesunięcia musi byc liczbą całkowitą z przediału 1-9 ");
            return false;
        } else return true;
    }

    public static void main(String[] args) {
        launch(args);
    }

    class EncodeButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            String messageText = String.valueOf(messageTextField.getText());
            if (shiftTextValidator(shiftTextField)) {
                int shift = Integer.parseInt(shiftTextField.getText());
                encryptor.setShift(shift);
                resultLabel.setText(encryptor.encodeText(messageText));
            }
        }
    }

    class DecodeButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            String messageText = String.valueOf(messageTextField.getText());
            if (shiftTextValidator(shiftTextField)) {
                int shift = Integer.parseInt(shiftTextField.getText());
                encryptor.setShift(shift);
                resultLabel.setText(encryptor.decodeText(messageText));
            }
        }
    }

    class CopyButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(resultLabel.getText());
            clipboard.setContent(content);
        }
    }

}
