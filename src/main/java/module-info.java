module com.example.dictionary_ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dictionary_ui to javafx.fxml;
    exports com.example.dictionary_ui;
}