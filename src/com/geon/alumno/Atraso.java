package com.geon.alumno;

public class Atraso {

	private int id;
	

	private String dia;
	private String mes;
	private int red;
	private int green;
	private int blue;
	private String hora;
	
	public Atraso(int id, String dia, String mes, int red, int green, int blue,
			String hora) {
		super();
		this.id = id;
		this.dia = dia;
		this.mes = mes;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.hora = hora;
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

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
