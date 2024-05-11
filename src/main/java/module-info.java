module com.fede.nicole.domandetolc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens com.fede.nicole.domandetolc to javafx.fxml;
    opens com.fede.nicole.domandetolc.Controllers to javafx.fxml;
    exports com.fede.nicole.domandetolc;
    exports com.fede.nicole.domandetolc.Controllers to javafx.fxml;
}