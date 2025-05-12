package antho.demo_jwt.llantas.cat_movimientosinventario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/movimientosinventario")
@RequiredArgsConstructor
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoInventarioService;
    // Obtener todos los movimientos de inventario
    @GetMapping(value = "/all")
    public ResponseEntity<List<MovimientoInventario>> getAllMovimientosInventario() {
        List<MovimientoInventario> movimientos = movimientoInventarioService.getAllMovimientosInventario();
        return ResponseEntity.ok(movimientos);
    }
    // Registrar movimiento de inventario
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerMovimientoInventario(@RequestBody MovimientoInventario request) {
        movimientoInventarioService.registerMovimientoInventario(request);
        return ResponseEntity.ok("Movimiento de inventario registrado");
    }
}
