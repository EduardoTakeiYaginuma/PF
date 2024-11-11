package insper.pf.service;

import insper.pf.classes.ReturnUsuarioDTO;
import insper.pf.repositorys.ArtigoRepository;
import insper.pf.classes.Artigo;
import insper.pf.classes.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private UsuarioService usuarioService;

//    @Id
//    private String id;
//    //private String autor;
//    private String titulo;
//    private String resumo;
//
    public Artigo criarArtigo(Artigo artigo, String token) {
        ReturnUsuarioDTO usuario = usuarioService.validateUser(token);
        if (!usuario.getPapel().equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas administradores podem criar artigos");
        }
        if (artigo.getTitulo() == null || artigo.getResumo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título e resumo são obrigatórios");
        }
        artigo.setNomeAutor(usuario.getNome());
        return artigoRepository.save(artigo);
    }
//
//    public Artigo associarCarroDono(String idCarro, String cpf, String authorization) {
//        Artigo carro = carroRepository.findById(idCarro).get();
//        Usuario usuario = usuarioService.getUsuario(cpf, authorization);
//        if (carro == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro não encontrado");
//        }
//        if (usuario == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
//        }
//        carro.setDono(usuario);
//        return carroRepository.save(carro);
//    }
//
    public List<Artigo> listaArtigos(String id, String token) {
        ReturnUsuarioDTO usuario = usuarioService.validateUser(token);
        if (id != null) {
            Optional<Artigo> artigoOptional = artigoRepository.findById(id);
            if (artigoOptional.isPresent()) {
                return List.of(artigoOptional.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artigo não encontrado");
            }
        }

        return artigoRepository.findAll();
    }

    public void deletarArtigo(String id, String token) {
        ReturnUsuarioDTO usuario = usuarioService.validateUser(token);
        if (!usuario.getPapel().equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas administradores podem deletar artigos");
        }
        artigoRepository.deleteById(id);
    }


}
