package ra.entity;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private float price;
    private String author;
    private boolean status;

    public Book() {
    }

    public Book(String bookId, String bookName, float price, String author, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.author = author;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner sc) {
        System.out.println("Nhập mã sách :");
        this.bookId = sc.nextLine();
        System.out.println("Nhập tên sách :");
        this.bookName = sc.nextLine();
        System.out.println("Nhập vào giá của sách :");
        this.price = Float.parseFloat(sc.nextLine());
        System.out.println("Nhập tên tác giả: ");
        this.author = sc.nextLine();
        System.out.println("Nhập vào trạng thái true | false: ");
        this.status = Boolean.parseBoolean(sc.nextLine());
    }

    public void displayBook() {
        System.out.printf("Mã sách: %-5s - Tên sách: %-10s - Giá: %-5.2f - Tác giả: %-5s - Trạng thái: %b \n",this.bookId,this.bookName,this.price,this.author,this.status);
    }
}