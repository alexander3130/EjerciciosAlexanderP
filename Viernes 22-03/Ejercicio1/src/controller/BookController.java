package controller;

import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;
import java.util.List;

public class BookController {

    //Instanciamos un model de book

    BookModel objBookModel;
    AuthorController objAuthorController;

    public BookController() {
        this.objBookModel = new BookModel();
        this.objAuthorController = new AuthorController();
    }

    /**
     * <h3><br> METODO PARA LISTAR LOS Libros<br/></h1>
     */

    public void getAll() {
        //Utilizamos el metodo sobreescrito de getAll que nos devuelve la lista en String  y recibe como parametro una lista
        String listStr = this.getAll(this.objBookModel.findAll());

        JOptionPane.showMessageDialog(null, listStr);
    }

    /**
     * <h4><br> METODO SOBRESCRITO PARA LISTAR USUARIOS QUE NOS DEVUELVE UN STRING<br/></h1>
     */
    public String getAll(List<Object> objectList) {
        // Creamos el contenedor de String donde vamos a concatenar todos los books
        String listStr = "LIST BOOKS \n";

        //Recorremos la lista que le llega como para metro para retornarla en String
        for (Object obj : objectList) {

            //Casteamos para poder acceder a los metodos como BOOK.toString() , ya que un objeto es generico
            Book objBook = (Book) obj;

            listStr += objBook.toString() + "\n";

        }
        return listStr;
    }

    /**
     * <h4>Metodo Para crear un libro </h4>
     **/

    public void create() {
        objAuthorController.getAll();
        Book objBook = new Book();
        int authorID = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id del autor del libro"));
        String title = JOptionPane.showInputDialog("Insert Title");
        String publicationYear = JOptionPane.showInputDialog("Insert Publication Year");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert  Price"));


        objBook.setTitle(title);
        objBook.setYearPublication(publicationYear);
        objBook.setPrice(price);
        objBook.setIdAuthor(authorID);

        objBook = (Book) this.objBookModel.insert(objBook);
        JOptionPane.showMessageDialog(null, objBook.toString());
    }
    // PARA INSERTAR PRIMERO DEBO LISTAR LOS AUTORES CON UN SELECT DE JOPTION
    //QUE EL USUARIO LO ELIJA Y LUEGO  GUARDO ESE AUTOR

    public void update() {
        //Listamos para que el usuario elija el id
        String listBook = this.getAll(this.objBookModel.findAll());
        JOptionPane.showMessageDialog(null, listBook);
        //Guardamos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserta el id que vas a actualizar "));

        //Verificar si existe un libro con ese id
        Book objBook = (Book) this.objBookModel.findByID(idUpdate);

        //Si el libro llega null es porque ingreso mal el id
        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "Book not found ");
        }
        //De lo contrario actualizamos el book
        else {
            //El tercer parametro sirve para llenar el valor del input por defecto

            int authorID = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id del autor del libro",objBook.getIdAuthor()));
            String title = JOptionPane.showInputDialog(null,"Insert Title",objBook.getTitle());
            String publicationYear = JOptionPane.showInputDialog(null,"Insert Publication Year",objBook.getYearPublication());
            double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Insert  Price",objBook.getPrice()));
            //String.valueOf sirve para convertir de entero a String

            //Actualizar el objBook que nos devuelve la consulta por ID

            objBook.setTitle(title);
            objBook.setYearPublication(publicationYear);
            objBook.setPrice(price);
            objBook.setIdAuthor(authorID);

            objBook = (Book) this.objBookModel.insert(objBook);
            JOptionPane.showMessageDialog(null, objBook.toString());

            //Le pasamos el coder con los nuevos valores al metodo del model
            // que es la encargada de interactuar con la base de datos
            this.objBookModel.update(objBook);
        }
    }

    public  void delete(){
//listar los libros
        String listBooks = this.getAll(this.objBookModel.findAll());
        JOptionPane.showMessageDialog(null,listBooks);
        //Creamos una bandera de confirmacion
        int confirm = 1;
        //Le pedimos al usuario el id que desea elminiar
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Inserte el id a eliminar"));

        //Creamos el book , lo buscamos con el metodo finID y le pasamos el id
        Book objBook = (Book) this.objBookModel.findByID(id);

        if(objBook == null){
            JOptionPane.showMessageDialog(null,"no se encontro el libro");
        }
        else {
            confirm = JOptionPane.showConfirmDialog(null,"Deseas eliminar este libro" + objBook.toString());

            //Si el usuario dice que si entonces eliminamos
            if (confirm == 0 ){
                this.objBookModel.delete(objBook);
            }
        }
    }
}
