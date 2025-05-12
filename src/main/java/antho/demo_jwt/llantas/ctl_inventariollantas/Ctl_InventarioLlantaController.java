package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<?> registerInventarioLlanta(@RequestBody Ctl_InventarioLlanta request) {
        ctlInventarioLlantaService.registerInventarioLlanta(request);
        return ResponseEntity.ok("Inventario de llanta registrado");
    }

}
