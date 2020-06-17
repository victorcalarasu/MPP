package web.Converter;

import Domain.SaleEntity;
import org.springframework.stereotype.Component;
import web.DTO.SaleDto;

@Component
public class SaleConverter extends BaseConverter<SaleEntity, SaleDto>{
    @Override
    public SaleEntity convertDtoToModel(SaleDto dto){
        SaleEntity sale= SaleEntity.builder()
                .buyid(dto.getBuyid())
                .bookid(dto.getBookid())
                .clientid(dto.getClientid())
                .build();
        sale.setId(dto.getId());
        return sale;
    }

    @Override
    public SaleDto convertModelToDto(SaleEntity sale){
        SaleDto dto=SaleDto.builder()
                .buyid(sale.getBuyid())
                .bookid(sale.getBookid())
                .clientid(sale.getClientid())
                .build();
        dto.setId(sale.getId());
        return dto;

    }
}
