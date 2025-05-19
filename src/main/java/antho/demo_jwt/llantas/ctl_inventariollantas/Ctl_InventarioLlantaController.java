package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antho.demo_jwt.User.UserDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/inventarioLlanta")
@RequiredArgsConstructor
public class Ctl_InventarioLlantaController {

    private final Ctl_InventarioLlantaService ctlInventarioLlantaService;
    // Obtener todos los inventarios de llantas
    @GetMapping(value = "/all")
    public ResponseEntity<List<Ctl_InventarioLlanta>> getAllInventariosLlanta() {
        List<Ctl_InventarioLlanta> inventarios = ctlInventarioLlantaService.getAllInventariosLlanta();
        return ResponseEntity.ok(inventarios);
    }
    //registrar inventario de llanta
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerInventarioLlanta(@RequestBody Ctl_InventarioLlantaDTO request) {
        ctlInventarioLlantaService.registerInventarioLlanta(request);
        return ResponseEntity.ok("Inventario de llanta registrado");
    }

    //recibir id llanta y cantidad y regrese el precio unitario y el total(precio unitario * cantidad). antes revisa si hay stock
    @GetMapping("/price/{id}/{amount}")
    public ResponseEntity<Map<String, Double>> getLlantaPrice (@PathVariable Integer id, @PathVariable Integer amount) {
        Map<String, Double> llantaPrice = ctlInventarioLlantaService.getLlantaPrice(id, amount);
        return ResponseEntity.ok(llantaPrice);
    }

    //Realizar busqueda por cualquiera de los campos
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<Ctl_InventarioLlanta>> searchLlanta(@PathVariable String searchTerm) {
        List<Ctl_InventarioLlanta> llanta = ctlInventarioLlantaService.searchLlantas(searchTerm);
        return ResponseEntity.ok(llanta);
    }


}
