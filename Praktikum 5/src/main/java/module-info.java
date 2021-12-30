module de.example.Praktikum5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;

    opens de.example.praktikum5.bank to com.google.gson;
    exports de.example.praktikum5;
    opens de.example.praktikum5 to com.google.gson, javafx.fxml;
}