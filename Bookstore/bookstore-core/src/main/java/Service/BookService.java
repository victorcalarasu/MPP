package Service;

import Domain.Book;
import Repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    public static final Logger log= LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository BookRepository;

    public void addBook(Book book){
        log.trace("addBook called: book={}",book);
        BookRepository.save(book);
        log.trace("addBook successfully executed");

    }

    public void deleteBook(Long id){
        log.trace("deleteBook called: id={}",id);
        BookRepository.deleteById(id);
        log.trace("deleteBook successfully executed");
    }

    public void updateBook(Book book) {
       log.trace("updateBook called: book={}",book);
       BookRepository.findById(book.getId()).ifPresent(b->{
           b.setAuthor(book.getAuthor());
           b.setGenre(book.getGenre());
           b.setSerialnumber(book.getSerialnumber());
           b.setName(book.getName());
           BookRepository.saveAndFlush(b);
           log.debug("updateBook - updated: b={}", b);
       });
       log.trace("updateBook successfully executed");
    }

    public Book getOne(Long id){
        log.trace("getOne called: id={}",id);
        Optional<Book> b= BookRepository.findById(id);
        if(b.isPresent()){
            log.trace("getOne executed successfully");
            return b.get();
        }
        throw new RuntimeException("Book does not exist!");
    }

    public Set<Book> getAllBooks(){
        log.trace("getAllBooks called");
        Iterable<Book> b = BookRepository.findAll();
        log.trace("getAllBooks successfully executed");
        return StreamSupport.stream(b.spliterator(),false).collect(Collectors.toSet());
    }

    public List<Book> bookOfTheGenre(String genre) {
        log.trace("bookOfTheGenre called: genre={}",genre);
        Iterable<Book>b= BookRepository.findAll();
        log.trace("booksOfTheGenre successfully executed");
        return StreamSupport.stream(b.spliterator(),false).filter((e)-> e.getGenre().equals(genre)).collect(Collectors.toList());
    }

    // Returns the books written by the given authors
    public Set<Book> filterByAuthor(String author){
        log.trace("filterByAuthor called: author={}",author);
        Iterable<Book>b= BookRepository.findAll();
        log.trace("filterByAuthor successfully executed");
        return StreamSupport.stream(b.spliterator(),false).filter((e)-> e.getAuthor().equals(author)).collect(Collectors.toSet());

    }
}
