package jogadorRipoProva.demo;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class Jogador {

    @Id
    private String id;
    private String nome;
    private Integer idade;
    private List<Integer> times;



}
