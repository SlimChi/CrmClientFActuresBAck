package clientcrm.frcs.auth;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "Le nom ne doit pas être vide")
    @NotEmpty(message = "Le nom ne doit pas être vide")
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String firstName;
    @NotNull(message = "Le prénom ne doit pas être vide")
    @NotEmpty(message = "Le prénom ne doit pas être vide")
    @NotBlank(message = "Le prénom ne doit pas être vide")
    private String lastName;
    @NotNull(message = "L'email ne doit pas être vide")
    @NotEmpty(message = "L'email ne doit pas être vide")
    @NotBlank(message = "L'email ne doit pas être vide")
    @Email(message = "L'email n'est pas conforme")
    private String email;
    @NotNull(message = "Le mot de passe ne doit pas être vide")
    @NotEmpty(message = "Le mot de passe ne doit pas être vide")
    @NotBlank(message = "Le mot de passe ne doit pas être vide")
    @Size(min = 8, max = 16, message = "Le mot de passe doit être entre 8 et 16 caracteres")
    private String password;
    @NotNull(message = "Le telephone ne doit pas être vide")
    @NotEmpty(message = "Le telephone ne doit pas être vide")
    @NotBlank(message = "Le telephone ne doit pas être vide")
    private String phone;


}
