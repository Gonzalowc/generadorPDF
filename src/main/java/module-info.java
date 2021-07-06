module generador.pdf{
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires itextpdf;
	
	opens generador.pdf to javafx.fxml;
	exports generador.pdf;
}