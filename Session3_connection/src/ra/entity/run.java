package ra.entity;

import bussiness.BookBussiness;
import connection.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("*********************MENU*******************************");
            System.out.println("1. Hiển thị các sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm sách theo tên sách");
            System.out.println("6. Thống kê sách theo tác giả");
            System.out.println("7. Sắp xếp sách theo giá tăng dần (procedure)");
            System.out.println("8. Thoát");
            System.out.println("-----------------------------------------------------");

            System.out.print("\nLựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    run.displayListBook();
                    break;
                case 2:
                    run.createBook(sc);
                    break;
                case 3:
                    run.upDate(sc);
                    break;
                case 4:
                    run.delete(sc);
                    break;
                case 5:
                    run.searchBookByName(sc);
                    break;
                case 6:
                    break;
                case 7:
                    run.sortBookListByPrice();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Hãy chọn từ 1 - 8.");
                    break;
            }
        } while (true);
    }

    public static void displayListBook() {
        List<Book> listBook = BookBussiness.getAllBook();
        System.out.println("Thông tin sách");
        listBook.stream().forEach(book -> book.displayBook());
    }

    public static void createBook(Scanner sc) {
        // Nhập dữ liệu cho 1 sản phâmr để thêm mới
        Book bookNew = new Book();
        bookNew.inputData(sc);

        //gọi createBook của Bussiness để thực hiện thêm dữ liệu vào db
        boolean result = BookBussiness.createBook(bookNew);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Có lỗi xảy ra");
        }
    }

    public static void upDate(Scanner sc) {
        System.out.println("Nhập mã sản phẩm cần cập nhật: ");
        String bookIdin = sc.nextLine();
        // kiểm tra tồn tại
        Book bookUpdate = BookBussiness.getBookById(bookIdin);
        if (bookUpdate != null) {
            //mã sách tồn tại trong csdl
            System.out.println("Nhập vào tên sách mới: ");
            bookUpdate.setBookName(sc.nextLine());
            //thực hiện
            boolean result = BookBussiness.updateBook(bookUpdate);
            if (result) {
                System.out.println("Thành công");
            } else {
                System.err.println("Có lỗi.");
            }
        } else {
            // không tồn tại
            System.err.println("Mã sách không tồn tại. ");
        }
    }

    public static void delete(Scanner sc) {
        System.out.println("Nhập mã sản phẩm cần xóa: ");
        String bookidin = sc.nextLine();
        Book book = BookBussiness.getBookById(bookidin);
        if (book != null) {
            boolean result = BookBussiness.deleteBook(bookidin);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.err.println("Có lỗi");
            }
        } else {
            System.err.println("Không tồn tại");
        }
    }

    public static void searchBookByName(Scanner sc) {
        System.out.println("Nhập vào tên sách cần tìm:");
        String bookNameSeach = sc.nextLine();
        List<Book> listBookSearch = BookBussiness.searchBookByName(bookNameSeach);
        if (listBookSearch != null) {
            listBookSearch.stream().forEach(book -> book.displayBook());
        } else {
            System.err.println("Có lỗi xảy ra");
        }
    }

    public static void sortBookListByPrice() {
        List<Book> sortedBookList = BookBussiness.sortBookList();
        sortedBookList.stream().forEach(book -> book.displayBook());
    }



}


