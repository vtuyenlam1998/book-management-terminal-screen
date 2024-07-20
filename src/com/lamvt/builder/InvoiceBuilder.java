package com.lamvt.builder;

import com.lamvt.abstraction.Invoice;

import java.text.ParseException;

public interface InvoiceBuilder {
    InvoiceBuilder setNameCustomer(String customerName);
    InvoiceBuilder setBookName(String bookName);
    InvoiceBuilder setBookPrice(int bookPrice);
    InvoiceBuilder setQuantity(long quantity);
    InvoiceBuilder setDayOfPurchase(String dayOfPurchase) throws ParseException;

    Invoice build();
}
