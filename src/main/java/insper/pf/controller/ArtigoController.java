package insper.pf.controller;

import insper.pf.classes.Artigo;
import insper.pf.classes.LoginDTO;
import insper.pf.service.ArtigoService;
import insper.pf.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artigo")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return usuarioService.login(dto);
    }


    // Endpoint para criar um carro
    @PostMapping()
    public Artigo criarArtigo(@RequestBody Artigo artigo, @RequestHeader(name = "Authorization") String authorization) {
        return artigoService.criarArtigo(artigo, authorization);
    }

    @DeleteMapping("/{id}")
    public void deletarArtigo(@PathVariable String id, @RequestHeader(name = "Authorization") String authorization) {
        artigoService.deletarArtigo(id, authorization);
    }

    @GetMapping("/{id}")
    public Artigo listaArtigo(@PathVariable String id, @RequestHeader(name = "Authorization") String authorization) {
        return artigoService.listaArtigoEspecifico(id, authorization);
    }

    @GetMapping()
    public List<Artigo> listaArtigos(@RequestHeader(name = "Authorization") String authorization) {
        return artigoService.listaArtigos(authorization);
    }
}
