package antho.demo_jwt.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//validaciones al registrar
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "El usuario no puede estar vacío")
    @Size(min = 7, max = 40, message = "El usuario debe tener entre 7 y 40 caracteres")
    String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 40, message = "El usuario debe tener entre 6 y 40 caracteres")
    String password;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres")
    String firstname;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 3, max = 60, message = "El apellido debe tener entre 3 y 60 caracteres")
    String lastname;

    @NotBlank(message = "El país no puede estar vacío")
    @Size(min = 4, max = 50, message = "El Pais debe tener entre 4 y 50 caracteres ")
    String country;

}
