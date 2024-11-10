package insper.pf.controller;

import insper.pf.classes.Carro;
import insper.pf.classes.LoginDTO;
import insper.pf.classes.ReturnUsuarioDTO;
import insper.pf.service.CarroService;
import insper.pf.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return usuarioService.login(dto);
    }


    // Endpoint para criar um carro
    @PostMapping("/criar")
    public Carro criarCarro(@RequestBody Carro carro) {
        return carroService.criarCarro(carro);
    }

    // Endpoint para associar um carro a um dono usando o ID do carro e o CPF do dono
    @PostMapping("/associar/{idCarro}/{cpf}")
    public Carro associarCarroDono(@PathVariable String idCarro, @PathVariable String cpf) {
        return carroService.associarCarroDono(idCarro, cpf);
    }

    // Endpoint para listar todos os carros de um dono específico pelo ID do dono
    @GetMapping("/listar")
    public List<Carro> listaCarros(@RequestParam(required = false) String id) {
        return carroService.listaCarros(id);
    }
}
