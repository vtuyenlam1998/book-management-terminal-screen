package model.builder;

import model.abstraction.Invoice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceConcreteBuilder implements InvoiceBuilder {
private String customerName;
private String bookName;
private int bookPrice;
private long quantity;
private Date dayOfPurchase;
    @Override
    public InvoiceBuilder setNameCustomer(String customerName) {
        this.customerName = customerName;
        return this;
    }

    @Override
    public InvoiceBuilder setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    @Override
    public InvoiceBuilder setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
        return this;
    }

    @Override
    public InvoiceBuilder setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public InvoiceBuilder setDayOfPurchase(String dayOfPurchase) throws ParseException {
        this.dayOfPurchase = new SimpleDateFormat("dd-MM-yyyy").parse(dayOfPurchase);
        return this;
    }

    @Override
    public Invoice build() {
        return new Invoice(customerName,bookName,bookPrice,quantity,dayOfPurchase);
    }
}
