package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Producto;

public interface ProductoDAO {
	
	public abstract boolean crearProducto(Producto objPro);
	public abstract List<Producto> obtenerTodosLosProductos();
	public abstract boolean eliminarProducto(int id);
	public abstract Producto obtenerProductoPorId(int id) throws ClassNotFoundException, SQLException;
	public abstract boolean actualizarProducto(Producto objPro) throws ClassNotFoundException, SQLException;
}
