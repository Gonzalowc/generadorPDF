package generador.pdf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private static Stage stage;

	@Override
	public void start(Stage stage) throws IOException {
		try {
			// crear la clase que controla el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GeneradorPDF.fxml"));
			// creamos el panel a partir del loader
			AnchorPane aplicacion = (AnchorPane) loader.load();
			// creamos el objeto controlador que queremos usar
			DatosRellenarController datosRellenarController = loader.<DatosRellenarController>getController();
			// usamos sus metodos
			DatosRellenarController.setStage(stage);
			datosRellenarController.asignarDatosEmpresa();
			scene = new Scene(aplicacion);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		App.stage = stage;
	}
	

}