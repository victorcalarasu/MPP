package Service;

import Domain.SaleEntity;
import Repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SaleService {
    public static final Logger log= LoggerFactory.getLogger(SaleService.class);

    @Autowired
    private SaleRepository SaleRepository;

    public void addBuy(SaleEntity buyEntity) {
        log.trace("addSale called: sale={}",buyEntity);
        SaleRepository.save(buyEntity);
        log.trace("addSale executed successfully");
    }

    public void deleteSale(Long id){
        log.trace("removeSale called : id={}",id);
        SaleRepository.deleteById(id);
        log.trace("removeSale executed successfully");
    }

    public SaleEntity getOne(Long id){
        log.trace("getOne called");
        Optional<SaleEntity> sale= SaleRepository.findById(id);
        if(sale.isPresent()){
            log.trace("getOne successfully executed");
            return sale.get();
        }
        throw new RuntimeException("Sale does not exist!");

    }

    public Set<SaleEntity> getAll(){
        log.trace("getAll called");
        Iterable<SaleEntity> sales= SaleRepository.findAll();
        log.trace("getAll successfully executed");
        return StreamSupport.stream(sales.spliterator(),false).collect(Collectors.toSet());
    }

    public Long mostSoldBook(){
        log.trace("bookreport called");
        Iterable<SaleEntity> sales= SaleRepository.findAll();
        List<Long> books=new ArrayList<Long>();
        sales.forEach(x->books.add(x.getClientid()));
        books.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse((long)-1);
        return null;
    }


}
