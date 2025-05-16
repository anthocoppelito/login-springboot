package antho.demo_jwt.llantas.sales;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlanta;
import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlantaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesListService {
    private final Ctl_InventarioLlantaService ctlInventarioLlantaService;
    private final SaleOneService saleOneService;
    private final SalesListRepository salesListRepository;

    public void createSaleList(RegisterSalesList request){

        //crear lista de ventas individuales
        List<SaleOne> salesList = request.getSalesList().stream().map(saleOne -> {
            // Obtener el producto desde la base de datos
            Ctl_InventarioLlanta llanta = ctlInventarioLlantaService.getLlantaEntityById(saleOne.getId());

            //registrar y guardar venta individual
            RegisterSaleOne registerSaleOne = new RegisterSaleOne(saleOne.getId(), saleOne.getAmount());
            saleOneService.makeSaleOne(registerSaleOne);

            // Crear una instancia de SaleOne para añadir a la lista de ventas
            return SaleOne.builder()
                .llantas(llanta)
                .amount(saleOne.getAmount())
                .price(llanta.getNum_preciobasico())
                .totalPrice(llanta.getNum_preciobasico() * saleOne.getAmount())
                .build();
        }).toList();

        //calcular precio total de la venta
        double totalPrice = salesList.stream()
            .mapToDouble(SaleOne::getTotalPrice)
            .sum();

        // Crear la venta principal(lista)
        SalesList salesListEntity = SalesList.builder()
            .salesList(salesList)
            .totalPrice(totalPrice)
            .build();

        //guardar la venta principal(lista)
        salesListRepository.save(salesListEntity);
    }

    //obtener todas las ventaslist
    public List<SalesList> getAllSalesList() {
        return salesListRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    //conseguir datos de una ventalist por id
    public SalesList getSalesListById(Integer id) {
        return salesListRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada, verifica el ID"));
    }


}
