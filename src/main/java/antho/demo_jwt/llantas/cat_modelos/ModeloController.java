package antho.demo_jwt.llantas.cat_modelos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
