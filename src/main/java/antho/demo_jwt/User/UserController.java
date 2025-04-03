package antho.demo_jwt.User;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Obtener todos los datos
    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    //Obtener datos no sensibles
    @GetMapping("/public/{id}")
    public ResponseEntity<UserDTO> getUserSummaryById(@PathVariable Integer id) {
        UserDTO userDTO = userService.getUserDTOById(id);
        return ResponseEntity.ok(userDTO);
    }

    //Obtener datos no sensibles por username
    @GetMapping("/public/username/{username}")
    public ResponseEntity<UserDTO> getUserSummaryByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getUserDTOByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

    //Obtener todos los usuarios
    @GetMapping("/public/username/all")
    public ResponseEntity<List<UserDTO>> getUserAllUser() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Actualizar datos
    @PutMapping("/admin/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UpdateUserDTO updateUserDTO) {
        UserDTO updatedUser = userService.updateUser(id, updateUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    //Actualizar a admin
    @PutMapping("/admin/setAdmin/{username}")
    public ResponseEntity<?> setAdmin(@PathVariable String username){
        userService.updateUserRole(username, "ADMIN");
    return ResponseEntity.ok("Rol actualizado a ADMIN");
    }

    //Actualizar a cajero
    @PutMapping("/admin/setCajero/{username}")
    public ResponseEntity<?> setCajero(@PathVariable String username){
        userService.updateUserRole(username, "CAJERO");
    return ResponseEntity.ok("Rol actualizado a CAJERO");
    }

    //Actualizar a bodega
    @PutMapping("/admin/setBodega/{username}")
    public ResponseEntity<?> setBodega(@PathVariable String username){
        userService.updateUserRole(username, "BODEGA");
    return ResponseEntity.ok("Rol actualizado a BODEGA");
    }

    //Actualizar a usuario
    @PutMapping("/admin/setUsuario/{username}")
    public ResponseEntity<?> setUsuario(@PathVariable String username){
        userService.updateUserRole(username, "USER");
    return ResponseEntity.ok("Rol actualizado a USUARIO");
    }

    

    




}
