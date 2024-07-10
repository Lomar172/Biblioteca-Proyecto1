package com.autozone.models;
 
import com.autozone.annotations.NotNull;
 
public class Libros {
	
	/**En esta clase se declaran las variables, se aplican anotaciones
	 *se crean metodos getter y setters y se crea un metodo constructor para
	 *usarse como base a la hora de crear un objeto de esta clase.
	 */
	
	private int isbn;
	@NotNull
	private String titulo;
	@NotNull
	private String autor;
	private String disponibilidad;
 
	public Libros(String titulo, String autor, String string) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.disponibilidad = string;
	}
	
	public String getTitulo() {
		return titulo;
	}
 
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
 
	public String getAutor() {
		return autor;
	}
 
	public void setAutor(String autor) {
		this.autor = autor;
	}
 
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
 
	public String getDisponibilidad() {
		return disponibilidad;
	}
 
	public int getIsbn() {
		return isbn;
	}
 
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
}