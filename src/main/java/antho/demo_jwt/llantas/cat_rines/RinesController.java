package antho.demo_jwt.llantas.cat_rines;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/rines")
@RequiredArgsConstructor
public class RinesController {

    private final RinesService rinesService;
    // Obtener todos los rines
    @GetMapping(value = "/all")
    public ResponseEntity<List<Rines>> getAllRines() {
        List<Rines> rines = rinesService.getAllRines();
        return ResponseEntity.ok(rines);
    }
    //Registrar rines
    @PostMapping(value="/register")
    public ResponseEntity<?> registerRines(@RequestBody Rines request) {
        rinesService.registerRines(request);
        return ResponseEntity.ok("Rines registrados");
    }

    //editar rin
    @PutMapping(value="/edit")
    public ResponseEntity<?> editRines(@RequestBody Rines request) {
        rinesService.editRines(request);
        return ResponseEntity.ok("Rin editado");
    }

    //eliminar rin
    @PutMapping(value="/delete")
    public ResponseEntity<?> deleteRines(@RequestBody Rines request) {
        rinesService.deleteRines(request.getIdRin());
        return ResponseEntity.ok("Rin eliminado");
    }

}
