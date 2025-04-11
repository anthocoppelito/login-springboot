package antho.demo_jwt.Sale;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll().stream()
            .map(sale -> new SaleDTO(
                sale.getId(), 
                sale.getProduct(), 
                sale.getAmount()
                ))
                .collect(Collectors.toList());   
    }

    public void registerSale(SaleDTO saleDTO) {
        Sale sale = Sale.builder()
            .product(saleDTO.getProduct())
            .amount(saleDTO.getAmount())
            .build();
        // Guardar venta en la base de datos
        saleRepository.save(sale);
    }
}
