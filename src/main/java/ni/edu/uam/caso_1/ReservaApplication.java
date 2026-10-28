package ni.edu.uam.caso_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReservaApplication.class.getResource("/ni/edu/uam/caso_1/reserva-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Reserva de Entradas");
        stage.setScene(scene);
        stage.show();
    }
}
