package jogadorRipoProva.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public void salvarJogador(Jogador jogador) {
        if (jogador.getNome() == null || jogador.getNome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome inválido");
        }
        if (jogador.getIdade() == null || jogador.getIdade() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Idade inválida");
        }
        if (jogador.getTimes().isEmpty()){
            jogadorRepository.save(jogador);
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Times deve ser uma lista vazia");

    }

    public List<Jogador> listarJogadores() {
        return jogadorRepository.findAll();
    }

    public void adicionaJogadorTime(String id_jogador, Integer id_time) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Time> time = restTemplate.getForEntity(
                "http://localhost:8081/time/" + id_time,
                Time.class);
        Jogador jogador = jogadorRepository.findById(id_jogador).orElse(null);
        if (time.getStatusCode().is2xxSuccessful()) {
            assert jogador != null;
            List<Integer> times = jogador.getTimes();
           times.add(id_time);
           jogador.setTimes(times);
           jogadorRepository.save(jogador);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar time com id " + id_time);
        }

    }
}
