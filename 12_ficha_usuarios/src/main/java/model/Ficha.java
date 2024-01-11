package model;

public class Ficha {
	private String usuario;
	private int edad;
	private String email;
	public Ficha(String usuario, int edad, String email) {
		super();
		this.usuario = usuario;
		this.edad = edad;
		this.email = email;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
