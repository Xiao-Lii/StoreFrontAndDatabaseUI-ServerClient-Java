package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Book extends Product {
    private String authorName;
    private LocalDate publicationData;
    private int numPages;
    private int edition;
    private ArrayList<Category> prodCategory;


    public Book(String productID, String productName, String brandName, String productDesc,
                LocalDate dateOfIncorp, Category selectedCategory, String authorName,
                LocalDate publicationData, int numPages, int edition) {
        super(productID, productName, brandName, productDesc, dateOfIncorp, selectedCategory);
        this.authorName = authorName;
        this.publicationData = publicationData;
        this.numPages = numPages;
        this.edition = edition;
        this.prodCategory = new ArrayList<Category>();
        this.prodCategory.add(selectedCategory);
        //Category books = new Category("Book", "Book", "Paperback media entertainment.");
        //this.prodCategory.add(books);
    }

    public Book(String productID, String productName, String brandName, String productDesc,
                LocalDate dateOfIncorp, String authorName, LocalDate publicationData, int numPages, int edition) {
        super(productID, productName, brandName, productDesc, dateOfIncorp);
        this.authorName = authorName;
        this.publicationData = publicationData;
        this.numPages = numPages;
        this.edition = edition;
        this.prodCategory = new ArrayList<Category>();
        Category books = new Category("Book", "Book", "Paperback media entertainment.");
        this.prodCategory.add(books);
    }

    // THIS IS IMPORTANT WHEN DISPLAYING TO LISTVIEW TABLES
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format(" - %s - %tD - %d - %d", this.getAuthorName(), this.getPublicationData(), this.getNumPages(),
                this.getEdition()));
        return sb.toString();
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

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setProdCategory(ArrayList<Category> prodCategory) {
        this.prodCategory = prodCategory;
    }


}