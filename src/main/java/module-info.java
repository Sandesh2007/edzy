module com.edzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens com.edzy to javafx.fxml;
    exports com.edzy;
}
