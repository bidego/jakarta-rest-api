package org.eclipse.rest.api.models;

public class User {

	private String name;

	private String mensaje;

	private Integer counter;

	public User(String name) {
        this.name = name;
	}


	public User(String name, String mensaje) {
        this.name = name;
		this.mensaje = mensaje;
	}

	public User(String name, String mensaje, Integer counter) {
        this.name = name;
		this.mensaje = mensaje;
		this.counter = counter;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getUser(){
		return this.toString();
	}

	@Override
	public String toString() {
		return "{ name: " + name + ", mensaje: " + mensaje + ", id: " + counter + " }";
	}
}