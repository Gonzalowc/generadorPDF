package generador.pdf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import generador.excepciones.CampoObligatorio;
import generador.extra.GeneradorPFDF;
import generador.objetos.Cliente;
import generador.objetos.Empresa;
import generador.objetos.Factura;
import generador.objetos.Servicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ServiciosController {
	@FXML
	private Label txtNombreCliente;
	@FXML
	private Label txtDireccionFiscal;
	@FXML
	private Label txtFecha;
	@FXML
	private Label txtDomicilio;
	@FXML
	private Label txtNIF;
	@FXML
	private TableView<Servicio> tablaServicios;
	@FXML
	private TableColumn<Integer, Servicio> cCantidad;
	@FXML
	private TableColumn<String, Servicio> cConcepto;
	@FXML
	private TableColumn<Double, Servicio> cPrecio;
	@FXML
	private TableColumn<Double, Servicio> cTotal;
	@FXML
	private TextField txtCantidad;
	@FXML
	private TextField txtPrecioUnidad;
	@FXML
	private TextField txtFactura;
	@FXML
	private TextArea txtConcepto;
	@FXML
	private TextField txtIva;
	@FXML
	private Label txtError;
	private static Scene scene;
	private static Stage stage;
	private static Empresa empresa;
	private static Cliente cliente;
	private LocalDate fecha;
	ArrayList<Servicio> servicesLista = new ArrayList<>();
	private ObservableList<Servicio> serviciosObservable = FXCollections.observableArrayList();

	@FXML
	public void addServicioTabla() throws CampoObligatorio {
		int cantidad = -1;
		double precio = -1;
		try {
			cantidad = Integer.parseInt(txtCantidad.getText());
		} catch (NumberFormatException e) {
			e.getStackTrace();
			throw new CampoObligatorio("Error al introducir numero", txtCantidad);
		}
		txtCantidad.setStyle(null);
		try {
			precio = Double.parseDouble(txtPrecioUnidad.getText());
		} catch (NumberFormatException e) {
			e.getStackTrace();
			throw new CampoObligatorio("Error al introducir numero", txtPrecioUnidad);
		}
		txtPrecioUnidad.setStyle(null);
		Servicio servicioSimple = new Servicio(cantidad, txtConcepto.getText(), precio);
		if (!serviciosObservable.contains(servicioSimple)) {
			serviciosObservable.add(servicioSimple);
		} else {
			txtError.setVisible(true);
			new Timer(false).schedule(new TimerTask() {
				@Override
				public void run() {
					txtError.setVisible(false);
				}
			}, 3000);
		}

		cCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		cConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
		cPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		cTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		tablaServicios.setItems(serviciosObservable);
		txtPrecioUnidad.setText(null);
		txtConcepto.setText(null);
		txtCantidad.setText(null);
	}

	@FXML
	public void back() {

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
			Stage nuevaVentana = new Stage();
			DatosRellenarController.setStage(nuevaVentana);
			nuevaVentana.setTitle("Facturar");
			Scene scene = new Scene(aplicacion);
			nuevaVentana.setScene(scene);
			stage.close();
			App.setStage(nuevaVentana);
			nuevaVentana.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void rellenarDatosCliente() {
		txtNombreCliente.setText(cliente.getNombre());
		String direccion = cliente.getCodigoPostal() + " " + cliente.getPueblo() + " (" + cliente.getCiudad() + ")";
		if (!direccion.contains("null")) {
			txtDireccionFiscal.setText(direccion);
		}
		txtDomicilio.setText(cliente.getDomicilio());
		if (cliente.isNIFaDNI()) {
			txtNIF.setText("DNI: " + cliente.getNif());
		} else {
			txtNIF.setText("NIF: " + cliente.getNif());
		}

		String fechaFormat = fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
		txtFecha.setText(fechaFormat);
	}

	@FXML
	private void crearPDF() {
		GeneradorPFDF pdf = new GeneradorPFDF();
		try {
			if (txtFactura.getText() == null) {
				Factura fact = new Factura(fecha);
				fact.setServicios(new ArrayList<Servicio>(serviciosObservable));
				if (txtIva.getText() != null) {
					fact.setIva(Double.parseDouble(txtIva.getText()));
				}
				pdf.generarPDF(empresa, cliente, fact);
			} else {
				Factura fact = new Factura(fecha, txtFactura.getText());
				fact.setServicios(new ArrayList<Servicio>(serviciosObservable));
				if (txtIva.getText() != null) {
					fact.setIva(Double.parseDouble(txtIva.getText()));
				}
				pdf.generarPDF(empresa, cliente, fact);
			}

		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void borrarDatoTabla() {
		int index = tablaServicios.getSelectionModel().selectedIndexProperty().get();
		System.out.println("INDEX: " + index);
		serviciosObservable.remove(index);
		tablaServicios.setItems(serviciosObservable);
	}

	public static Empresa getEmpresa() {
		return empresa;
	}

	public static void setEmpresa(Empresa empresa) {
		ServiciosController.empresa = empresa;
	}

	public static void setStage(Stage stage) {
		ServiciosController.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public static void setCliente(Cliente cliente) {
		ServiciosController.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
