package entity;

public class Book {
    private int id;
    private  String title;
    private String yearPublication;

    private double price ;

    private int idAuthor;

    private Author objAuthor;

    public Book(int id, String title, String yearPublication, double price, int idAuthor, Author objAuthor) {
        this.id = id;
        this.title = title;
        this.yearPublication = yearPublication;
        this.price = price;
        this.idAuthor = idAuthor;
        this.objAuthor = objAuthor;
    }
    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(String yearPublication) {
        this.yearPublication = yearPublication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Author getObjAuthor() {
        return objAuthor;
    }

    public void setObjAuthor(Author objAuthor) {
        this.objAuthor = objAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearPublication='" + yearPublication + '\'' +
                ", price=" + price +
                ", idAuthor=" + idAuthor +
                ", objAuthor=" + objAuthor +
                '}';
    }
}
