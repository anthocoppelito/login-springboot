package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //editar llanta
    @PutMapping(value = "/edit")
    public ResponseEntity<?> editLlanta(@RequestBody Ctl_InventarioLlantaEdit request) {
        ctlInventarioLlantaService.editLlanta(request);
        return ResponseEntity.ok("Llanta editada");
    }

    //borrar llanta
    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteLlanta(@RequestBody Ctl_InventarioLlantaEdit request) {
        ctlInventarioLlantaService.deleteLlantaPhysically(request.idLlanta);
        return ResponseEntity.ok("Llanta eliminada");
    }

    //añadir stock
    @PutMapping(value = "/addStock")
    public ResponseEntity<?> addStock(@RequestBody Ctl_InventarioLlantaEdit request) {
        ctlInventarioLlantaService.addStock(request.idLlanta, request.numExistencia);
        return ResponseEntity.ok("Stock añadido");
    }

    //remover stock
    @PutMapping(value = "/removeStock")
    public ResponseEntity<?> removeStock(@RequestBody Ctl_InventarioLlantaEdit request) {
        ctlInventarioLlantaService.removeStock(request.idLlanta, request.numExistencia);
        return ResponseEntity.ok("Stock removido");
    }


}
