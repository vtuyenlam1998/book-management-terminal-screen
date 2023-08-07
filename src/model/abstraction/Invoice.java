package model.abstraction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {
    private String customerName;
    private String bookName;
    private int bookPrice;
    private long quantity;
    private Date dayOfPurchase;
    public Invoice(){}
    public String getFormatDayOfPurchase(){
        return new SimpleDateFormat("dd-MM-yyyy").format(dayOfPurchase);
    }

    public Invoice(String customerName, String bookName, int bookPrice, long quantity, Date dayOfPurchase) {
        this.customerName = customerName;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.dayOfPurchase = dayOfPurchase;
    }
    @Override
    public String toString() {
        return "Tên khách hàng: " + customerName +
                "; " + "Tên sách: " + bookName +
                "; " + "Giá: " + bookPrice + "₫" +
                "; " + "Số lượng: " + quantity +
                "; " + "Ngày mua: " + getFormatDayOfPurchase();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Date getDayOfPurchase() {
        return dayOfPurchase;
    }

    public void setDayOfPurchase(Date dayOfPurchase) {
        this.dayOfPurchase = dayOfPurchase;
    }
}
