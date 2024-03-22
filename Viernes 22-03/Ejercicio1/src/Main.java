import controller.AuthorController;
import controller.BookController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();
        String optionPrincipal = "";
        String optionAuthors = "";
        String optionBooks = "";

        do {
            optionPrincipal = JOptionPane.showInputDialog(null, """
                    MENU 
                    1. Authors
                    2. Books
                    3. Salir
                    """);
            switch (optionPrincipal) {
                case "1":
                    do {
                        optionAuthors = JOptionPane.showInputDialog(null, """
                                MENU 
                                1. List Authors
                                2. Create Author
                                3. Update Author
                                4. Delete Author
                                                   
                                5. Salir
                                """);

                        switch (optionAuthors) {
                            case "1":
                                objAuthorController.getAll();
                                break;

                            case "2":
                                objAuthorController.create();
                                break;

                            case "3":
                                objAuthorController.update();
                                break;

                            case "4":
                                objAuthorController.delete();
                                break;

                        }
                    } while (!optionAuthors.equals("5"));

                    break;
                case "2":
                    do {
                        optionBooks = JOptionPane.showInputDialog(null, """
                                MENU 
                                1. List Books
                                2. Create Book
                                3. Update Book
                                4. Delete Book
                                                   
                                5. Salir
                                """);

                        switch (optionAuthors) {
                            case "1":
                                objBookController.getAll();
                                break;

                            case "2":
                                objBookController.create();
                                break;

                            case "3":
                                objBookController.update();
                                break;

                            case "4":
                                objBookController.delete();
                                break;

                        }
                    } while (!optionBooks.equals("5"));
                    break;
            }


        } while (!optionPrincipal.equals("3"));
    }
}