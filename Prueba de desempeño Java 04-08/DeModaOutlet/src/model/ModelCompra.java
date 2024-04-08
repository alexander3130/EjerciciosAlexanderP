package model;

import database.ConfigDB;
import entity.*;
import interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModelCompra implements CRUD {
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listToCompras = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT compra.*, cliente.*, producto.*, tienda.*" +
                    "FROM compra " +
                    "INNER JOIN cliente ON cliente.id_cliente = compra.fk_cliente " +
                    "INNER JOIN producto ON producto.id_producto = compra.fk_producto "+
                    "INNER JOIN tienda ON tienda.id_tienda = producto.fk_tienda";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult =  objPrepare.executeQuery();
            while (objResult.next()){
                Compra objCompra = new Compra();
                objCompra.setId(objResult.getInt("id_compra"));
                objCompra.setFecha_compra(objResult.getString("fecha_compra"));
                objCompra.setCantidad(objResult.getInt("cantidad"));

                Cliente objCliente = new Cliente();
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("email"));

                Producto objProducto = new Producto();
                objProducto.setId(objResult.getInt("id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));

                Tienda objTienda = new Tienda();
                objTienda.setId(objResult.getInt("id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setNombre(objResult.getString("tienda.ubicacion"));

                CompraClienteProducto objCCP = new CompraClienteProducto(objCompra, objCliente, objProducto, objTienda);

                listToCompras.add(objCCP);
            }
        } catch (Exception e) {
            System.out.println("Error en modelo compra :" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listToCompras;
    }
    @Override
    public Object create(Object obj) {
        Compra objCompra = (Compra) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO compra (fk_cliente, fk_producto, fecha_compra, cantidad ) VALUES (?, ?, ?, ?)";


            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objCompra.getFk_cliente());
            objPrepare.setInt(2, objCompra.getFk_producto());
            objPrepare.setString(3, objCompra.getFecha_compra());
            objPrepare.setInt(4, objCompra.getCantidad());

            int rowsAffected = objPrepare.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet objResult = objPrepare.getGeneratedKeys();
                if (objResult.next()) {
                    objCompra.setId(objResult.getInt(1));
                }
            } else {
                // Manejar el caso en el que no se insertó ninguna fila
                System.out.println("Error al insertar la compra: no se insertaron filas");
                return null;
            }



            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objCompra.setId(objResult.getInt(1));
            }


        }catch (SQLException e) {
            System.out.println("Error al insertar la compra: " + e.getMessage());
            return null;
        }

        ConfigDB.closeConnection();
        return objCompra;
    }
    @Override
    public boolean update(Object obj) {
        Compra objCompra = (Compra) obj;
        Connection objConexion = ConfigDB.openConnection();
        boolean isUpdate = false;

        try {
            String sql = "UPDATE compra SET fk_cliente = ?, fk_producto = ?, fecha_compra = ?, cantidad = ?  WHERE id_compra = ?;";

            PreparedStatement objPrepare = objConexion.prepareStatement(sql);

            objPrepare.setInt(1, objCompra.getFk_cliente());
            objPrepare.setInt(2, objCompra.getFk_producto());
            objPrepare.setString(3, objCompra.getFecha_compra());
            objPrepare.setInt(4, objCompra.getCantidad());
            objPrepare.setInt(5, objCompra.getId());


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
        Compra objCompra;
        CompraClienteProducto CCP = new CompraClienteProducto();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT compra.*, cliente.*, producto.*, tienda.* " +
                    "FROM compra " +
                    "INNER JOIN cliente ON cliente.id_cliente = compra.fk_cliente " +
                    "INNER JOIN producto ON producto.id_producto = compra.fk_producto " +
                    "INNER JOIN tienda ON tienda.id_tienda = producto.fk_tienda " +
                    "WHERE id_compra = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);



            ResultSet objResult =  objPrepare.executeQuery();

            if (objResult.next()){
                objCompra = new Compra();
                objCompra.setId(objResult.getInt("id_compra"));
                objCompra.setFecha_compra(objResult.getString("fecha_compra"));
                objCompra.setCantidad(objResult.getInt("cantidad"));
                objCompra.setFk_cliente(objResult.getInt("fk_cliente"));
                objCompra.setFk_producto(objResult.getInt("fk_producto"));


                Cliente objCliente = new Cliente();
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

                Producto objProducto = new Producto();
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setStock(objResult.getInt("stock"));

                Tienda objTienda = new Tienda();
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("ubicacion"));



                CCP = new CompraClienteProducto(objCompra, objCliente, objProducto, objTienda);

            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return CCP;
    }
    @Override
    public boolean delete(Object obj) {
        boolean isDeleted = false;

        Compra objCompra = (Compra) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM compra WHERE id_compra = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCompra.getId());

            int rowsAffected = objPrepare.executeUpdate();
            System.out.println("Filas afectadas: "+rowsAffected);

            if (rowsAffected > 0){
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return isDeleted;
    }




}

