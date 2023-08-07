package view;

public class View {
    public static void main(String[] args){
        BookStoreView bookStore = BookStoreView.getInstance();
        bookStore.displayMainMenu();
    }
}