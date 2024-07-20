package com.lamvt.ulti;

import com.lamvt.entity.Customer;
import com.lamvt.entity.Staff;
import com.lamvt.abstraction.Book;
import com.lamvt.abstraction.Invoice;
import com.lamvt.builder.BookConcreteBuilder;
import com.lamvt.builder.InvoiceConcreteBuilder;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileUlti {
    private static List<String> readFile(String path){
        List<String> readFileList = new ArrayList<>();
        File file = new File(path);
        try ( FileReader fileReader = new FileReader(file);
              BufferedReader reader = new BufferedReader(fileReader)){
            String line;
            while ((line = reader.readLine()) != null) {
                readFileList.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } return readFileList;
    }
    public static List<Customer> fileReadingCustomer(String path){
        List<String> propertyOfCustomerList = readFile(path);
        List<Customer> customerList = new ArrayList<>();
        final int INDEX_OF_USERNAME = 0;
        final int INDEX_OF_FULL_NAME = 1;
        final int INDEX_OF_PHONE_NUMBER = 2;
        final int INDEX_OF_EMAIL = 3;
        final int INDEX_OF_PASSWORD = 4;
        final int INDEX_OF_ADDRESS = 5;
        for (String propertyOFCustomer : propertyOfCustomerList) {
            if(propertyOFCustomer.equals(propertyOfCustomerList.get(0))) {
                continue;
            }
            String[] properties = propertyOFCustomer.split("; ");
            customerList.add(new Customer(
                    properties[INDEX_OF_USERNAME],
                    properties[INDEX_OF_FULL_NAME],
                    properties[INDEX_OF_PHONE_NUMBER],
                    properties[INDEX_OF_EMAIL],
                    properties[INDEX_OF_PASSWORD],
                    properties[INDEX_OF_ADDRESS]));
        }
        return customerList;
    }
    public static List<Staff> fileReadingStaff(String path){
        List<String> propertyOfCustomerList = readFile(path);
        List<Staff> customerList = new ArrayList<>();
        final int INDEX_OF_USERNAME = 0;
        final int INDEX_OF_FULL_NAME = 1;
        final int INDEX_OF_PHONE_NUMBER = 2;
        final int INDEX_OF_EMAIL = 3;
        final int INDEX_OF_PASSWORD = 4;
        final int INDEX_OF_ADDRESS = 5;
        for (String propertyOFCustomer : propertyOfCustomerList) {
            if(propertyOFCustomer.equals(propertyOfCustomerList.get(0))) {
                continue;
            }
            String[] properties = propertyOFCustomer.split("; ");
            customerList.add(new Staff(properties[INDEX_OF_USERNAME],
                    properties[INDEX_OF_FULL_NAME],
                    properties[INDEX_OF_PHONE_NUMBER],
                    properties[INDEX_OF_EMAIL],
                    properties[INDEX_OF_PASSWORD],
                    properties[INDEX_OF_ADDRESS]));
        }
        return customerList;
    }
    public static List<Book> fileReadingBook(String path) throws ParseException {
        List<String> propertyOfBookList = readFile(path);
        List<Book> bookList = new ArrayList<>();
        final int INDEX_OF_BOOK_NAME = 0;
        final int INDEX_OF_BOOK_TYPE = 1;
        final int INDEX_OF_YEAR_OF_PUBLICATION = 2;
        final int INDEX_OF_DETAIL = 3;
        final int INDEX_OF_PRICE = 4;
        final int INDEX_OF_QUANTITY = 5;
        for (String propertyOfBook : propertyOfBookList) {
            String[] properties = propertyOfBook.split("; ");
            bookList.add(new BookConcreteBuilder()
                    .setNameBook(properties[INDEX_OF_BOOK_NAME])
                    .setType(properties[INDEX_OF_BOOK_TYPE])
                    .setYearOfPublication(properties[INDEX_OF_YEAR_OF_PUBLICATION])
                    .setDetail(properties[INDEX_OF_DETAIL])
                    .setPrice(Integer.parseInt(properties[INDEX_OF_PRICE]))
                    .setQuantity(Long.parseLong(properties[INDEX_OF_QUANTITY])).build());
        } return bookList;
    }
    public static List<Invoice> fileReadingInvoice(String path) throws ParseException {
        List<String> propertyOfInvoiceList = readFile(path);
        List<Invoice> invoices = new ArrayList<>();
        final int INDEX_OF_CUSTOMER_NAME = 0;
        final int INDEX_OF_BOOK_NAME = 1;
        final int INDEX_OF_BOOK_PRICE = 2;
        final int INDEX_OF_QUANTITY = 3;
        final int INDEX_OF_DAYOFPURCHASE = 4;
        for (String propertyOfInvoice : propertyOfInvoiceList) {
            if(propertyOfInvoice.equals(propertyOfInvoiceList.get(0))) {
                continue;
            }
            String[] properties = propertyOfInvoice.split("; ");
            invoices.add(new InvoiceConcreteBuilder()
                    .setNameCustomer(properties[INDEX_OF_CUSTOMER_NAME])
                    .setBookName(properties[INDEX_OF_BOOK_NAME])
                    .setBookPrice(Integer.parseInt(properties[INDEX_OF_BOOK_PRICE]))
                    .setQuantity(Long.parseLong(properties[INDEX_OF_QUANTITY]))
                    .setDayOfPurchase(properties[INDEX_OF_DAYOFPURCHASE])
                    .build());
        } return invoices;
    }
}
