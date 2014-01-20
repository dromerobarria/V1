package com.geon.cursos;

public class Cursos {


	private String nombre;
	private String profesor;
	

	public Cursos(String nombre, String profesor) {
		

		this.nombre = nombre;
		this.setProfesor(profesor);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	
}
