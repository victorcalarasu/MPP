package web.Converter;

import Domain.Book;
import org.springframework.stereotype.Component;
import web.DTO.BookDto;

@Component
public class BookConverter extends BaseConverter<Book, BookDto> {

    @Override
    public Book convertDtoToModel(BookDto dto){
        Book book=Book.builder()
                .serialnumber(dto.getSerialnumber())
                .name(dto.getName())
                .genre(dto.getGenre())
                .author(dto.getAuthor())
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book){
        BookDto dto=BookDto.builder()
                .serialnumber(book.getSerialnumber())
                .name(book.getName())
                .genre(book.getGenre())
                .author(book.getAuthor())
                .build();
        dto.setId(book.getId());
        return dto;
    }
}
