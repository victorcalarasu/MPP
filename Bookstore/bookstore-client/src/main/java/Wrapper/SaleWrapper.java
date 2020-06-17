package Wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import web.DTO.ClientDto;
import web.DTO.ClientsDto;
import web.DTO.SaleDto;
import web.DTO.SalesDto;

@Component
public class SaleWrapper {
    @Value("http://localhost:8080/api/sales")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    public void save(SaleDto dto){
        restTemplate.postForObject(URL,dto,SaleDto.class);
    }

    public void delete(Long id){
        restTemplate.delete(URL + "/" + id);
    }

    public SaleDto getByID(Long id){
        return restTemplate.getForObject(URL+"/"+id, SaleDto.class);
    }

    public SalesDto getAll(){
        return restTemplate.getForObject(URL,SalesDto.class);
    }

}