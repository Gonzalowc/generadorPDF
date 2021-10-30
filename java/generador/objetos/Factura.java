package generador.objetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Factura implements Serializable {
	private LocalDate fechaFactura;
	private ArrayList<Servicio> servicios;
	private double total;
	private String factura;
	private double iva;
	
	
	public Factura(LocalDate fechaFactura, ArrayList<Servicio> servicios) {
		this.fechaFactura = fechaFactura;
		this.servicios = servicios;
		factura=null;
	}
	public Factura(LocalDate fechaFactura) {
		this.fechaFactura = fechaFactura;
		this.servicios = new ArrayList<Servicio>();
		factura=null;
	}
	
	public Factura(LocalDate fecha, String factura) {
		this(fecha);
		this.factura = factura;
	}
	public void addServicio(Servicio servicio) {
		if(!servicios.contains(servicio)) {
			servicios.add(servicio);
		}
		calcularTotal();
	}
	public void calcularTotal() {
		total=0;
		for(Servicio servicio:servicios) {
			total+=servicio.getTotal();
		}
	}
	
	public void deleleServicio(Servicio servicio) {
		servicios.remove(servicio);
		calcularTotal();
	}
	
	public LocalDate getFechaFactura() {
		return fechaFactura;
	}
	
	public void setFechaFactura(LocalDate fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
		calcularTotal();
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	@Override
	public String toString() {
		return "Factura [fechaFactura=" + fechaFactura + ", servicios=" + servicios + ", total=" + total + ", factura="
				+ factura + ", iva=" + iva + "]";
	}
	
	
}
