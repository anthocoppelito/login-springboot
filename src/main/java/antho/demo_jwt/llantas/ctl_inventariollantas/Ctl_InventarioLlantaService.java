package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import antho.demo_jwt.llantas.cat_marcas.Marca;
import antho.demo_jwt.llantas.cat_marcas.MarcaRepository;
import antho.demo_jwt.llantas.cat_modelos.Modelo;
import antho.demo_jwt.llantas.cat_modelos.ModeloRepository;
import antho.demo_jwt.llantas.cat_rines.Rines;
import antho.demo_jwt.llantas.cat_rines.RinesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Ctl_InventarioLlantaService {

    private final Ctl_InventarioLlantaRepository ctlInventarioLlantaRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final RinesRepository rinesRepository;

    public List<Ctl_InventarioLlanta> getAllInventariosLlanta() {
        return ctlInventarioLlantaRepository.findAll(Sort.by(Sort.Direction.ASC, "idLlanta"));
    }

    public void registerInventarioLlanta(Ctl_InventarioLlantaDTO request) {
        
        //obtener la marca y revisar si existen
        Marca marca = marcaRepository.findById(request.id_marca)
            .orElseThrow(() -> new RuntimeException("Marca no encontrada, verifica el ID"));
        Modelo modelo = modeloRepository.findById(request.id_modelo)
            .orElseThrow(() -> new RuntimeException("Modelo no encontrado, verifica el ID"));
        Rines rines = rinesRepository.findById(request.id_rin)
            .orElseThrow(() -> new RuntimeException("Rin no encontrado, verifica el ID"));

        //se construye la instancia
        Ctl_InventarioLlanta inventario = Ctl_InventarioLlanta.builder()
            .marca(marca)
            .modelo(modelo)
            .rines(rines)
            .num_preciobasico(request.num_preciobasico)
            .fec_alta(LocalDateTime.now(ZoneId.of("America/Mazatlan")))
            .num_existencia(request.num_existencia)
            .build();

        //se guarda 
        ctlInventarioLlantaRepository.save(inventario);

    }
    
    public boolean llantaExists(Integer llanta) {
        // Verifica si la llanta existe
        return ctlInventarioLlantaRepository.findById(llanta).isPresent();
    }

    //checkStock
    public boolean checkStock(Integer id, Integer amount) {
        //verifica si la llanta existe
        if (!llantaExists(id)) {
            throw new RuntimeException("Llanta no registrada");
        }

        //verifica si la cantidad a comprar es mayor a 0
        if (amount <= 0) {
            throw new RuntimeException("La cantidad a comprar debe ser mayor a 0");
        }

        // Verifica si la llanta tiene suficiente stock
        Ctl_InventarioLlanta llanta = ctlInventarioLlantaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Llanta no encontrada"));
        if (llanta.getNum_existencia() >= amount) {
            return true;
        }else{
            throw new RuntimeException("No hay suficiente stock para la llanta: " + id);
        }
    }

    public void removeStock(Integer id, Integer quantity) {
        //verifica si la llanta existe
        if (!llantaExists(id)) {
            throw new RuntimeException("Llanta no registrada");
        }

        //verifica si la cantidad a comprar es mayor a 0
        if (quantity <= 0) {
            throw new RuntimeException("La cantidad a comprar debe ser mayor a 0");
        }

        // Verifica si la llanta tiene suficiente stock
        Ctl_InventarioLlanta llanta = ctlInventarioLlantaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Llanta no encontrada"));
        if (llanta.getNum_existencia() >= quantity) {
            llanta.setNum_existencia(llanta.getNum_existencia() - quantity);
            ctlInventarioLlantaRepository.save(llanta);
        } else {
            throw new RuntimeException("No hay suficiente stock para la llanta: " + id);
        }
    }

    public Ctl_InventarioLlanta getLlantaEntityById(Integer id) {
        return ctlInventarioLlantaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Llanta no encontrada"));
    }

    //obtiene el id de la llanta y la cantidad y regresa el precio unitario y el total (precio unitario * cantidad). antes revisa si hay stock
    //si no hay stock, lanza una excepcion
    public Map<String, Double> getLlantaPrice(Integer id, Integer amount) {
        Ctl_InventarioLlanta llanta = ctlInventarioLlantaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Llanta no encontrada"));
        if (llanta.getNum_existencia() >= amount) {
            double unitPrice = llanta.getNum_preciobasico();
            double totalPrice = unitPrice * amount;
            
            //Crear un mapa con los valores
            Map<String, Double> priceMap = new HashMap<>();
            priceMap.put("unitPrice", unitPrice);
            priceMap.put("totalPrice", totalPrice);
            return priceMap;
        } else {
            throw new RuntimeException("No hay suficiente stock para la llanta: " + id);
        }
    }
    //realizar busqueda. como hay posibles datos nulos, solo se hara la busqueda, si no contiene nulos
    public List<Ctl_InventarioLlanta> searchLlantas(String searchTerm) {
        return ctlInventarioLlantaRepository.findAll(Sort.by(Sort.Direction.ASC, "idLlanta")).stream()
            .filter(llanta ->
            (llanta.getIdLlanta() != null && llanta.getIdLlanta().toString().contains(searchTerm))
            ||
            (llanta.getMarca() != null && llanta.getMarca().getNomMarcas() != null &&
                llanta.getMarca().getNomMarcas().toLowerCase().contains(searchTerm.toLowerCase()))
            ||
            (llanta.getModelo() != null && llanta.getModelo().getNomModelos() != null &&
                llanta.getModelo().getNomModelos().toLowerCase().contains(searchTerm.toLowerCase()))
            ||
            (llanta.getRines() != null && llanta.getRines().getNomRin() != null &&
                llanta.getRines().getNomRin().toLowerCase().contains(searchTerm.toLowerCase()))
        )
        .toList();
        
    }

    


}
