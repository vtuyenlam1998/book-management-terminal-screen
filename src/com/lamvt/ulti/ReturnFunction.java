package com.lamvt.ulti;

import com.lamvt.entity.Customer;
import com.lamvt.entity.Staff;
import com.lamvt.view.BookStoreView;
import com.lamvt.service.impl.BookServiceImpl;
import com.lamvt.service.impl.UserServiceImpl;
import com.lamvt.exception.InvalidChoiceException;

public class ReturnFunction {
    public static BookStoreView bookStoreView = BookStoreView.getInstance();
    public static BookServiceImpl bookServiceImpl = BookServiceImpl.getInstance();
    public static UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    public static void backPage() {
        do {
            try {
                System.out.println("""
                                            
                        1. Mua sách online
                        2. Quay lại trang trước
                        3. Quay lại Main Menu
                        4. Log Out
                        5. Đóng chương trình
                                """);
                int backChoice = Input.choiceIntegerInput("Enter your choice: ");

                switch (backChoice) {
                    case 1:
                        bookServiceImpl.selecBookToBuy();
                        break;
                    case 2:
                        return;
                    case 3:
                        if (userServiceImpl.getCurrentUser() instanceof Customer) {
                            bookStoreView.displayUserInterface();
                        } else if (userServiceImpl.getCurrentUser() instanceof Staff) {
                            bookStoreView.displayStaffInterface();
                        }
                    case 4:
                        bookStoreView.displayMainMenu();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        throw new InvalidChoiceException("Lựa chọn không phù hợp");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
