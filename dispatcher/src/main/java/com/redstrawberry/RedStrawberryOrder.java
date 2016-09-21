package com.redstrawberry;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RedStrawberryOrder {

	private Long idProducto;
	private int cantidad;
	private String idTransaccion;
	private Date fecha;

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "RedStrawberryOrder [idProducto=" + idProducto + ", cantidad=" + cantidad + ", idTransaccion="
				+ idTransaccion + ", fecha=" + fecha + "]";
	}
}
