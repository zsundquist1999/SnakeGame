module wsu.edu {
    requires javafx.controls;
    requires javafx.fxml;

    opens wsu.edu to javafx.fxml;
    exports wsu.edu;
}