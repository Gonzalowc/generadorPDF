package generador.excepciones;

import javafx.scene.control.Control;

public class CampoObligatorio extends Exception {
	

	public CampoObligatorio(String message, Control control) {
		super(message);
		control.setStyle("-fx-background-color: #f6baba;");
	}

}
