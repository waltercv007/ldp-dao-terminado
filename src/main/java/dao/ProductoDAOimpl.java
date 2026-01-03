package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexionMySQL;
import interfaces.ProductoDAO;
import model.Producto;

public class ProductoDAOimpl implements ProductoDAO {

	@Override
	public boolean crearProducto(Producto objPro) {
		String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
		try (Connection con = ConexionMySQL.obtenerConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, objPro.getNombreProducto());
			ps.setDouble(2, objPro.getPrecioProducto());
			ps.setInt(3, objPro.getStockProducto());

			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Producto> obtenerTodosLosProductos() {
		List<Producto> lista = new ArrayList<>();
		String sql = "SELECT id_producto, nombre, precio, stock FROM productos ";

		try (Connection con = ConexionMySQL.obtenerConexion();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Producto p = new Producto();
				p.setIdProducto(rs.getInt("id_producto"));
				p.setNombreProducto(rs.getString("nombre"));
				p.setPrecioProducto(rs.getDouble("precio"));
				p.setStockProducto(rs.getInt("stock"));
				lista.add(p);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public boolean eliminarProducto(int id) {
		String sql = "DELETE FROM productos WHERE id_producto = ?";
		try (Connection con = ConexionMySQL.obtenerConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Producto obtenerProductoPorId(int id) throws ClassNotFoundException, SQLException {
		Connection con = ConexionMySQL.obtenerConexion();
		Producto objPro = null;
		
		String sql = "SELECT id_producto,nombre,precio,stock FROM productos WHERE id_producto = ?";
		
		PreparedStatement pstsmt =  con.prepareStatement(sql);
		pstsmt.setInt(1, id);
		
		ResultSet rs = pstsmt.executeQuery();
		
		if(rs.next()) {
			objPro = new Producto();
			
			objPro.setIdProducto(rs.getInt("id_producto"));
			objPro.setNombreProducto(rs.getString("nombre"));
			objPro.setPrecioProducto(rs.getDouble("precio"));
			objPro.setStockProducto(rs.getInt("stock"));
			
		}
				
		return objPro;
	}

	@Override
	public boolean actualizarProducto(Producto objPro) throws ClassNotFoundException, SQLException {
		Connection con = ConexionMySQL.obtenerConexion();
		String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id_producto = ?";
		PreparedStatement pstsmt =  con.prepareStatement(sql);
		pstsmt.setString(1, objPro.getNombreProducto());
		pstsmt.setDouble(2, objPro.getPrecioProducto());
		pstsmt.setInt(3, objPro.getStockProducto());
		pstsmt.setInt(4, objPro.getIdProducto());
		
		int filasAfectaron = pstsmt.executeUpdate();
		
		if(filasAfectaron > 0) {
			return true;
		}
		
		return false;
	}

}
