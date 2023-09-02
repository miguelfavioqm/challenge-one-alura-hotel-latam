package alurahotel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaInsercion {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexion = factory.creaConexion();
		PreparedStatement stm = conexion.prepareStatement("INSERT INTO RESERVAS (Fecha_Entrada, "
				+ "Fecha_Salida, "
				+ "Valor, "
				+ "Forma_Pago) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		Date fechaEntrada = Date.valueOf("2023-08-18");
		Date fechaSalida = Date.valueOf("2023-08-25");
		stm.setDate(1, fechaEntrada);
		stm.setDate(2, fechaSalida);
		stm.setString(3, "200");
		stm.setString(4, "tarjeta");
		
		stm.execute();
		
		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("el id creado fue " + id);
			
		}
		conexion.close();
	}

}
