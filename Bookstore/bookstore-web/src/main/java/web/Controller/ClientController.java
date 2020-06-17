package web.Controller;

import Service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.Converter.ClientConverter;
import web.DTO.BooksDto;
import web.DTO.ClientDto;
import web.DTO.ClientsDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    public static final Logger log= LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients" , method= RequestMethod.POST)
    void addClient(@RequestBody ClientDto clientDto){
        log.trace("addClient - called");
        clientService.addClient(clientConverter.convertDtoToModel(clientDto));
        log.trace("addClient - executed");
    }

    @RequestMapping(value="/clients/{id}", method=RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("deleteClient - called");
        clientService.deleteClient(id);
        log.trace("deleteClient - executed");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/clients/{id}",method=RequestMethod.PUT)
    void updateClient(@PathVariable Long id,
                    @RequestBody ClientDto clientDto){
        log.trace("updateClient - called");
        clientDto.setId(id);
        clientService.updateClient(
                clientConverter.convertDtoToModel(clientDto)
        );

        log.trace("updateClient - executed");
    }

    @RequestMapping(value="/clients/{id}",method=RequestMethod.GET)
    ClientDto getByID(@PathVariable Long id){
        log.trace("getByID - called");
        return clientConverter.convertModelToDto(clientService.getOne(id));
    }

    @RequestMapping(value="/clients",method=RequestMethod.GET)
    List<ClientDto> getAll(){
        log.trace("getAll - called");
        return new ArrayList<>(clientConverter.convertModelsToDtos(clientService.getAllClients()));
    }

    @RequestMapping(value="/clients/filter/name/{genre}",method=RequestMethod.GET)
    ClientsDto filterClientsByName(@PathVariable String name){
        log.trace("filter by name - called");
        return new ClientsDto(clientConverter.convertModelsToDtos(clientService.filterByName(name)));
    }
}
