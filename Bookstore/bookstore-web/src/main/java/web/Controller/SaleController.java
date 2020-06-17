package web.Controller;

import Service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.Converter.SaleConverter;
import web.DTO.BookDto;
import web.DTO.BooksDto;
import web.DTO.SaleDto;
import web.DTO.SalesDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SaleController {
    public static final Logger log= LoggerFactory.getLogger(BookController.class);
    @Autowired
    private SaleService saleService;
    @Autowired
    private SaleConverter saleConverter;

    @RequestMapping(value = "/sales" , method= RequestMethod.POST)
    void addSale(@RequestBody SaleDto saleDto){
        log.trace("addSale - called");
        saleService.addBuy(saleConverter.convertDtoToModel(saleDto));
        log.trace("addSale - executed");
    }

    @RequestMapping(value="/sales/{id}", method=RequestMethod.DELETE)
    ResponseEntity<?> deleteSale(@PathVariable Long id){
        log.trace("deleteSale - called");
        saleService.deleteSale(id);
        log.trace("deleteSale - executed");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/sales/{id}",method=RequestMethod.GET)
    SaleDto getByID(@PathVariable Long id){
        log.trace("getByID - called");
        return saleConverter.convertModelToDto(saleService.getOne(id));
    }

    @RequestMapping(value="/sales",method=RequestMethod.GET)
    List<SaleDto> getBooks(){
        log.trace("getAll - called");
        return new ArrayList<>(saleConverter.convertModelsToDtos(saleService.getAll()));

    }
}
