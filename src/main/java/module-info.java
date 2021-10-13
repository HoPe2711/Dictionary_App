module com.example.dictionary_ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;
  //requires javafx.graphics;

    opens com.example.dictionary_ui to javafx.fxml;
    opens com.example.dictionary_ui.controller to javafx.fxml;
    exports com.example.dictionary_ui;
    exports com.example.dictionary_ui.controller;
}