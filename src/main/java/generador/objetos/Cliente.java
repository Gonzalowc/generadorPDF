package generador.objetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente implements Serializable {
	private String nombre;
	private String domicilio;
	private String codigoPostal;
	private String ciudad;
	private String pueblo;
	private String nif;
	private boolean isNIFaDNI;
	private ArrayList<Factura> facturas;
	
	public Cliente(String nombre,LocalDate fecha) {
		this.nombre = nombre;
		this.domicilio = null;
		this.codigoPostal = null;
		this.ciudad = null;
		this.pueblo = null;
		this.nif = null;
		isNIFaDNI=false;
		this.facturas = new ArrayList<Factura>();
		facturas.add(new Factura(fecha));
	}
	public Factura getFacturaPorFecha(LocalDate fecha) {
		for(Factura factura : facturas) {
			if(factura.getFechaFactura()==fecha) {
				return factura;
			}
		}
		return null;
	}
	
	public Cliente(String nombre, String domicilio, String codigoPostal, String ciudad, String pueblo, String nif,
			ArrayList<Factura> facturas) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.codigoPostal = codigoPostal;
		this.ciudad = ciudad;
		this.pueblo = pueblo;
		this.nif = nif;
		isNIFaDNI=false;
		this.facturas = facturas;
	}
	public Cliente(String nombre, String domicilio, String codigoPostal, String ciudad, String pueblo, String nif) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.codigoPostal = codigoPostal;
		this.ciudad = ciudad;
		this.pueblo = pueblo;
		this.nif = nif;
		isNIFaDNI=false;
		this.facturas = new ArrayList<Factura>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPueblo() {
		return pueblo;
	}
	public void setPueblo(String pueblo) {
		this.pueblo = pueblo;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public boolean isNIFaDNI() {
		return isNIFaDNI;
	}
	public void setNIFaDNI(boolean isNIFaDNI) {
		this.isNIFaDNI = isNIFaDNI;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", domicilio=" + domicilio + ", codigoPostal=" + codigoPostal + ", ciudad="
				+ ciudad + ", pueblo=" + pueblo + ", nif=" + nif + ", isNIFaDNI=" + isNIFaDNI + ", facturas=" + facturas
				+ "]";
	}
	
	
}
