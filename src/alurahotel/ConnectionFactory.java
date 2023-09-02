package alurahotel;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection creaConexion() throws SQLException{
		Connection conexion  = DriverManager.getConnection("jdbc:mysql://localhost:3306/alura_hotel", "root", "64121283");
		System.out.println("Creando conexion");
		return conexion;
	}
}
