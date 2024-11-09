package insper.pf.classes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Carro {

    @Id
    private String id;
    private String marca;
    private String modelo;
    private Integer ano;
    private Usuario dono;
}
