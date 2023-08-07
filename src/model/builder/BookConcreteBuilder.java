package model.builder;

import model.abstraction.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookConcreteBuilder implements BookBuilder {
    private String nameBook;
    private String type;
    private Date yearOfPublication;
    private String detail;
    private int price;
    private long quantity;

    @Override
    public BookBuilder setID(int id) {
        return this;
    }

    @Override
    public BookBuilder setNameBook(String nameBook) {
        this.nameBook = nameBook;
        return this;
    }

    @Override
    public BookBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public BookBuilder setYearOfPublication(String yearOfPublication) throws ParseException {
        this.yearOfPublication = new SimpleDateFormat("dd-MM-yyyy").parse(yearOfPublication);
        return this;
    }

    @Override
    public BookBuilder setDetail(String detail) {
        this.detail = detail;
        return this;
    }
    @Override
    public BookBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    @Override
    public BookBuilder setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public Book build() {
        return new Book(nameBook,type,yearOfPublication,detail,price,quantity);
    }
}
