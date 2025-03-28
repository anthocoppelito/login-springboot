package antho.demo_jwt.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String lastname;
    private String firstname;
    private String country;
    private String role;
}
