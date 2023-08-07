package model.abstraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private int bookID;
    private String nameBook;
    private String type;
    private Date yearOfPublication;
    private String detail;
    private int price;
    private long quantity;
    private static int count = 0;
    private int numberOfBookPurchase = 0;

    public Book() {
    }

    public Date getYearOfPublication() {
        return yearOfPublication;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYearOfPublication(String yearOfPublication) throws ParseException {
        this.yearOfPublication = new SimpleDateFormat("dd-MM-yyyy").parse(yearOfPublication);
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getFormatYearOfPublication() {
        return new SimpleDateFormat("dd-MM-yyyy").format(yearOfPublication);
    }

    public int getNumberOfBookPurchase() {
        return numberOfBookPurchase;
    }
    public void setNumberOfBookPurchase(){
        this.numberOfBookPurchase++;
    }
    public Book(String nameBook, String type, Date yearOfPublication, String detail, int price, long quantity) {
        this.bookID = ++count;
        this.nameBook = nameBook;
        this.type = type;
        this.yearOfPublication = yearOfPublication;
        this.detail = detail;
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity() {
        this.quantity = quantity - 1;
    }
    @Override
    public String toString() {
        return "THÔNG TIN SẢN PHẨM: "
                + "\n" + "✨ Tác phẩm: " + bookID
                + "\n" + "❒ Sách: " + nameBook
                + "\n" + "❒ Năm xuất bản: " + getFormatYearOfPublication()
                + "\n" + "❒ Thể loại: " + getType()
                + "\n" + "❒ Nội dung: " + getDetail()
                + "\n" + "❒ Giá bán: " + price + " ₫"
                + "\n" + "❒ Số lượng trong kho: " + quantity;
    }

    public int getID() {
        return bookID;
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public int getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }
}
