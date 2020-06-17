package Service;

import Domain.Client;
import Repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {
    public static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository ClientRepository;

    public void addClient(Client client){
        log.trace("addClient called: client={}",client);
        ClientRepository.save(client);
        log.trace("addClient successfully executed");

    }

    public void deleteClient(Long id) {
        log.trace("deleteClient called: id={}",id);
        ClientRepository.deleteById(id);
        log.trace("deleteClient successfully executed");
    }

    public void updateClient(Client client) {
        log.trace("updateClient called: client={}",client);
        ClientRepository.findById(client.getId()).ifPresent(c->{
           c.setId2(client.getId2());
           c.setName(client.getName());
           c.setPhoneNumber(client.getPhoneNumber());
           ClientRepository.saveAndFlush(c);
           log.debug("updateClient - updated: c={}",c);
        });
        log.trace("updateClient successfully executed");
    }

    public Client getOne(Long id){
        log.trace("getOne called: id={}",id);
        Optional<Client> c= ClientRepository.findById(id);
        if(c.isPresent()){
            log.trace("getOne executed successfully");
            return c.get();
        }
        throw new RuntimeException("Client does not exist!");
    }

    public Set<Client> getAllClients(){
        log.trace("getAllClients called");
        Iterable<Client> c = this.ClientRepository.findAll();
        log.trace("getAllClients successfully executed");
        return StreamSupport.stream(c.spliterator(), false).collect(Collectors.toSet());
    }



    // Returns the clients with the given name
    public Set<Client> filterByName(String name) {
        log.trace("filterByName called: name={}",name);
        Iterable<Client>c= ClientRepository.findAll();
        log.trace("filterByName successfully executed");
        return StreamSupport.stream(c.spliterator(),false).filter((e)->e.getName()==name).collect(Collectors.toSet());

    }
}
