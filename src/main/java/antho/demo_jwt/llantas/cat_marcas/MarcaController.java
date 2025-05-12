package antho.demo_jwt.llantas.cat_marcas;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/marcas")
@RequiredArgsConstructor
public class MarcaController {


    private final MarcaService marcaService;
    //obtener todos las marcas
    @GetMapping(value = "/all")
    public ResponseEntity<List<Marca>> getAllMarcas(){
        List<Marca> marca = marcaService.getAllMarcas();
        return ResponseEntity.ok(marca);
    }
    //registrar marca
    @PostMapping(value="/register")
    public ResponseEntity<?> registerMarca(@RequestBody Marca request){
        marcaService.registerMarca(request);
        return ResponseEntity.ok("Marca registrada");
    }
}
