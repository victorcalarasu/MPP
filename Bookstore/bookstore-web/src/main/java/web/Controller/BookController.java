package web.Controller;

import Service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.Converter.BookConverter;
import web.DTO.BookDto;
import web.DTO.BooksDto;
import web.DTO.ClientDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    public static final Logger log= LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/books" , method= RequestMethod.POST)
    void addBook(@RequestBody BookDto bookDto){
        log.trace("addBook - called");
        bookService.addBook(bookConverter.convertDtoToModel(bookDto));
        log.trace("addBook - executed");
    }

    @RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
    ResponseEntity<?> deleteBook(@PathVariable Long id){
        log.trace("deleteBook - called");
        bookService.deleteBook(id);
        log.trace("deleteBook - executed");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/books/{id}",method=RequestMethod.PUT)
    void updateBook(@PathVariable Long id,
                       @RequestBody BookDto bookDto){
        log.trace("updateBook - called");
        bookDto.setId(id);
        bookService.updateBook(
                bookConverter.convertDtoToModel(bookDto)
        );

        log.trace("updateBook - executed");
    }

    @RequestMapping(value="/books/{id}",method=RequestMethod.GET)
    BookDto getByID(@PathVariable Long id){
        log.trace("getByID - called");
        return bookConverter.convertModelToDto(bookService.getOne(id));
    }

    @RequestMapping(value="/books",method=RequestMethod.GET)
    List<BookDto> getAll(){
        log.trace("getAll - called");
        return new ArrayList<>(bookConverter.convertModelsToDtos(bookService.getAllBooks()));

    }

    @RequestMapping(value="/books/filter/author/{author}",method=RequestMethod.GET)
    BooksDto filterBooksByAuthor(@PathVariable String author)
    {
     log.trace("filter by author - called");
     return new BooksDto(bookConverter.convertModelsToDtos(bookService.filterByAuthor(author)));
    }

    @RequestMapping(value="/books/filter/genre/{genre}",method=RequestMethod.GET)
    BooksDto filterBooksByGenre(@PathVariable String genre){
        log.trace("filter by genre - called");
        return new BooksDto(bookConverter.convertModelsToDtos(bookService.bookOfTheGenre(genre)));
    }




}
