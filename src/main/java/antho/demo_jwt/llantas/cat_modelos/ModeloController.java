package antho.demo_jwt.llantas.cat_modelos;

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
@RequestMapping("/api/v2/modelos")
@RequiredArgsConstructor
public class ModeloController {

    private final ModeloService modeloService;
    //obtener todos los modelos
    @GetMapping(value = "/all")
    public ResponseEntity<List<Modelo>> getAllModelos(){
        List<Modelo> modelo = modeloService.getAllModelos();
        return ResponseEntity.ok(modelo);
    }
    //registrar modelo
    @PostMapping(value="/register")
    public ResponseEntity<?> registerModelo(@RequestBody Modelo request){
        modeloService.registerModelo(request);
        return ResponseEntity.ok("Modelo registrado");
    }

    //editar modelo
    @PutMapping(value="/edit")
    public ResponseEntity<?> editModelo(@RequestBody Modelo request){
        modeloService.editModelo(request);
        return ResponseEntity.ok("Modelo editado");
    }

    //eliminar modelo
    @PutMapping(value="/delete")
    public ResponseEntity<?> deleteModelo(@RequestBody Modelo request){
        modeloService.deleteModelo(request.getIdModelo());
        return ResponseEntity.ok("Modelo eliminado");
    }
}
