package com.autozone.database.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.autozone.database.DatabaseConnection;
import com.autozone.enums.LibroStatus;
import com.autozone.models.Libros;
import com.autozone.utils.Validador;
 
/**
* En esta clase se realizan todas las interacciones entre el usuario y la tabla de libros en la base de datos.
*/
 
public class LibroDAO {
	public void insertarLibro(Libros libro) throws SQLException, IllegalArgumentException, IllegalAccessException{
		//Aqui se manda a validar el objeto de libro.
		Validador.vaildar(libro);
		//Aqui se define el query que queremos ejecutar.
		String sql = "INSERT INTO Libros (Titulo, Autor, Disponibilidad) VALUES (?,?,?);";
		//Aqui se ejecuta la conexion con la base de datos, se sustituyen los '?' por los valores deseados y se ejecuta el query.
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getAutor());
			pstmt.setString(3, libro.getDisponibilidad().toString());
			pstmt.executeUpdate();
		}
	}
	public void actualizarLibro(int Isbn, Libros libro) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Validador.vaildar(libro);
		String sql = "UPDATE Libros SET Titulo = ?, Autor = ? WHERE ISBN = "+Isbn+";";
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getAutor());
			pstmt.executeUpdate();
		}
	}
	public void actualizarLibroDisp(int Isbn, LibroStatus disp) throws SQLException, IllegalArgumentException, IllegalAccessException {
		String sql;
		//Aqui estamos validando la disponibilidad de un libro, dependiendo del estatus, se tomara una accion u otra.
		switch (disp.toString()) {
		case "DISPONIBLE": {
			sql = "UPDATE Libros SET Disponibilidad = 'PRESTADO' WHERE ISBN = ?;";
			break;
		}
		case "PRESTADO": {
			sql = "UPDATE Libros SET Disponibilidad = 'DISPONIBLE' WHERE ISBN = ?;";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + disp);
		}
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, Isbn);
			pstmt.executeUpdate();
		}
	}
	public List<Libros> obtenerEstatusDeLibro(int isbn) throws SQLException {
		String sql = "SELECT * FROM Libros WHERE ISBN = '"+isbn+"';";
		List<Libros> libros = new ArrayList<Libros>();
		Libros libro = null;
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			//Aqui estamos repasando los registros que nos muestra el query de este metodo y lo guardamos en una lista. 
			while(rs.next()){
				libro = new Libros(rs.getString("Titulo"), rs.getString("Autor"), rs.getString("Disponibilidad"));
				libro.setIsbn(rs.getInt("ISBN"));
				libros.add(libro);
			}
		}
		return libros;
	}
	public void eliminarLibro(int isbnLibro) throws SQLException {
		String sql = "DELETE FROM Libros WHERE ISBN = ?;";
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, isbnLibro);
			pstmt.executeUpdate();
		}
	}
	public List<Libros> obtenerLibrosPorTitulo(String titulo) throws SQLException {
		String sql = "SELECT * FROM Libros WHERE Titulo = '"+titulo+"';";
		List<Libros> libros = new ArrayList<Libros>();
		Libros libro = null;
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()){
				libro = new Libros(rs.getString("Titulo"), rs.getString("Autor"), null);
				libro.setIsbn(rs.getInt("ISBN"));
				libros.add(libro);
			}
		}
		return libros;
	}
	public List<Libros> obtenerLibrosPorAutor(String autor) throws SQLException {
		String sql = "SELECT * FROM Libros WHERE Autor = '"+autor+"';";
		List<Libros> libros = new ArrayList<Libros>();
		Libros libro = null;
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()){
				libro = new Libros(rs.getString("Titulo"), rs.getString("Autor"), null);
				libro.setIsbn(rs.getInt("ISBN"));
				libros.add(libro);
			}
		}
		return libros;
	}
 
	public List<Libros> obtenerLibrosPorIsbn(int isbn) throws SQLException {
		String sql = "SELECT * FROM Libros WHERE ISBN = '"+isbn+"';";
		List<Libros> libros = new ArrayList<Libros>();
		Libros libro = null;
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()){
				libro = new Libros(rs.getString("Titulo"), rs.getString("Autor"), null);
				libro.setIsbn(rs.getInt("ISBN"));
				libros.add(libro);
			}
		}
		return libros;
	}
}