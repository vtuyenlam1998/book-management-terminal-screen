package model.Service;

import model.abstraction.Book;
import model.abstraction.Invoice;
import model.abstraction.User;
import model.exception.InvalidChoiceException;
import model.ulti.Input;
import model.ulti.ReadFileUlti;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    private static final InvoiceService invoiceService = new InvoiceService();

    private InvoiceService() {
    }

    public static InvoiceService getInstance() {
        return invoiceService;
    }

    private static final List<Invoice> INVOICE_LIST = new ArrayList<>();
    private static final BookService bookService = BookService.getInstance();
    private static final UserService userService = UserService.getInstance();
    private static final CartService cartService = CartService.getInstance();
    private static final List<Book> BOOK_CART = bookService.getBookCart();
    private static User currenUser;

    static {
        try {
            List<Invoice> dataList = ReadFileUlti.fileReadingInvoice("src\\model\\data\\invoiceHistory.csv");
            INVOICE_LIST.addAll(dataList);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void displayCustomerInvoiceHistory() {
        do {
            try {
                currenUser = userService.getCurrentUser();
                System.out.println("""
                        Bạn muốn xem lịch sử mua hàng như thế nào?
                        1. Xổ ra hết đê
                        2. Xổ theo danh sách tháng
                        3. Quay lại Main Menu
                        """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn của bạn: ");
                switch (choice) {
                    case 1:
                        if (!cartService.checkPaidCart()) {
                            INVOICE_LIST.stream().filter(ele -> ele.getCustomerName().equalsIgnoreCase(currenUser.getFullName())).forEach(System.out::println);
                            break;
                        } else {
                            List<Invoice> dataList = ReadFileUlti.fileReadingInvoice("src\\model\\data\\invoiceHistory.csv");
                            INVOICE_LIST.clear();
                            INVOICE_LIST.addAll(dataList);
                            INVOICE_LIST.stream().filter(ele -> ele.getCustomerName().equalsIgnoreCase(currenUser.getFullName())).forEach(System.out::println);
                        }
                        break;
                    case 2:
                        boolean monthExistence = false;
                        if (!cartService.checkPaidCart()) {
                            int monthOfPurchase = Input.choiceIntegerInput("Nhập tháng muốn xem: ") - 1;
                            for (Invoice invoice : INVOICE_LIST) {
                                if (invoice.getCustomerName().equalsIgnoreCase(currenUser.getFullName())) {
                                    if (monthOfPurchase == invoice.getDayOfPurchase().getMonth()) {
                                        System.out.println(invoice);
                                        monthExistence = true;
                                    }
                                }
                            }
                        } else {
                            List<Invoice> dataList = ReadFileUlti.fileReadingInvoice("src\\model\\data\\invoiceHistory.csv");
                            INVOICE_LIST.clear();
                            INVOICE_LIST.addAll(dataList);
                            int monthOfPurchase = Input.choiceIntegerInput("Nhập tháng muốn xem: ") - 1;
                            for (Invoice invoice : INVOICE_LIST) {
                                if (invoice.getCustomerName().equalsIgnoreCase(currenUser.getFullName())) {
                                    if (monthOfPurchase == invoice.getDayOfPurchase().getMonth()) {
                                        System.out.println(invoice);
                                        monthExistence = true;
                                    }
                                }
                            }
                        }
                        if (!monthExistence) { throw new InvalidChoiceException("Không tìm thấy dữ liệu trong tháng này"); }
                        break;
                    case 3:
                        return;
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public void getSoldBookHistoryInMonth() {
        do {
            try {
                currenUser = userService.getCurrentUser();
                System.out.println("""
                        Bạn muốn xem lịch sử bán hàng như thế nào?
                        1. Xổ ra hết đê
                        2. Xổ theo danh sách tháng
                        3. Quay lại Main Menu
                        """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn của bạn: ");
                switch (choice) {
                    case 1:
                        if (!cartService.checkPaidCart()) {
                            INVOICE_LIST.stream().forEach(System.out::println);
                            break;
                        } else {
                            List<Invoice> dataList = ReadFileUlti.fileReadingInvoice("src\\model\\data\\invoiceHistory.csv");
                            INVOICE_LIST.clear();
                            INVOICE_LIST.addAll(dataList);
                            INVOICE_LIST.stream().forEach(System.out::println);
                        }
                        break;
                    case 2:
                        boolean monthExistence = false;
                        if (!cartService.checkPaidCart()) {
                            int monthOfPurchase = Input.choiceIntegerInput("Nhập tháng muốn xem: ") - 1;
                            for (Invoice invoice : INVOICE_LIST) {
                                if (monthOfPurchase == invoice.getDayOfPurchase().getMonth()) {
                                    System.out.println(invoice);
                                    monthExistence = true;
                                }
                            }
                        } else {
                            List<Invoice> dataList = ReadFileUlti.fileReadingInvoice("src\\model\\data\\invoiceHistory.csv");
                            INVOICE_LIST.clear();
                            INVOICE_LIST.addAll(dataList);
                            int monthOfPurchase = Input.choiceIntegerInput("Nhập tháng muốn xem: ") - 1;
                            for (Invoice invoice : INVOICE_LIST) {
                                if (monthOfPurchase == invoice.getDayOfPurchase().getMonth()) {
                                    System.out.println(invoice);
                                    monthExistence = true;
                                }
                            }
                        }
                        if (!monthExistence) {
                            throw new InvalidChoiceException("Không tìm thấy dữ liệu trong tháng này");
                        }
                        break;
                    case 3:
                        return;
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    public void getSaleAndProfitInMonth() {
        do {
            try {
                System.out.println("""
                        Bạn muốn kiểm tra doanh thu/ lợi nhuận trong tháng nào?
                        1. Doanh thu trong tháng
                        2. Lợi nhuận trong tháng
                        3. Quay lại
                        """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                int totalIncome = 0;
                double profitRate = 0.23;
                switch (choice) {
                    case 1:
                        boolean monthIncomeExist = false;
                        int saleMonthCompareToFormatGetMonth = Input.choiceIntegerInput("Nhập tháng doanh thu muốn kiểm tra: ") - 1;
                        for (Invoice invoice : INVOICE_LIST) {
                            if (saleMonthCompareToFormatGetMonth == invoice.getDayOfPurchase().getMonth()) {
                                System.out.println(invoice);
                                totalIncome += invoice.getBookPrice();
                                monthIncomeExist = true;
                            }
                        }
                        if (!monthIncomeExist) {
                            throw new InvalidChoiceException("Không tìm thấy dữ liệu trong tháng");
                        } else {
                            System.out.println("Tổng doanh thu tháng " + (saleMonthCompareToFormatGetMonth + 1) + " là " + totalIncome + "₫");
                        }
                        break;
                    case 2:
                        boolean monthProfitExist = false;
                        int profitMonthCompareToFormatGetMonth = Input.choiceIntegerInput("Nhập tháng lợi nhuận muốn kiểm tra: ") - 1;
                        for (Invoice invoice : INVOICE_LIST) {
                            if (profitMonthCompareToFormatGetMonth == invoice.getDayOfPurchase().getMonth()) {
                                System.out.println(invoice);
                                totalIncome += invoice.getBookPrice();
                                monthProfitExist = true;
                            }
                        }
                        if (!monthProfitExist) {
                            throw new InvalidChoiceException("Không tìm thấy dữ liệu trong tháng");
                        } else {
                            System.out.println("Tổng lợi nhuận tháng " + (profitMonthCompareToFormatGetMonth + 1) + " là " + (int)(totalIncome*profitRate) + "₫");
                        }
                        break;
                    case 3:
                        return;
                    default:
                        throw new InvalidChoiceException("Nhập chán vl!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
