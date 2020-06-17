package UserInterface;

import Wrapper.BookWrapper;
import Wrapper.ClientWrapper;
import Wrapper.SaleWrapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.DTO.BookDto;
import web.DTO.ClientDto;
import web.DTO.SaleDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
@Component
public class Console {
    @Autowired
    private BookWrapper bookWrapper;
    @Autowired
    private ClientWrapper clientWrapper;
    @Autowired
    private SaleWrapper saleWrapper;

    public void run(){

        printMenu();
        int com;
        System.out.println(">>>");
        Scanner sc= new Scanner(System.in);
        com = sc.nextInt();
        while (com != 0){
            if(com == 1){
                addClients();
            }
            if(com == 2){
                deleteClients();
            }
            if (com == 3){
                updateClients();
            }
            if(com == 4){
                printAllClients();
            }
            if(com == 5){
                addBooks();
            }
            if(com == 6){
                deleteBooks();
            }
            if (com == 7){
                updateBooks();
            }
            if(com == 8){
                printAllBooks();
            }
            if(com == 9){
                booksOfTheGenre();
            }
            if(com == 10){
                addBuy();
            }
            if(com==11){
                filterByAuthor();
            }
            if(com==12){
                filterByName();
            }
            if(com == 13){
                //report();
            }
            printMenu();
            System.out.println(">>>");
            com = sc.nextInt();
        }

    }

    private void printMenu(){
        System.out.println();
        System.out.println("0. Exit.");
        System.out.println("1. Add a client.");
        System.out.println("2. Delete a client.");
        System.out.println("3. Update a client.");
        System.out.println("4. Print all clients.");
        System.out.println("5. Add a book.");
        System.out.println("6. Delete a book.");
        System.out.println("7. Update a book.");
        System.out.println("8. Print all books.");
        System.out.println("9. Print the books of a certain genre.");
        System.out.println("10. Sell a book");
        System.out.println("11. Print the books from a certain author.");
        System.out.println("12. Filter the clients by name");
        System.out.println("13. Most sold book");

    }

    private void addBooks() {
        BookDto book = readBook();
        bookWrapper.save(book);

    }


    private void deleteBooks(){
        System.out.println("Give the id of the book you want to delete: ");
        Scanner sc= new Scanner(System.in);
        long id = sc.nextLong();
        bookWrapper.delete(id);
    }

    private BookDto readBook() {
        System.out.println("Read Book {id,serialNumber, name, author, genre}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String serialNumber = bufferRead.readLine();
            String name = bufferRead.readLine();
            String author =bufferRead.readLine();
            String genre = bufferRead.readLine();// ...

            BookDto book= new BookDto(serialNumber, name, author, genre);
            book.setId(id);

            return book;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void addClients() {
        ClientDto client = readClient();
        clientWrapper.save(client);

    }



    private void deleteClients() {
        System.out.println("Give the id of the client you want to delete: ");
        Scanner sc= new Scanner(System.in);
        long id = sc.nextInt();
        clientWrapper.delete(id);
    }

    private ClientDto readClient() {
        System.out.println("Read client {id,ID, name, phoneNumber}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            long ID = Long.parseLong(bufferRead.readLine());
            String name = bufferRead.readLine();
            long phoneNumber =Long.parseLong(bufferRead.readLine());

            ClientDto client= new ClientDto(ID, name, phoneNumber);
            client.setId(id);

            return client;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    private void printAllBooks() {
        bookWrapper.getAll().getBooks().forEach(System.out::println);

    }

    private void printAllClients(){
        clientWrapper.getAll().getClients().forEach(System.out::println);
    }


    private void booksOfTheGenre(){
        System.out.println("Give the genre you are interested in : ");
        Scanner sc= new Scanner(System.in);
        String genre = sc.next();
        bookWrapper.filterByGenre(genre).getBooks().forEach(System.out::println);
    }

    private void filterByAuthor(){
        System.out.println("Give the author you are interested in : ");
        Scanner sc= new Scanner(System.in);
        String author = sc.next();

        bookWrapper.filterByAuthor(author).getBooks().forEach(System.out::println);
    }

    private void filterByName(){
        System.out.println("Give the name you are interested in : ");
        Scanner sc=new Scanner(System.in);
        String name=sc.next();
        clientWrapper.filterByName(name).getClients().forEach(System.out::println);
    }

    private void updateBooks(){
        BookDto book = readBook();
        bookWrapper.update(book);
    }

    private void updateClients(){
        ClientDto client = readClient();
        clientWrapper.update(client);
    }

    private SaleDto readBuyEntity() {
        System.out.println("Read Buy Entity { id, BuyId, BookId, ClientId }");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            int BuyId = Integer.parseInt(bufferRead.readLine());
            int BookId = Integer.parseInt(bufferRead.readLine());
            int ClientId = Integer.parseInt(bufferRead.readLine());

            SaleDto buyEntity= new SaleDto(BuyId, BookId, ClientId);
            buyEntity.setId(id);

            return buyEntity;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void addBuy() {
        SaleDto buyEntity = readBuyEntity();
        saleWrapper.save(buyEntity);


    }
}
