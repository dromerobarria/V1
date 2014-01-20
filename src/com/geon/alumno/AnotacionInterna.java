package com.geon.alumno;


public class AnotacionInterna {
	private String Mensaje;
	private String Mes;
	private String Dia;
	private int Id;
	private String Anotador;
	private int red;
	
	private int blue;
	private int green;
	
	
	public AnotacionInterna(String mensaje, String mes, String dia, int id,
			String anotador, int red, int blue, int green) {
		super();
		this.Mensaje = mensaje;
		this.Mes = mes;
		this.Dia = dia;
		this.Id = id;
		this.Anotador = anotador;
		this.red = red;
		this.blue = blue;
		this.green = green;
	}
	
	

	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	public String getMes() {
		return Mes;
	}
	public void setMes(String mes) {
		Mes = mes;
	}
	public String getDia() {
		return Dia;
	}
	public void setDia(String dia) {
		Dia = dia;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getAnotador() {
		return Anotador;
	}
	public void setAnotador(String anotador) {
		Anotador = anotador;
	}

	public int getRed() {
		return red;
	}



	public void setRed(int red) {
		this.red = red;
	}



	public int getBlue() {
		return blue;
	}



	public void setBlue(int blue) {
		this.blue = blue;
	}



	public int getGreen() {
		return green;
	}



	public void setGreen(int green) {
		this.green = green;
	}

}
