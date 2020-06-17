package Wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import web.DTO.BookDto;
import web.DTO.BooksDto;

@Component
public class BookWrapper {
    @Value("http://localhost:8080/api/books")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    public void save(BookDto dto){
        restTemplate.postForObject(URL,dto,BookDto.class);
    }

    public void delete(Long id){
        restTemplate.delete(URL + "/" + id);
    }

    public void update(BookDto dto){
        restTemplate.put(URL+"/{id}",dto.getId());
    }

    public BookDto getByID(Long id){
        return restTemplate.getForObject(URL+"/"+id,BookDto.class);
    }

    public BooksDto getAll(){
        return restTemplate.getForObject(URL,BooksDto.class);
    }

    public BooksDto filterByAuthor(String author){
        return restTemplate.getForObject(URL+"/filter/author/" + author,BooksDto.class);
    }

    public BooksDto filterByGenre(String genre){
        return restTemplate.getForObject(URL+"/filter/genre/" + genre,BooksDto.class);
    }

}
