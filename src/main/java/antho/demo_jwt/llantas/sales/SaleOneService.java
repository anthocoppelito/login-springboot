package antho.demo_jwt.llantas.sales;

import org.springframework.stereotype.Service;

import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlanta;
import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlantaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleOneService {

    private final SaleOneRepository saleOneRepository;
    private final Ctl_InventarioLlantaService ctlInventarioLlantaService;

    //venta individual
    public void makeSaleOne(RegisterSaleOne registerSaleOne) {

        if (ctlInventarioLlantaService.checkStock(registerSaleOne.getId(),registerSaleOne.getQuantity())){

            //quitar stock comprado
            ctlInventarioLlantaService.removeStock(registerSaleOne.getId(), registerSaleOne.getQuantity());

            //crear venta
            Ctl_InventarioLlanta llanta = ctlInventarioLlantaService.getLlantaEntityById(registerSaleOne.getId());
            SaleOne saleOne = SaleOne.builder()
                .llantas(llanta)
                .amount(registerSaleOne.getQuantity())
                .price(llanta.getNum_preciobasico())
                .totalPrice(llanta.getNum_preciobasico() * registerSaleOne.getQuantity())
                .build();

            //guardar venta
            saleOneRepository.save(saleOne);

        }


        // Implement the logic to register a sale
        // You can use the saleOneRepository to save the sale
        // and update the inventory using ctlInventarioLlantaService
    }

}
