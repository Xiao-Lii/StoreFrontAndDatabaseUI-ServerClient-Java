package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Book extends Product {
    private String authorName;
    private LocalDate publicationData;
    private int numPages;
    private int edition;

    public Book(String productID, String productName, String brandName, String productDesc,
                LocalDate dateOfIncorp, ArrayList<Category> prodCategory, String authorName,
                LocalDate publicationData, int numPages, int edition) {
        super(productID, productName, brandName, productDesc, dateOfIncorp);
        this.authorName = authorName;
        this.publicationData = publicationData;
        this.numPages = numPages;
        this.edition = edition;
        // Category(String catID, String catName, String catDesc)
        Category books = new Category("Books21", "Books", "Paperback media entertainment.");
        prodCategory.add(books);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getPublicationData() {
        return publicationData;
    }

    public void setPublicationData(LocalDate publicationData) {
        this.publicationData = publicationData;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
}