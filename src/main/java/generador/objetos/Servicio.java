package generador.objetos;

import java.io.Serializable;

public class Servicio implements Comparable<Servicio>,Serializable {
	private int cantidad;
	private String concepto;
	private double precio;
	private double total;
	
	public Servicio(int cantidad, String concepto, double precio) {
		this.cantidad = cantidad;
		this.concepto = concepto;
		this.precio = precio;
		if(cantidad>1) {
			total = cantidad*precio;
		}else {
			total = precio;
		}
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concepto == null) ? 0 : concepto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		if (concepto == null) {
			if (other.concepto != null)
				return false;
		} else if (!concepto.equals(other.concepto))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Servicio [cantidad=" + cantidad + ", concepto=" + concepto + ", precio=" + precio + ", total=" + total
				+ "]";
	}
	
	@Override
	public int compareTo(Servicio o) {
		return cantidad-o.getCantidad();
	}
	
	
}
