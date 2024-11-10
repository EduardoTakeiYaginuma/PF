package insper.pf.service;

import insper.pf.repositorys.CarroRepository;
import insper.pf.classes.Carro;
import insper.pf.classes.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Carro criarCarro(Carro carro) {
        if (carro.getMarca() == null || carro.getModelo() == null || carro.getAno() == null) {
            throw new IllegalArgumentException("Marca, modelo e ano são obrigatórios");
        }
        if (carro.getAno() < 1900 || carro.getAno() > 2021) {
            throw new IllegalArgumentException("Ano inválido");
        }
        return carroRepository.save(carro);
    }

    public Carro associarCarroDono(String idCarro, String cpf, String authorization) {
        Carro carro = carroRepository.findById(idCarro).get();
        Usuario usuario = usuarioService.getUsuario(cpf, authorization);
        if (carro == null) {
            throw new IllegalArgumentException("Carro não encontrado");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        carro.setDono(usuario);
        return carroRepository.save(carro);
    }

    public List<Carro> listaCarros(String id) {
        if (id != null) {
            Optional<Carro> carroOptional = carroRepository.findById(id);
            if (carroOptional.isPresent()) {
                return carroRepository.findByDono(id); // Supondo que você tenha um método para buscar carros pelo dono
            } else {
                throw new IllegalArgumentException("Carro não encontrado");
            }
        }
        return carroRepository.findAll();
    }


}
