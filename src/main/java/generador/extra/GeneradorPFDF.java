package generador.extra;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import generador.objetos.Cliente;
import generador.objetos.Factura;
import generador.objetos.Empresa;
import generador.objetos.Servicio;


public class GeneradorPFDF {
	public String generarPDF(Empresa empresa, Cliente cliente, Factura factura)
			throws URISyntaxException, MalformedURLException, IOException {
		Document documento = new Document();
		try {
			System.out.println(empresa.toString()+"\n"+cliente.toString()+"\n"+factura);

			String ruta = System.getProperty("user.home");
			String rutaCompleta = ruta + "\\Documents\\" + cliente.getNombre() + factura.getFechaFactura() + ".pdf";
			System.out.println("Ruta Completa: " + ruta + " \n" + rutaCompleta);
			PdfWriter.getInstance(documento, new FileOutputStream(rutaCompleta));
			float[] tam = { 130F, 100F, 100F, 100F, 100F, 50F, 100F, 100F, 100F, 100F };
			PdfPTable tabla = new PdfPTable(10);
			tabla.setWidths(tam);
			tabla.setWidthPercentage(100);
			documento.open();
			/* Fuente */
			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
			/* Datos empresa */
			PdfPCell nombreEmpresa = new PdfPCell(new Phrase(empresa.getNombreTrabajador().toUpperCase(), boldFont));
			nombreEmpresa.setColspan(8);
			nombreEmpresa.setPadding(8f);
			nombreEmpresa.setBorder(Rectangle.NO_BORDER);
			nombreEmpresa.setRowspan(2);
			nombreEmpresa.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla.addCell(nombreEmpresa);
			PdfPCell fecha = new PdfPCell(new Phrase(factura.getFechaFactura().toString(), normalFont));
			fecha.setColspan(2);
			fecha.setPadding(8f);
			fecha.setBorder(Rectangle.NO_BORDER);
			fecha.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			if (factura.getFactura() != null && !factura.getFactura().trim().equals("")) {
				fecha.setRowspan(1);
				PdfPCell nFactura = new PdfPCell(new Phrase("Factura: " + factura.getFactura(), normalFont));
				nFactura.setColspan(2);
				nFactura.setBorder(Rectangle.NO_BORDER);
				nFactura.setHorizontalAlignment(Element.ALIGN_CENTER);
				nFactura.setPadding(8f);
				tabla.addCell(fecha);
				tabla.addCell(nFactura);
			}else {
				fecha.setRowspan(2);
				tabla.addCell(fecha);
			}
			
			PdfPCell dniStatic = new PdfPCell(new Phrase("DNI:", boldFont));
			dniStatic.setColspan(2);
			dniStatic.setPadding(8f);
			dniStatic.setBorder(Rectangle.NO_BORDER);
			dniStatic.setHorizontalAlignment(Element.ALIGN_LEFT);
			tabla.addCell(dniStatic);
			PdfPCell dni = new PdfPCell(new Phrase(empresa.getDni().toUpperCase(), normalFont));
			dni.setColspan(8);
			dni.setBorder(Rectangle.NO_BORDER);
			dni.setPadding(8f);
			dni.setHorizontalAlignment(Element.ALIGN_LEFT);
			tabla.addCell(dni);
			PdfPCell tipoNegocio = new PdfPCell(new Phrase(empresa.getNombreNegocio().toUpperCase(), boldFont));
			tipoNegocio.setColspan(10);
			tipoNegocio.setPadding(8f);
			tipoNegocio.setBorder(Rectangle.NO_BORDER);
			tipoNegocio.setHorizontalAlignment(Element.ALIGN_LEFT);
			tabla.addCell(tipoNegocio);
			PdfPCell direccion = new PdfPCell(new Phrase(empresa.getDireccion().toUpperCase(), boldFont));
			direccion.setColspan(10);
			direccion.setPadding(8f);
			direccion.setBorder(Rectangle.NO_BORDER);
			direccion.setHorizontalAlignment(Element.ALIGN_LEFT);
			tabla.addCell(direccion);
			/* Datos Cliente */
			PdfPCell clienteNombreEstatic = new PdfPCell(new Phrase("Cliente: ", boldFont));
			clienteNombreEstatic.setColspan(2);
			clienteNombreEstatic.setPadding(7f);
			clienteNombreEstatic.setBorder(Rectangle.NO_BORDER);
			clienteNombreEstatic.setHorizontalAlignment(Element.ALIGN_LEFT);
			tabla.addCell(clienteNombreEstatic);
			PdfPCell clienteNombre = new PdfPCell(new Phrase(cliente.getNombre(), normalFont));
			clienteNombre.setColspan(8);
			clienteNombre.setPadding(8f);
			clienteNombre.setBorder(Rectangle.NO_BORDER);
			clienteNombre.setHorizontalAlignment(Element.ALIGN_LEFT);
			tabla.addCell(clienteNombre);

			if (cliente.getDomicilio() != null && !cliente.getDomicilio().trim().equals("")) {
				PdfPCell clienteDirStatic = new PdfPCell(new Phrase("Domicilio: ", boldFont));
				clienteDirStatic.setColspan(2);
				clienteDirStatic.setPadding(7f);
				clienteDirStatic.setBorder(Rectangle.NO_BORDER);
				clienteDirStatic.setHorizontalAlignment(Element.ALIGN_LEFT);
				tabla.addCell(clienteDirStatic);

				PdfPCell clienteDir = new PdfPCell(new Phrase(cliente.getDomicilio()));
				clienteDir.setColspan(8);
				clienteDir.setPadding(8f);
				clienteDir.setBorder(Rectangle.NO_BORDER);
				clienteDir.setHorizontalAlignment(Element.ALIGN_LEFT);
				tabla.addCell(clienteDir);
			}
			if (cliente.getCodigoPostal() != null && cliente.getCiudad() != null && cliente.getPueblo() != null) {
				String pueblo = cliente.getCodigoPostal() + " " + cliente.getPueblo() + " (" + cliente.getCiudad()
						+ ")";
				PdfPCell clientePueblo = new PdfPCell(new Phrase(pueblo));
				clientePueblo.setColspan(10);
				clientePueblo.setPadding(8f);
				clientePueblo.setBorder(Rectangle.NO_BORDER);
				clientePueblo.setHorizontalAlignment(Element.ALIGN_LEFT);
				tabla.addCell(clientePueblo);
			}
			if (cliente.getNif() != null && !cliente.getNif().trim().equals("")) {
				if(cliente.isNIFaDNI()) {
					PdfPCell clienteNIFStatic = new PdfPCell(new Phrase("D.N.I: ", boldFont));
					clienteNIFStatic.setColspan(1);
					clienteNIFStatic.setPadding(8f);
					clienteNIFStatic.setBorder(Rectangle.NO_BORDER);
					clienteNIFStatic.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell(clienteNIFStatic);
				}else {
					PdfPCell clienteNIFStatic = new PdfPCell(new Phrase("N.I.F: ", boldFont));
					clienteNIFStatic.setColspan(1);
					clienteNIFStatic.setPadding(8f);
					clienteNIFStatic.setBorder(Rectangle.NO_BORDER);
					clienteNIFStatic.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell(clienteNIFStatic);
				}
				PdfPCell clienteNIF = new PdfPCell(new Phrase(cliente.getNif()));
				clienteNIF.setColspan(9);
				clienteNIF.setPadding(8f);
				clienteNIF.setBorder(Rectangle.NO_BORDER);
				clienteNIF.setHorizontalAlignment(Element.ALIGN_LEFT);
				tabla.addCell(clienteNIF);
			}
			PdfPCell empresaCuentaStatic = new PdfPCell(new Phrase("Número de cuenta: ", boldFont));
			empresaCuentaStatic.setColspan(2);
			empresaCuentaStatic.setPadding(3f);
			empresaCuentaStatic.setBorder(Rectangle.NO_BORDER);
			empresaCuentaStatic.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tabla.addCell(empresaCuentaStatic);

			PdfPCell empresaCuenta = new PdfPCell(new Phrase(empresa.getnCuenta(), normalFont));
			empresaCuenta.setColspan(8);
			empresaCuenta.setPadding(3f);
			empresaCuenta.setBorder(Rectangle.NO_BORDER);
			empresaCuenta.setHorizontalAlignment(Element.ALIGN_LEFT);
			tabla.addCell(empresaCuenta);
			documento.add(tabla);
			/*parte de la factura*/
			Font boldFontFactura = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
			Font normalFontFactura = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);			
			float[] tamFactura = { 80F,300F,80F,80F};
			PdfPTable tablaFactura = new PdfPTable(4);
			tablaFactura.setWidths(tamFactura);
			tablaFactura.setWidthPercentage(100);
			//Crear cabecera
			PdfPCell titleFactura = new PdfPCell(new Phrase("FACTURA",boldFont));
			titleFactura.setColspan(4);
			titleFactura.setUseVariableBorders(true);
			titleFactura.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleFactura.setVerticalAlignment(Element.ALIGN_CENTER);
			titleFactura.setBorderColorTop(BaseColor.WHITE);
			titleFactura.setBorderColorRight(BaseColor.WHITE);
			titleFactura.setBorderColorLeft(BaseColor.WHITE);
			titleFactura.setPadding(10F);
			tablaFactura.addCell(titleFactura);
			PdfPCell c1 = new PdfPCell(new Phrase("CANTIDAD",boldFontFactura));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablaFactura.addCell(c1);
			PdfPCell c2 = new PdfPCell(new Phrase("CONCEPTO",boldFontFactura));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablaFactura.addCell(c2);
			PdfPCell c3 = new PdfPCell(new Phrase("PRECIO",boldFontFactura));
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablaFactura.addCell(c3);
			PdfPCell c4 = new PdfPCell(new Phrase("TOTAL",boldFontFactura));
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablaFactura.addCell(c4);
			/*Rellenar tabla de servicios*/
			for(Servicio serv : factura.getServicios()) {
				if(serv.getCantidad()>0) {
					PdfPCell cantidad = new PdfPCell(new Phrase(Integer.toString(serv.getCantidad())));
					cantidad.setPadding(8f);
					cantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
					cantidad.setUseVariableBorders(true);
					cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
					cantidad.setBorderColorTop(BaseColor.WHITE);
					cantidad.setBorderColorBottom(BaseColor.WHITE);
					cantidad.setBorderColorRight(BaseColor.WHITE);
					tablaFactura.addCell(cantidad);
				}else {
					PdfPCell cantidad = new PdfPCell(new Phrase("-"));
					cantidad.setPadding(8f);
					cantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
					cantidad.setUseVariableBorders(true);
					cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
					cantidad.setBorderColorTop(BaseColor.WHITE);
					cantidad.setBorderColorBottom(BaseColor.WHITE);
					cantidad.setBorderColorRight(BaseColor.WHITE);
					tablaFactura.addCell(cantidad);
				}
				PdfPCell concepto = new PdfPCell(new Phrase(serv.getConcepto()));
				concepto.setPadding(8f);
				concepto.setHorizontalAlignment(Element.ALIGN_CENTER);
				concepto.setUseVariableBorders(true);
				concepto.setVerticalAlignment(Element.ALIGN_CENTER);
				concepto.setBorderColorTop(BaseColor.WHITE);
				concepto.setBorderColorBottom(BaseColor.WHITE);
				tablaFactura.addCell(concepto);
				PdfPCell precio = new PdfPCell(new Phrase(String.format("%,.2f €", serv.getPrecio())));
				precio.setPadding(8f);
				precio.setHorizontalAlignment(Element.ALIGN_CENTER);
				precio.setUseVariableBorders(true);
				precio.setVerticalAlignment(Element.ALIGN_CENTER);
				precio.setBorderColorTop(BaseColor.WHITE);
				precio.setBorderColorBottom(BaseColor.WHITE);
				precio.setBorderColorLeft(BaseColor.WHITE);
				precio.setBorderColorRight(BaseColor.WHITE);
				tablaFactura.addCell(precio);
				PdfPCell totalParcial = new PdfPCell(new Phrase(String.format("%,.2f €",serv.getTotal())));
				totalParcial.setPadding(8f);
				totalParcial.setHorizontalAlignment(Element.ALIGN_CENTER);
				totalParcial.setUseVariableBorders(true);
				totalParcial.setVerticalAlignment(Element.ALIGN_CENTER);
				totalParcial.setBorderColorTop(BaseColor.WHITE);
				totalParcial.setBorderColorBottom(BaseColor.WHITE);
				tablaFactura.addCell(totalParcial);
			}
			PdfPCell sumaStatic = new PdfPCell(new Phrase("SUMA: "));
			sumaStatic.setPadding(8f);
			sumaStatic.setColspan(3);
			sumaStatic.setUseVariableBorders(true);
			sumaStatic.setHorizontalAlignment(Element.ALIGN_RIGHT);
			sumaStatic.setVerticalAlignment(Element.ALIGN_CENTER);
			sumaStatic.setBorderColorBottom(BaseColor.WHITE);
			tablaFactura.addCell(sumaStatic);
			PdfPCell suma = new PdfPCell(new Phrase(String.format("%,.2f €",factura.getTotal())));
			suma.setPadding(8f);
			suma.setColspan(1);
			suma.setUseVariableBorders(true);
			suma.setHorizontalAlignment(Element.ALIGN_RIGHT);
			suma.setVerticalAlignment(Element.ALIGN_CENTER);
			suma.setBorderColorBottom(BaseColor.WHITE);
			tablaFactura.addCell(suma);
			if(factura.getIva()!=0) {
				PdfPCell iva = new PdfPCell(new Phrase("IVA ("+factura.getIva()+"%)"));
				iva.setPadding(8f);
				iva.setColspan(3);
				iva.setUseVariableBorders(true);
				iva.setHorizontalAlignment(Element.ALIGN_RIGHT);
				iva.setVerticalAlignment(Element.ALIGN_CENTER);
				iva.setBorderColorBottom(BaseColor.WHITE);
				iva.setBorderColorTop(BaseColor.WHITE);
				tablaFactura.addCell(iva);
				double sumPrecioIva = (factura.getIva()/100)*factura.getTotal();
				PdfPCell ivaPrecio = new PdfPCell(new Phrase(String.format("%,.2f €",sumPrecioIva)));
				ivaPrecio.setPadding(8f);
				ivaPrecio.setColspan(3);
				ivaPrecio.setUseVariableBorders(true);
				ivaPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
				ivaPrecio.setVerticalAlignment(Element.ALIGN_CENTER);
				ivaPrecio.setBorderColorBottom(BaseColor.WHITE);
				tablaFactura.addCell(ivaPrecio);
				PdfPCell total = new PdfPCell(new Phrase("TOTAL: ",boldFont));
				total.setPadding(8f);
				total.setColspan(3);
				total.setUseVariableBorders(true);
				total.setHorizontalAlignment(Element.ALIGN_RIGHT);
				total.setVerticalAlignment(Element.ALIGN_CENTER);
				total.setBorderColorRight(BaseColor.WHITE);
				tablaFactura.addCell(total);
				
				PdfPCell totalPrecio = new PdfPCell(new Phrase(String.format("%,.2f €", sumPrecioIva+factura.getTotal()),boldFont));
				totalPrecio.setPadding(8f);
				totalPrecio.setColspan(1);
				totalPrecio.setUseVariableBorders(true);
				totalPrecio.setHorizontalAlignment(Element.ALIGN_RIGHT);
				totalPrecio.setVerticalAlignment(Element.ALIGN_CENTER);
				totalPrecio.setBorderColorLeft(BaseColor.WHITE);
				tablaFactura.addCell(totalPrecio);
			}else {
				PdfPCell total = new PdfPCell(new Phrase("TOTAL: ",boldFont));
				total.setPadding(8f);
				total.setColspan(3);
				total.setHorizontalAlignment(Element.ALIGN_RIGHT);
				total.setVerticalAlignment(Element.ALIGN_CENTER);
				tablaFactura.addCell(total);
				
				PdfPCell totalPrecio = new PdfPCell(new Phrase(String.format("%,.2f €",factura.getTotal()),boldFont));
				totalPrecio.setPadding(8f);
				totalPrecio.setColspan(1);
				totalPrecio.setHorizontalAlignment(Element.ALIGN_RIGHT);
				totalPrecio.setVerticalAlignment(Element.ALIGN_CENTER);
				tablaFactura.addCell(totalPrecio);
			}
			//PdfPCell iva
			documento.add(tablaFactura);
			documento.close();
			return rutaCompleta;

		} catch (IOException | DocumentException e) {
			e.getStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Empresa e = new Empresa("Gonzalo", "77938920Q", "Casa antonia", "C/Málaga, 83", "ES1238721398712312");
		Cliente c = new Cliente("Pepe", LocalDate.now());
		c.setDomicilio("Donde sea");
		Factura f = new Factura(LocalDate.now(),"1/21");
		f.setIva(21);
		Servicio s = new Servicio(5,"Lacado en blanco de 5 puertas con herrajes nuevos",351.33);
		Servicio s1 = new Servicio(0,"Lacado en blanco de 5 puertas con herrajes nuevos y no se si algo mas para rellenar",150);
		f.addServicio(s);
		f.addServicio(s1);
		try {
			GeneradorPFDF pdf = new GeneradorPFDF();
			System.out.println(pdf.generarPDF(e, c, f));
		} catch (URISyntaxException | IOException e1) {
			e1.getStackTrace();
		}

	}
}
