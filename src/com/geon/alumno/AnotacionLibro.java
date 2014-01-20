package com.geon.alumno;

public class AnotacionLibro {

	private int id;
	private String dia;
	private String mes;
	private int red;
	private int green;
	private int blue;
	private int tipoAnotacion;
	private String mensaje;
	private String rut_docente;
	
	
	public AnotacionLibro(int id, String dia, String mes, int red, int green,
			int blue, int tipoAnotacion, String mensaje, String rut_docente) {

		this.id = id;
		this.dia = dia;
		this.mes = mes;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.tipoAnotacion = tipoAnotacion;
		this.mensaje = mensaje;
		this.rut_docente = rut_docente;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDia() {
		return dia;
	}


	public void setDia(String dia) {
		this.dia = dia;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public int getRed() {
		return red;
	}


	public void setRed(int red) {
		this.red = red;
	}


	public int getGreen() {
		return green;
	}


	public void setGreen(int green) {
		this.green = green;
	}


	public int getBlue() {
		return blue;
	}


	public void setBlue(int blue) {
		this.blue = blue;
	}


	public int getTipoAnotacion() {
		return tipoAnotacion;
	}


	public void setTipoAnotacion(int tipoAnotacion) {
		this.tipoAnotacion = tipoAnotacion;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getRut_docente() {
		return rut_docente;
	}


	public void setRut_docente(String rut_docente) {
		this.rut_docente = rut_docente;
	}
	
	
	
	
}
