package model.builder;

import model.abstraction.Book;

import java.text.ParseException;

public interface BookBuilder {
    BookBuilder setID(int id);
    BookBuilder setNameBook(String nameBook);
    BookBuilder setType(String type);
    BookBuilder setYearOfPublication(String yearOfPublication) throws ParseException;
    BookBuilder setDetail(String detail);
    BookBuilder setPrice(int price);
    BookBuilder setQuantity(long quantity);
    Book build();
}
