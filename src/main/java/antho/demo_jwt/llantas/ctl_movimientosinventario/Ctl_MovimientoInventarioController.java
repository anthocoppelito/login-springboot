package antho.demo_jwt.llantas.ctl_movimientosinventario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/movimientoInventario")
@RequiredArgsConstructor
public class Ctl_MovimientoInventarioController {

    private final Ctl_MovimientoInventarioService ctlMovimientoInventarioService;
    // Obtener todos los movimientos de inventario
    @GetMapping(value = "/all")
    public ResponseEntity<List<Ctl_MovimientoInventario>> getAllMovimientosInventario() {
        List<Ctl_MovimientoInventario> movimientos = ctlMovimientoInventarioService.getAllMovimientosInventario();
        return ResponseEntity.ok(movimientos);
    }
    //registrar movimiento de inventario
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerMovimientoInventario(@RequestBody Ctl_MovimientoInventario request) {
        ctlMovimientoInventarioService.registerMovimientoInventario(request);
        return ResponseEntity.ok("Movimiento de inventario registrado");
    }

}
