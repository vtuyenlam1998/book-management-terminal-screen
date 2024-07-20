package com.lamvt.service.impl;

import com.lamvt.abstraction.Book;
import com.lamvt.exception.InvalidChoiceException;
import com.lamvt.service.BookService;
import com.lamvt.view.BookStoreView;
import com.lamvt.ulti.Input;
import com.lamvt.ulti.ReadFileUlti;
import com.lamvt.ulti.ReturnFunction;
import com.lamvt.builder.BookConcreteBuilder;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final BookServiceImpl BOOK_SERVICE_IMPL = new BookServiceImpl();
    private static final BookStoreView bookStoreView = BookStoreView.getInstance();
    private static final UserServiceImpl USER_SERVICE_IMPL = UserServiceImpl.getInstance();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        return BOOK_SERVICE_IMPL;
    }

    private static final List<Book> BOOK_LIST = new ArrayList<>();
    private static final List<Book> BOOK_CART = new ArrayList<>();

    static {
        try {
            List<Book> dataList = ReadFileUlti.fileReadingBook("src\\com\\lamvt\\data\\book.csv");
            BOOK_LIST.addAll(dataList);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getBookCart() {
        return BOOK_CART;
    }

    public List<Book> getBookList() {
        return BOOK_LIST;
    }

    public void sortBookByPrice() {
        do {
            try {
                System.out.print("""
                        Bạn muốn sắp xếp sách như thế nào?
                        1. Từ rẻ tới đắt
                        2. Từ đắt tới rẻ
                        3. Từ cũ nhất tới mới nhất
                        4. Từ mới nhất tới cũ nhất
                        5. Quay lại trang trước
                        """);
                int setChoice = Input.choiceIntegerInput("Enter your choice: ");
                switch (setChoice) {
                    case 1:
                        BOOK_LIST.sort(Comparator.comparingDouble(Book::getPrice));
                        for (Book book : BOOK_LIST) {
                            System.out.println("ID: " + book.getID() + ". " + book.getNameBook() + " | Giá tiền: " + book.getPrice() + "₫");
                        }
                        ReturnFunction.backPage();
                        break;
                    case 2:
                        BOOK_LIST.sort(Comparator.comparing(Book::getPrice).reversed());
                        for (Book book : BOOK_LIST) {
                            System.out.println("ID: " + book.getID() + ". " + book.getNameBook() + " | Giá tiền: " + book.getPrice() + "₫");
                        }
                        ReturnFunction.backPage();
                        break;
                    case 3:
                        BOOK_LIST.stream().sorted(Comparator.comparing(Book::getYearOfPublication)).forEach(Book -> System.out.println("ID: " + Book.getID() + ". " + Book.getNameBook() + " | Ngày xuất bản: " + Book.getFormatYearOfPublication()));
                        ReturnFunction.backPage();
                        break;
                    case 4:
                        BOOK_LIST.stream().sorted(Comparator.comparing(Book::getYearOfPublication).reversed()).forEach(Book -> System.out.println("ID: " + Book.getID() + ". " + Book.getNameBook() + " | Ngày xuất bản: " + Book.getFormatYearOfPublication()));
                        ReturnFunction.backPage();
                        break;
                    case 5:
                        return;
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    public void searchBookByType(String type) {
        try {
            boolean isExistTypeOfBook = false;
            for (Book bookShelf : BOOK_LIST) {
                if ((bookShelf.getType().toUpperCase()).contains(type.toUpperCase())) {
                    System.out.println(bookShelf);
                    isExistTypeOfBook = true;
                }
            }
            if (!isExistTypeOfBook) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy nội dung này");
        }
    }

    public void searchBookByPrice() {
    }

    public void searchBookByName() {
//        contentof
    }

    public void displayDetailOfBook() {
        do {
            try {
                BOOK_LIST.stream().forEach(Book -> System.out.println("ID: " + Book.getID() + ". " + Book.getNameBook() + " | " + Book.getType()));
                System.out.println("""
                                                
                        1. Hiển thị thông tin sách theo ID hoặc tên sách
                        2. Mua sách theo ID
                        3. Quay lại
                        """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                switch (choice) {
                    case 1:
                        System.out.println("""
                                Bạn muốn tìm theo?
                                1. ID
                                2. Name
                                """);
                        int searchChoice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                        switch (searchChoice) {
                            case 1:
                                int id = Input.choiceIntegerInput("Nhập ID: ");
                                if (id <= BOOK_LIST.size()) {
                                    for (Book book : BOOK_LIST) {
                                        if (id == book.getID()) {
                                            System.out.println(book);
                                            break;
                                        }
                                    }
                                } else System.out.println("Không tìm thấy lựa chọn trên!");
                                break;
                            case 2:
                                String bookName = Input.promt("Nhập tên sách: ");
                                boolean existBookName = false;
                                for (Book book : BOOK_LIST) {
                                    if (book.getNameBook().toUpperCase().contains(bookName.toUpperCase())) {
                                        existBookName = true;
                                        break;
                                    }
                                }
                                if (existBookName) {
                                    BOOK_LIST.stream().filter(ele -> ele.getNameBook().toUpperCase().contains(bookName.toUpperCase())).forEach(System.out::println);
                                } else System.out.println("Không tìm thấy nội dung trên");
                                break;
                            default:
                                throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                        }
                        ReturnFunction.backPage();
                        break;
                    case 2:
                        selecBookToBuy();
                        break;
                    case 3:
                        return;
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                }
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public List<Book> selecBookToBuy() {
        do {
            try {
                BOOK_LIST.forEach(Book -> System.out.println("ID: " + Book.getID() + ". " + Book.getNameBook() + " | " + " Số lượng: " + Book.getQuantity()));
                int ID = Input.choiceIntegerInput("Nhập ID sách muốn mua: ");
                if (ID <= BOOK_LIST.size()) {
                    for (Book book : BOOK_LIST) {
                        if ((book.getID()) == ID) {
                            if (book.getQuantity() == 0) {
                                System.out.println("Sản phẩm đã hết hàng \uD83D\uDE2D");
                            } else {
                                BOOK_CART.add(book);
                                book.setNumberOfBookPurchase();
                                System.out.println("Sản phẩm " + ID + " đã được thêm vào giỏ!");
                            }
                        }
                    }
                    return BOOK_CART;
                } else throw new InvalidChoiceException("Lựa chọn không hợp lệ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public void customizeBook() {
        do {
            try {
                System.out.println("""
                        Hôm nay bạn muốn làm gì?
                        1. Thêm sách vào cửa hàng
                        2. Xóa sách ra khỏi cửa hàng
                        3. Sửa thông tin của sách
                        4. Quay lại
                        """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                switch (choice) {
                    case 1:
                        addNewBook();
                        break;
                    case 2:
                        removeOldBook();
                        break;
                    case 3:
                        editBookInformation();
                        break;
                    case 4:
                        return;
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public void editBookInformation() throws ParseException {
        do {
            try {
                BOOK_LIST.forEach(Book -> System.out.println("ID: " + Book.getID() + ". " + Book.getNameBook() + " | " + Book.getType()));
                System.out.println("""
                Bạn muốn sửa thông tin nào của sách?
                1. Tên sách
                2. Thể loại
                3. Năm xuất bản
                4. Nội dung
                5. Giá tiền
                6. Số lượng sách còn
                7. Quay lại
                """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                int id = Input.choiceIntegerInput("Nhập ID sách muốn sửa: ");
                switch (choice) {
                    case 1:
                        for (Book book : BOOK_LIST) {
                            if (book.getID() == id) {
                                System.out.println(book.getNameBook());
                                String editBookName = Input.promt("Nhập tên mới cho sách: ");
                                book.setNameBook(editBookName);
                                System.out.println("Sách đã được chỉnh sửa thành công!");
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (Book book : BOOK_LIST) {
                            if (book.getID() == id) {
                                System.out.println(book.getType());
                                String editBookType = Input.promt("Nhập thể loại mới cho sách: ");
                                book.setType(editBookType);
                                System.out.println("Sách đã được chỉnh sửa thành công!");
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (Book book : BOOK_LIST) {
                            if (book.getID() == id) {
                                System.out.println(book.getFormatYearOfPublication());
                                String editBookDate = Input.promt("Nhập ngày mới cho sách: ");
                                book.setYearOfPublication(editBookDate);
                                System.out.println("Sách đã được chỉnh sửa thành công!");
                                break;
                            }
                        }
                        break;
                    case 4:
                        for (Book book : BOOK_LIST) {
                            if (book.getID() == id) {
                                System.out.println(book.getDetail());
                                String editBookDetail = Input.promt("Nhập nội dung mới cho sách: ");
                                book.setDetail(editBookDetail);
                                System.out.println("Sách đã được chỉnh sửa thành công!");
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (Book book : BOOK_LIST) {
                            if (book.getID() == id) {
                                System.out.println(book.getPrice());
                                String editBookPrice = Input.promt("Nhập giá mới cho sách: ");
                                book.setPrice(Integer.parseInt(editBookPrice));
                                System.out.println("Sách đã được chỉnh sửa thành công!");
                                break;
                            }
                        }
                        break;
                    case 6:
                        for (Book book : BOOK_LIST) {
                            if (book.getID() == id) {
                                System.out.println(book.getQuantity());
                                String editBookQuantity = Input.promt("Nhập tồn mới cho sách: ");
                                book.setQuantity(Long.parseLong(editBookQuantity));
                                System.out.println("Sách đã được chỉnh sửa thành công!");
                                break;
                            }
                        }
                        break;
                    case 7: return;
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public void removeOldBook() {
        int bookID = Input.choiceIntegerInput("Nhập ID sách muốn xóa: ");
        for (int i = 0; i < BOOK_LIST.size(); i++) {
            if (BOOK_LIST.get(i).getID() == bookID) {
                BOOK_LIST.remove(i);
                System.out.println("Sách đã được xóa khỏi list");
                break;
            }
        }
    }

    public void addNewBook() {
        do {
            try {
                String bookName = Input.promt("Nhập tên sách: ");
                String yearOfPublication = Input.promt("Nhập năm xuất bản: ");
                String bookType = Input.promt("Nhập thể loại sách: ");
                String detail = Input.promt("Nhập nội dung sách: ");
                int price = Input.choiceIntegerInput("Nhập giá sách: ");
                long bookQuantity = Input.choiceIntegerInput("Nhập số lượng sách: ");
                if (BOOK_LIST.isEmpty()) {
                    BOOK_LIST.add(new BookConcreteBuilder().setNameBook(bookName).setYearOfPublication(yearOfPublication)
                            .setType(bookType).setDetail(detail).setPrice(price).setQuantity(bookQuantity).build());
                } else {
                    boolean checkExit = false;
                    for (Book book : BOOK_LIST) {
                        if (book.getNameBook().equalsIgnoreCase(bookName)) {
                            checkExit = true;
                            break;
                        }
                    }
                    if (!checkExit) {
                        BOOK_LIST.add(new BookConcreteBuilder().setNameBook(bookName).setYearOfPublication(yearOfPublication)
                                .setType(bookType).setDetail(detail).setPrice(price).setQuantity(bookQuantity).build());
                        System.out.println("Sách đã được thêm vào list");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void delete(Book book) {

    }
}
