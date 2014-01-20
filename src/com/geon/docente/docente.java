package com.geon.docente;

public class docente {

	private String url;
	private String nombre;
	private String rut;
	private String email;
	private String area;

	public docente(String url, String nombre, String rut, String area, String email) {
		
		this.url = url;
		this.nombre = nombre;
		this.rut = rut;
		this.setEmail(email);
		this.setArea(area);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}





	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
