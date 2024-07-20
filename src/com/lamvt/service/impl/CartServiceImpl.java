package com.lamvt.service.impl;

import com.lamvt.abstraction.Book;
import com.lamvt.exception.InvalidChoiceException;
import com.lamvt.ulti.Input;
import com.lamvt.ulti.ReturnFunction;
import com.lamvt.ulti.WriteFileUlti;

import java.util.List;

public class CartServiceImpl {
    private static final CartServiceImpl CART_SERVICE_IMPL = new CartServiceImpl();

    private CartServiceImpl() {
    }

    public static CartServiceImpl getInstance() {
        return CART_SERVICE_IMPL;
    }

    private static final BookServiceImpl BOOK_SERVICE_IMPL = BookServiceImpl.getInstance();
    private static final List<Book> bookList = BOOK_SERVICE_IMPL.getBookList();
    private static final List<Book> BOOK_CART;
    private static final UserServiceImpl USER_SERVICE_IMPL = UserServiceImpl.getInstance();
    private static boolean isPaid = false;
    static {
        BOOK_CART = BOOK_SERVICE_IMPL.getBookCart();
    }
    public boolean checkPaidCart() {
        return isPaid;
    }
    public void checkCart() {
        int totalCart = 0;
        if (BOOK_CART.isEmpty()) {
            System.out.print("""
                    Giỏ hàng:
                    Không có gì trong giỏ hàng
                    Bạn muốn mua thêm sách không?
                    """);
            ReturnFunction.backPage();
        } else {
            System.out.println("Giỏ hàng:" + "\n" + "Tổng số sản phẩm: " + BOOK_CART.size());
            for (Book book : BOOK_CART) {
                System.out.println("ID " + book.getID() + ". " + book.getNameBook() + " - Giá tiền: " + book.getPrice() + "₫" + "Số lượng ");
                totalCart += book.getPrice();
            }
            System.out.println("----------------------------------------------");
            System.out.println("\t\t\t\t\t\t\t" + "Tổng tiền " + totalCart + "₫");
            buyBookOnline();
        }
    }
    public void buyBookOnline() {
        do {
            try {
                System.out.println("""
                        1. Thanh toán bằng QR, thẻ ATM
                        2. Ship COD tới tận nhà
                        3. Quay lại
                        """);
                int transactionChoice = Input.choiceIntegerInput("Chọn hình thức thanh toán: ");
                switch (transactionChoice) {
                    case 1:
                        System.out.println("""
                                ** ***** ***** **
                                *** *** * *** ***
                                **** * *** * ****
                                ***** ***** *****
                                ****  *****  ****
                                *** ** *** ** ***
                                ** **** * **** **
                                                        
                                Quét mã để thanh toán sản phẩm:
                                1. Quét mã
                                2. Quay lại
                                """);
                        int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                        switch (choice) {
                            case 1:
                                    for (int i = 0; i < BOOK_CART.size(); i++) {
                                        for (int j = 0; j < bookList.size(); j++) {
                                            if (BOOK_CART.get(i).getID() == (bookList.get(j).getID())) {
                                                bookList.get(j).setQuantity();
                                                bookList.get(j).setNumberOfBookPurchase();
                                                isPaid = true;
                                            }
                                        }
                                    }
                                System.out.println("Đang thực hiện giao dịch ...");
                                System.out.println("Bạn đã thanh toán thành công, sách sẽ được chuyển tới từ 3 - 5 ngày ");
                                WriteFileUlti.writeFileInvoice("src\\com\\lamvt\\data\\invoice.txt", USER_SERVICE_IMPL.getCurrentUser());
                                WriteFileUlti.writeFileAppendInvoice("src\\com\\lamvt\\data\\invoice.txt",BOOK_CART);
                                WriteFileUlti.writeFileAppendInvoiceHistory("src\\com\\lamvt\\data\\invoiceHistory.csv", USER_SERVICE_IMPL.getCurrentUser(), BOOK_CART);
                                BOOK_CART.clear();
                                return;
                            case 2:
                                return;
                        }
                    case 2:
                        System.out.println(USER_SERVICE_IMPL.getCurrentUser().getAddress());
                        String verify = Input.promt("Xác định đúng địa chỉ nhà (Y/N): ");
                        if (verify.equalsIgnoreCase("Y")) {
                            if (bookList.contains(BOOK_CART)) {
                                for (int i = 0; i < BOOK_CART.size(); ) {
                                    for (int j = 0; j < bookList.size(); j++) {
                                        if (BOOK_CART.get(i).getID() == (bookList.get(j).getID())) {
                                            bookList.get(j).setQuantity();
                                            bookList.get(j).setNumberOfBookPurchase();
                                            isPaid = true;
                                        }
                                    }
                                }
                            }
                            System.out.println("Bạn đã đặt hàng thành công, sách sẽ được chuyển tới từ 3 - 5 ngày, vui lòng thanh toán khi nhận hàng!");
                            WriteFileUlti.writeFileInvoice("src\\com\\lamvt\\data\\invoice.txt", USER_SERVICE_IMPL.getCurrentUser());
                            WriteFileUlti.writeFileAppendInvoice("src\\com\\lamvt\\data\\invoice.txt",BOOK_CART);
                            WriteFileUlti.writeFileAppendInvoiceHistory("src\\com\\lamvt\\data\\invoiceHistory.csv", USER_SERVICE_IMPL.getCurrentUser(), BOOK_CART);
                            return;
                        } else if (verify.equalsIgnoreCase("N")) {
                            return;
                        } else throw new InvalidChoiceException("Đầu vào không hợp lệ");
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
