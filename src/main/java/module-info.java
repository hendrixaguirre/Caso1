
module ni.edu.uam.caso_1 {

    requires javafx.controls;
    requires javafx.fxml;

    opens ni.edu.uam.caso_1 to javafx.fxml;
    opens ni.edu.uam.caso_1.controller to javafx.fxml;

    exports ni.edu.uam.caso_1;
}
