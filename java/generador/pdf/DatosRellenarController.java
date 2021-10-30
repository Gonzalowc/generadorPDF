package generador.pdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

import generador.excepciones.CampoObligatorio;
import generador.objetos.Cliente;
import generador.objetos.Empresa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DatosRellenarController {
	@FXML
	private TextField txtEmpresaNombreCompleto;
	@FXML
	private TextField txtEmpresaDNI;
	@FXML
	private TextField txtEmpresaNombreNegocio;
	@FXML
	private TextField txtEmpresaDireccion;
	@FXML
	private TextField txtEmpresaNCuenta;
	@FXML
	private DatePicker dateClienteFecha;
	@FXML
	private TextField txtClienteNombreCompleto;
	@FXML
	private TextField txtClienteDomicilio;
	@FXML
	private TextField txtClienteCPostal;
	@FXML
	private TextField txtClientePueblo;
	@FXML
	private TextField txtClienteCiudad;
	@FXML
	private TextField txtClienteNIF;
	@FXML
	private Button btnActualizarDatosEmpresa;
	@FXML
	private Button btnRellenarDatosFactura;
	@FXML
	private Label lblEmpresaActualizadoCorrecto;
	@FXML
	private Label lblEmpresaActualizadoFallo;
	@FXML
	private CheckBox checkDNI;

	private static Stage stage;
	private static Empresa empresa = new Empresa();
	private static Cliente cliente = null;

	public static void cargarDatos() {
		try (ObjectInputStream leer = new ObjectInputStream(
				new FileInputStream("src/main/java/generador/ficheros/empresa.car"))) {
			while (true) {
				empresa = (Empresa) leer.readObject();
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Empresa Cargada");
		}
	}

	public void asignarDatosEmpresa() {
		cargarDatos();
		if (empresa != null) {
			txtEmpresaNombreCompleto.setText(empresa.getNombreTrabajador());
			txtEmpresaDNI.setText(empresa.getDni());
			txtEmpresaNombreNegocio.setText(empresa.getNombreNegocio());
			txtEmpresaDireccion.setText(empresa.getDireccion());
			txtEmpresaNCuenta.setText(empresa.getnCuenta());
		}
		System.out.println("asignar Datos");
	}
	@FXML
	public void comprobarSiEsDNI() {
		System.out.println("Comprueba Si es DNI");
		if (checkDNI.isSelected()) {
			System.out.println("ES DNI");
			txtClienteNIF.setPromptText("D.N.I");
		}else {
			System.out.println("ES NIF");
			txtClienteNIF.setPromptText("N.I.F");
		}
	}

	@FXML
	public void actualizarDatosEmpresa() throws CampoObligatorio {
		boolean allOk = true;
		if (txtEmpresaNombreCompleto.getText() != null && !txtEmpresaNombreCompleto.getText().trim().equals("")) {
			empresa.setNombreTrabajador(txtEmpresaNombreCompleto.getText());
			txtEmpresaNombreCompleto.setStyle(null);
		} else {
			throw new CampoObligatorio("Nombre del trabajador vacio", txtEmpresaNombreCompleto);
		}
		if (txtEmpresaDNI.getText() != null && !txtEmpresaDNI.getText().trim().equals("")) {
			empresa.setDni(txtEmpresaDNI.getText());
			txtEmpresaDNI.setStyle(null);
		} else {
			throw new CampoObligatorio("DNI vacio", txtEmpresaDNI);
		}
		if (txtEmpresaNombreNegocio.getText() != null && !txtEmpresaNombreNegocio.getText().trim().equals("")) {
			empresa.setNombreNegocio(txtEmpresaNombreNegocio.getText());
			txtEmpresaNombreNegocio.setStyle(null);
		} else {
			throw new CampoObligatorio("Nombre de la empresa vacio", txtEmpresaNombreNegocio);
		}
		if (txtEmpresaDireccion.getText() != null && !txtEmpresaDireccion.getText().trim().equals("")) {
			empresa.setDireccion(txtEmpresaDireccion.getText());
			txtEmpresaDireccion.setStyle(null);
		} else {
			throw new CampoObligatorio("Direccion de la empresa vacio", txtEmpresaDireccion);
		}
		if (txtEmpresaNCuenta.getText() != null && !txtEmpresaNCuenta.getText().trim().equals("")) {
			empresa.setnCuenta(txtEmpresaNCuenta.getText());
			txtEmpresaNCuenta.setStyle(null);
		} else {
			throw new CampoObligatorio("Nº de cuenta vacio", txtEmpresaNCuenta);
		}
		if (allOk) {
			if (guardarDatos()) {
				lblEmpresaActualizadoCorrecto.setVisible(true);
				new Timer(false).schedule(new TimerTask() {
					@Override
					public void run() {
						lblEmpresaActualizadoCorrecto.setVisible(false);
					}
				}, 3000);
			} else {
				lblEmpresaActualizadoFallo.setVisible(true);
				new Timer(false).schedule(new TimerTask() {
					@Override
					public void run() {
						lblEmpresaActualizadoFallo.setVisible(false);
					}
				}, 3000);
			}
			asignarDatosEmpresa();
		}

	}

	public static boolean guardarDatos() {
		try {
			ObjectOutputStream escribir = new ObjectOutputStream(
					new FileOutputStream("src/main/java/generador/ficheros/empresa.car"));
			escribir.writeObject(empresa);
			escribir.close();
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@FXML
	public void rellenarDatosCliente() throws CampoObligatorio {
		String nombre = null;
		LocalDate fecha = null;
		if (txtClienteNombreCompleto.getText() != null && !txtClienteNombreCompleto.getText().trim().equals("")) {
			nombre = txtClienteNombreCompleto.getText();
		} else {
			throw new CampoObligatorio("Nombre del Cliente vacio", txtClienteNombreCompleto);
		}
		if (dateClienteFecha.getValue() != null) {
			fecha = dateClienteFecha.getValue();
		} else {
			throw new CampoObligatorio("Fecha de la factura vacio", dateClienteFecha);
		}

		cliente = new Cliente(nombre, fecha);
		if (txtClienteDomicilio.getText() != null) {
			cliente.setDomicilio(txtClienteDomicilio.getText());
		}
		txtClientePueblo.setStyle(null);
		txtClienteCPostal.setStyle(null);
		txtClienteCiudad.setStyle(null);
		if (!((txtClienteCPostal.getText() == null || txtClienteCPostal.getText().trim().equals(""))
				&& (txtClienteCiudad.getText() == null || txtClienteCiudad.getText().trim().equals(""))
				&& (txtClientePueblo.getText() == null || txtClientePueblo.getText().trim().equals("")))) {
			if (txtClienteCPostal.getText() == null || txtClienteCPostal.getText().trim().equals("")) {
				throw new CampoObligatorio("Codigo Postal vacio", txtClienteCPostal);
			}
			if (txtClienteCiudad.getText() == null || txtClienteCiudad.getText().trim().equals("")) {
				throw new CampoObligatorio("Ciudad del cliente vacio", txtClienteCiudad);
			}
			if (txtClientePueblo.getText() == null || txtClientePueblo.getText().trim().equals("")) {
				throw new CampoObligatorio("Puebli cliente vacio", txtClientePueblo);
			}
			cliente.setCodigoPostal(txtClienteCPostal.getText());
			cliente.setCiudad(txtClienteCiudad.getText());
			cliente.setPueblo(txtClientePueblo.getText());
		}
		if (txtClienteNIF.getText() != null) {
			if (checkDNI.isSelected()) {
				cliente.setNIFaDNI(true);
			}
			cliente.setNif(txtClienteNIF.getText());
		}
		System.out.println("RellenarDatos FInal Metodo");
		try {
			paginaSiguiente();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void paginaSiguiente() throws IOException {
		// crear la clase que controla el archivo FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Servicios.fxml"));
		// creamos el panel a partir del loader
		AnchorPane servicios = (AnchorPane) loader.load();
		ServiciosController serviciosController = loader.<ServiciosController>getController();
		// usamos sus metodos
		ServiciosController.setCliente(cliente);
		ServiciosController.setEmpresa(empresa);
		ServiciosController.setStage(stage);
		serviciosController.setFecha(dateClienteFecha.getValue());
		serviciosController.rellenarDatosCliente();
		Stage nuevaVentana = new Stage();
		ServiciosController.setStage(nuevaVentana);
		nuevaVentana.setTitle("Facturar");
		Scene scene = new Scene(servicios);
		nuevaVentana.setScene(scene);
		stage.close();
		App.setStage(nuevaVentana);
		nuevaVentana.show();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		DatosRellenarController.stage = stage;
	}

	public static Empresa getEmpresa() {
		return empresa;
	}

	public static void setEmpresa(Empresa empresa) {
		DatosRellenarController.empresa = empresa;
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public static void setCliente(Cliente cliente) {
		DatosRellenarController.cliente = cliente;
	}

}
