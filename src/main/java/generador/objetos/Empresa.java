package generador.objetos;
import java.io.Serializable;

public class Empresa implements Serializable{

	private String nombreTrabajador;
	private String dni;
	private String nombreNegocio;
	private String direccion;
	private String nCuenta;
	
	public Empresa(String nombreTrabajador, String dni, String nombreNegocio, String direccion, String nCuenta) {
		this.nombreTrabajador = nombreTrabajador;
		this.dni = dni;
		this.nombreNegocio = nombreNegocio;
		this.direccion = direccion;
		this.nCuenta = nCuenta;
	}
	public Empresa() {
		this.nombreTrabajador = "";
		this.dni = "";
		this.nombreNegocio = "";
		this.direccion = "";
		this.nCuenta = "";
	}

	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreNegocio() {
		return nombreNegocio;
	}

	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getnCuenta() {
		return nCuenta;
	}

	public void setnCuenta(String nCuenta) {
		this.nCuenta = nCuenta;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nCuenta == null) ? 0 : nCuenta.hashCode());
		result = prime * result + ((nombreNegocio == null) ? 0 : nombreNegocio.hashCode());
		result = prime * result + ((nombreTrabajador == null) ? 0 : nombreTrabajador.hashCode());
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
		Empresa other = (Empresa) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (nCuenta == null) {
			if (other.nCuenta != null)
				return false;
		} else if (!nCuenta.equals(other.nCuenta))
			return false;
		if (nombreNegocio == null) {
			if (other.nombreNegocio != null)
				return false;
		} else if (!nombreNegocio.equals(other.nombreNegocio))
			return false;
		if (nombreTrabajador == null) {
			if (other.nombreTrabajador != null)
				return false;
		} else if (!nombreTrabajador.equals(other.nombreTrabajador))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Empresa [nombreTrabajador=" + nombreTrabajador + ", dni=" + dni + ", nombreNegocio=" + nombreNegocio
				+ ", direccion=" + direccion + ", nCuenta=" + nCuenta + "]";
	}
	
	
}
