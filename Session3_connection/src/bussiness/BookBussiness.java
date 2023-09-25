package bussiness;

import connection.ConnectionDB;
import ra.entity.Book;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookBussiness {
    public static List<Book> getAllBook() {
        //1 tạo đối tượng connection
        //2 tạo đối tượng callableStatement
        //3 gọi
        //4 xử lý dữ liệu trả về list

        List<Book> listBook = null;
        Connection conn = null;
        //2
        CallableStatement callSt = null;

        try {
            //1
            conn = ConnectionDB.openConnection();
            //2
            callSt = conn.prepareCall("{call get_all_book() }");

            //3 thực hiện gọi
            // khai báo đối tượgn Resulset để hứng đối tượng trả về

            /*
             * query - executeQuery()
             * insert, update, delete không có tham số trả ra - executeUpdate()
             * insert, update, delete có tham số trả ra - execute();
             *
             * */
            ResultSet rs = callSt.executeQuery(); //rs - tập hợp các bản ghi

            // duyệt các bản ghi trong rs đẩy ra listBook
            listBook = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_Id"));
                book.setBookName(rs.getString("book_name"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("book_status"));
                listBook.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listBook;

    }

    public static boolean createBook(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_book(?,?,?,?,?)}");
            //set dữ liệu cho tham số vào
            callSt.setString(1, book.getBookId());
            callSt.setString(2, book.getBookName());
            callSt.setFloat(3, book.getPrice());
            callSt.setString(4, book.getAuthor());
            callSt.setBoolean(5, book.isStatus());

            // đăng kí kiểu dữ liệu cho tham số trả ra
            //thực hiện gọi
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateBook(Book book) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_book(?,?,?,?,?)}");
            //set dữ liệu cho tham số vào
            callSt.setString(1, book.getBookId());
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                book = new Book();
                book.setBookId(rs.getString("book_Id"));
                book.setBookName(rs.getString("book_name"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("book_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Book getBookById (String bookId){
        Book book = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            //1
            conn = ConnectionDB.openConnection();
            //2
            callSt = conn.prepareCall("{call get_book_by_id(?) }");

            callSt.setString(1, bookId);
            ResultSet rs = callSt.executeQuery(); //rs - tập hợp các bản ghi

            // lấy dữ liệu rs đẩy vào đối tượng book trả về
            book = new Book();
            while (rs.next()) {
                book.setBookId(rs.getString("book_Id"));
                book.setBookName(rs.getString("book_name"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("book_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return book;
    }

    public static boolean deleteBook (String bookId){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            //1
            conn = ConnectionDB.openConnection();
            //2
            callSt = conn.prepareCall("{call delete_book(?) }");

            callSt.setString(1, bookId);
           callSt.executeUpdate();
           result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static List<Book> searchBookByName(String book_name) {
        Connection con = null;
        CallableStatement callSt = null;
        List<Book> listBookSearch = null;
        try {
            con = ConnectionDB.openConnection();
            callSt = con.prepareCall("{call get_book_by_name(?)}");
            callSt.setString(1, book_name);
            ResultSet rs = callSt.executeQuery();
            listBookSearch = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                listBookSearch.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(con, callSt);
        }
        return listBookSearch;
    }

    public static List<Book> sortBookList() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Book> sortedBookList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sapxep_by_price()}");
            ResultSet rs = callSt.executeQuery();
            sortedBookList = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                sortedBookList.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return sortedBookList ;
    }


}

