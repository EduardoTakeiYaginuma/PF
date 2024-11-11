package insper.pf.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUsuarioDTO {
    private String nome;
    private String cpf;
    private String email;
    private String password;
    private String role;
}