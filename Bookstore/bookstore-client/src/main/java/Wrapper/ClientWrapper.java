package Wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import web.DTO.BooksDto;
import web.DTO.ClientDto;
import web.DTO.ClientsDto;

@Component
public class ClientWrapper {
    @Value("http://localhost:8080/api/clients")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    public void save(ClientDto dto){
        restTemplate.postForObject(URL,dto,ClientDto.class);
    }

    public void delete(Long id){
        restTemplate.delete(URL + "/" + id);
    }

    public void update(ClientDto dto){
        restTemplate.put(URL+"/{id}",dto.getId());
    }

    public ClientDto getByID(Long id){
        return restTemplate.getForObject(URL+"/"+id, ClientDto.class);
    }

    public ClientsDto getAll(){
        return restTemplate.getForObject(URL,ClientsDto.class);
    }
    public ClientsDto filterByName(String name){
        return restTemplate.getForObject(URL+"/filter/name/" + name,ClientsDto.class);
    }

}
