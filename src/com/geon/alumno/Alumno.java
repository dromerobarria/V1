package com.geon.alumno;

public class Alumno {

	private String url;
	private String nombre;
	private String rut;
	private String email;
	private String curso;

	public Alumno(String url, String nombre, String rut, String curso) {
		
		this.url = url;
		this.nombre = nombre;
		this.rut = rut;
		this.email = email;
		this.curso = curso;
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

}
