package database;

import java.util.List;

public interface CRUD {
    /**
     * <h3>Metodo para insertar</h3>
     *
     * @param obj Recibe el objeto el cual se va a insertar en la DB
     * @return Retorna un objeto
     */
    public Object insert(Object obj);

    /**
     * <h3>Metodo para actualizar</h3>
     *
     * @param obj Recibe el objeto el cual se va a actualizar
     * @return Retorna un booleano que nos va a decir si fue actualizado o no
     */
    public boolean update(Object obj);

    /**
     * <h3>Metodo para eliminar</h3>
     *
     * @param obj Recibe el objeto el cual se va a eliminar
     * @return Retorna un booleano que nos va a decir si fue eliminado o no
     */
    public boolean delete(Object obj);

    /**
     * <h3>Metodo para listar</h3>
     *
     * @return Retorna una lista de lo que sea porque Object puede ser lo que sea
     */

    public List<Object> findAll();

    /**
     * <h3>Metodo para buscar por id</h3>
     *
     * @param id recibe un ID como parametro para poder hacer la consulta
     * @return retorna un objeto el cual encontro con ese id
     */

    public Object findByID(int id);
}
