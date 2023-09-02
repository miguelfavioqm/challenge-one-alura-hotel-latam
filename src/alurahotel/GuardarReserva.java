package alurahotel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuardarReserva {
	private Integer id;
	public GuardarReserva() {
	}
	
	public void save(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			Connection conexion = factory.creaConexion();
			PreparedStatement stm = conexion.prepareStatement("INSERT INTO RESERVAS (Fecha_Entrada, "
					+ "Fecha_Salida, "
					+ "Valor, "
					+ "Forma_Pago) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			//Date fechaEntrada = Date.valueOf("2023-08-18");
			//Date fechaSalida = Date.valueOf("2023-08-25");
			stm.setDate(1, fechaEntrada);
			stm.setDate(2, fechaSalida);
			stm.setString(3, valor);
			stm.setString(4, formaPago);
			
			stm.execute();
			
			ResultSet rst = stm.getGeneratedKeys();
			while(rst.next()) {
				id = rst.getInt(1);
				System.out.println("el id creado fue " + id);
				
			}
			conexion.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void saveHuesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad
			,String telefono, int id) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			Connection conexion = factory.creaConexion();
			PreparedStatement stm = conexion.prepareStatement("INSERT INTO HUESPEDES (Nombre, "
					+ "Apellido, "
					+ "FechaNacimiento, "
					+ "Nacionalidad, "
					+ "Telefono, "
					+ "IdReserva) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			//Date fechaEntrada = Date.valueOf("2023-08-18");
			//Date fechaSalida = Date.valueOf("2023-08-25");
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setDate(3, fechaNacimiento);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, id);
			
			stm.execute();
			
			ResultSet rst = stm.getGeneratedKeys();
			while(rst.next()) {
				id = rst.getInt(1);
				System.out.println("el id huesped creado fue " + id);
				
			}
			conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public List<Map<String, String>> obtenerReservas() throws SQLException {
			ConnectionFactory factory = new ConnectionFactory();
			Connection conexion = factory.creaConexion();
			PreparedStatement stm = conexion.prepareStatement("SELECT * FROM RESERVAS");
			stm.execute();
			ResultSet resultSet = stm.getResultSet();
			List<Map<String, String>> resultado = new ArrayList<>();
			while(resultSet.next()) {
				Map<String, String> fila = new HashMap<>();
				fila.put("Id",String.valueOf(resultSet.getInt("Id")));
				fila.put("Fecha_Entrada",String.valueOf(resultSet.getString("Fecha_Entrada")));
				fila.put("Fecha_Salida",String.valueOf(resultSet.getString("Fecha_Salida")));
				fila.put("Valor",String.valueOf(resultSet.getString("Valor")));
				fila.put("Forma_Pago",String.valueOf(resultSet.getString("Forma_Pago")));
				resultado.add(fila);
			}
			conexion.close();
			return resultado;
		
	}
	public List<Map<String, String>> obtenerHuespedes() throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.creaConexion();
		PreparedStatement stm = conexion.prepareStatement("SELECT * FROM HUESPEDES");
		stm.execute();
		ResultSet resultSet = stm.getResultSet();
		List<Map<String, String>> resultado = new ArrayList<>();
		while(resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("idHuespedes",String.valueOf(resultSet.getInt("idHuespedes")));
			System.out.println(resultSet.getInt("idHuespedes"));
			fila.put("Nombre",String.valueOf(resultSet.getString("Nombre")));
			fila.put("Apellido",String.valueOf(resultSet.getString("Apellido")));
			fila.put("FechaNacimiento",String.valueOf(resultSet.getString("FechaNacimiento")));
			fila.put("Nacionalidad",String.valueOf(resultSet.getString("Nacionalidad")));
			fila.put("Telefono",String.valueOf(resultSet.getString("Telefono")));
			fila.put("IdReserva",String.valueOf(resultSet.getInt("IdReserva")));
			resultado.add(fila);
		}
		conexion.close();
		return resultado;
	
}
	public void eliminarReserva(Integer id) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.creaConexion();
		PreparedStatement stm = conexion.prepareStatement("DELETE FROM HUESPEDES WHERE IdReserva = ?", Statement.RETURN_GENERATED_KEYS);
		stm.setInt(1, id);
		stm.execute();
		stm = conexion.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
		stm.setInt(1, id);
		stm.execute();
		conexion.close();
	}
	public void eliminarHuesped(Integer id) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.creaConexion();
		PreparedStatement stm = conexion.prepareStatement("DELETE FROM HUESPEDES WHERE idHuespedes = ?", Statement.RETURN_GENERATED_KEYS);
		stm.setInt(1, id);
		stm.execute();
		conexion.close();
	}
	public void modificarReserva(Date Fecha_Entrada, Date Fecha_Salida, String valor, String forma_Pago, int id ) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.creaConexion();
		PreparedStatement stm = conexion.prepareStatement("UPDATE reservas SET"
				+ " Fecha_Entrada = ?,"
				+ " Fecha_Salida = ?,"
				+ " Valor = ?,"
				+ " Forma_Pago = ?"
				+ " WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
		stm.setDate(1, Fecha_Entrada);
		stm.setDate(2, Fecha_Salida);
		stm.setString(3, valor);
		stm.setString(4, forma_Pago);
		stm.setInt(5, id);
		stm.execute();
		conexion.close();
	}
	public Integer getId() {
		return id;
	}

	public void modificarHuesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			Integer idReserva, Integer idHuesped) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.creaConexion();
		PreparedStatement stm = conexion.prepareStatement("UPDATE huespedes SET"
				+ " Nombre = ?,"
				+ " Apellido = ?,"
				+ " FechaNacimiento = ?,"
				+ " Nacionalidad = ?,"
				+ " Telefono = ?,"
				+ " IdReserva = ?"
				+ " WHERE idHuespedes = ?", Statement.RETURN_GENERATED_KEYS);
		stm.setString(1, nombre);
		stm.setString(2, apellido);
		stm.setDate(3, fechaNacimiento);
		stm.setString(4, nacionalidad);
		stm.setString(5, telefono);
		stm.setInt(6, idReserva);
		stm.setInt(7, idHuesped);
		stm.execute();
		conexion.close();
	}
	
}
