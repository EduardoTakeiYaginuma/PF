package insper.pf.classes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Usuario {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String password;
}
