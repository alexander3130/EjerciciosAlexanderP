package model;

import database.ConfigDB;
import entity.Tienda;
import entity.Producto;
import entity.ProductoToCliente;
import interfaces.CRUD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ModelProducto implements CRUD {
    public int getStockCapacity(int id){
        Connection objConnection = ConfigDB.openConnection();
        int stockCapacity = 0;

        try {
            String sql = "SELECT * FROM producto where id_producto = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                stockCapacity = objResult.getInt("stock");

            };



        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en model producto obtener stock: " + e.getMessage());
        }

        return stockCapacity;

    };
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listToProducts = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT producto.*, tienda.nombre, tienda.ubicacion, producto.stock FROM producto INNER JOIN tienda ON producto.fk_tienda = tienda.id_tienda;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                Producto objProducto = new Producto();
                objProducto.setId(objResult.getInt("id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setStock(objResult.getInt("stock"));

                Tienda objTienda = new Tienda();
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));

                ProductoToCliente productoToCliente = new ProductoToCliente(objProducto, objTienda);

                listToProducts.add(productoToCliente);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listToProducts;
    }
    @Override
    public Object create(Object obj) {
        Producto objProducto = (Producto) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO producto (nombre, precio, stock, fk_tienda ) VALUES (?, ?, ?, ?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getStock());
            objPrepare.setInt(4, objProducto.getFk_tienda());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objProducto.setId(objResult.getInt(1));
            }


        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
            return null;
        }
        ConfigDB.closeConnection();
        return objProducto;
    }
    @Override
    public boolean update(Object obj) {
        Producto objProducto = (Producto) obj;
        Connection objConexion = ConfigDB.openConnection();
        boolean isUpdate = false;



        try {
            String sql = "UPDATE producto SET nombre = ?, precio = ?, stock = ?, fk_tienda = ?  WHERE id_producto = ?;";
            PreparedStatement objPrepare =  objConexion.prepareStatement(sql);



            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getStock());
            objPrepare.setInt(4, objProducto.getFk_tienda());
            objPrepare.setInt(5, objProducto.getId());

            int rowsAffected =  objPrepare.executeUpdate();

            if (rowsAffected > 0){
                isUpdate = true;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        ConfigDB.closeConnection();
        return isUpdate;
    }
    public Object findByID(int id){
        Producto objProducto;
        ProductoToCliente productoToCliente = new ProductoToCliente();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT product.*, tienda.nombre, tienda.ubicacion " +
                    "FROM producto product INNER JOIN tienda ON product.fk_tienda = tienda.id_tienda WHERE product.id_producto = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setInt(1, id);

            ResultSet objResult =  objPrepare.executeQuery();

            if (objResult.next()){
                objProducto = new Producto();
                objProducto.setId(objResult.getInt("id_producto"));
                objProducto.setNombre(objResult.getString("Nombre"));
                objProducto.setFk_tienda(objResult.getInt("fk_tienda"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setStock(objResult.getInt("stock"));

                Tienda objTienda = new Tienda();
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));

                System.out.println(objTienda.getNombre());
                productoToCliente.setProducto(objProducto);
                productoToCliente.setTienda(objTienda);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return productoToCliente;
    }
    @Override
    public boolean delete(Object obj) {
        boolean isDeleted = false;

        Producto objProducto = (Producto) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM producto WHERE id_producto = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objProducto.getId());

            int rowsAffected = objPrepare.executeUpdate();

            if (rowsAffected > 0){
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }



        return isDeleted;
    }
}

