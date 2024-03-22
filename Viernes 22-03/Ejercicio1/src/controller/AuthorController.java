package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {
  AuthorModel objAuthorModel ;

    //Constructor
    public  AuthorController(){
        //CREAR UNA INSTANCIA DEL MODEL
        this.objAuthorModel = new AuthorModel();
    }

    /**
     * <h3><br> METODO PARA LISTAR LOS USUARIOS<br/></h1>
     */

    public void getAll(){
        //Utilizamos el metodo sobreescrito de getAll que nos devuelve la lista en String  y recibe como parametro una lista
        String listStr = this.getAll(this.objAuthorModel.findAll());

        JOptionPane.showMessageDialog(null,listStr);
    }


    /**
     * <h4><br> MÉTODO SOBRESCRITO PARA LISTAR USUARIOS QUE NOS DEVUELVE UN STRING<br/></h1>
     */
    public String getAll(List<Object> objectList){
        // Creamos el contenedor de String donde vamos a concatenar todos los autores
        String listStr = "LIST AUTHORS \n";

        //Recorremos la lista que le llega como para metro para retornarla en String
        for (Object obj : objectList){

            //Casteamos para poder acceder a los metodos como AUTHOR.toString() , ya que un objeto es generico
            Author objAuthor = (Author) obj ;

            listStr += objAuthor.toString() + "\n";

        }
        return listStr;
    }

    public void create(){
        Author objAuthor = new Author();

        String name = JOptionPane.showInputDialog(null,"Insert your name");
        String nationality = JOptionPane.showInputDialog(null,"Insert your nationality");

        objAuthor.setNationality(nationality);
        objAuthor.setName(name);

        objAuthor = (Author) this.objAuthorModel.insert(objAuthor);
        JOptionPane.showMessageDialog(null,objAuthor.toString());
    }

    public void update(){
        //Listamos para que el usuario elija el id
       String listAuthors = this.getAll(this.objAuthorModel.findAll());
        //Guardamos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserta el id que vas a actualizar "));

        //Verificar si existe un autor con ese id
        Author objAuthor = (Author) this.objAuthorModel.findByID(idUpdate);

        //Si el author llega null es porque ingreso mal el id
        if (objAuthor == null ){
            JOptionPane.showMessageDialog(null, "Author not found ");
        }
        //De lo contrario actualizamos el author
        else {
            //El tercer parametro sirve para llenar el valor del input por defecto
            String name = JOptionPane.showInputDialog(null, "ingrese nuevo nombre",objAuthor.getName());
            String clan = JOptionPane.showInputDialog(null, "ingrese su nacionalidad", objAuthor.getNationality());


            //String.valueOf sirve para convertir de entero a String

            //Actualizar el objAuthor que nos devuelve la consulta por ID

            objAuthor.setName(clan);
            objAuthor.setNationality(name);


            //Le pasamos el coder con los nuevos valores al metodo del model
            // que es la encargado de interactuar con la base de datos
            this.objAuthorModel.update(objAuthor);
        }
    }

    public void delete(){
        //listar los coders
        String listAuthors = this.getAll(this.objAuthorModel.findAll());
        JOptionPane.showMessageDialog(null,listAuthors);
        //Creamos una bandera de confirmacion
        int confirm = 1;
        //Le pedimos al usuario el id que desea elminiar
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Inserte el id a eliminar"));

        //Creamos el autor , lo buscamos con el metodo finID y le pasamos el id
        Author objAuthor = (Author) this.objAuthorModel.findByID(id);

        if(objAuthor == null){
            JOptionPane.showMessageDialog(null,"no se encontro el autor");
        }
        else {
            confirm = JOptionPane.showConfirmDialog(null,"Deseas eliminar este autor" + objAuthor.toString());

            //Si el usuario dice que si entonces eliminamos
            if (confirm == 0 ){
                this.objAuthorModel.delete(objAuthor);
            }
        }
    }
}
