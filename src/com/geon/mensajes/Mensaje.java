package com.geon.mensajes;

public class Mensaje {

	private String url;
	private String nombre;
	private String asunto;
	private String rut;
	
	public Mensaje(String url, String rut, String nombre, String asunto) {
		this.url = url;
		this.setRut(rut);
		this.nombre = nombre;
		this.asunto = asunto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

}
