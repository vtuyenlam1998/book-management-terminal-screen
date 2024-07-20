package com.lamvt.ulti;

import com.lamvt.abstraction.Book;
import com.lamvt.abstraction.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class WriteFileUlti {
    public static void writeFileUser(String path, User user) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(user.getUserName() + "; " + user.getFullName() + "; " + user.getPhoneNumber() + "; " + user.getEmail() + "; " + user.getPassWord() + "; " + user.getAddress() +  "\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void writeFileInvoice(String path, User user) {
        try (FileWriter fileWriter = new FileWriter(path);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(String.format("""
                    \t\t\t\tHÓA ĐƠN
                    %s
                    \n
                    """, user.toString()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void writeFileAppendInvoice(String path, List<Book> bookList) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write("THÔNG TIN GIỎ HÀNG" + "\n" + "Số lượng sản phẩm đã mua: " + bookList.size() + "\n");
            int sumOfCart = 0;
            for (Book book : bookList) {
                writer.write(String.format("""
                        Sản phẩm đã mua: %s
                        Giá tiền sản phẩm: %d
                        """, book.getNameBook(), book.getPrice()));
                sumOfCart += book.getPrice();
            }
            writer.write("Tổng tiền: " + sumOfCart + " ₫");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void writeFileAppendInvoiceHistory(String path,User user,List<Book> bookCart) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            HashSet<Book> printedBook = new HashSet<>();
            for (Book book : bookCart) {
                if (!printedBook.contains(book)) {
                    writer.write(user.getFullName() + "; " + book.getNameBook() + "; " + (book.getPrice() * book.getNumberOfBookPurchase()) + "; " + book.getNumberOfBookPurchase() + "; " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "\n");
                    printedBook.add(book);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
