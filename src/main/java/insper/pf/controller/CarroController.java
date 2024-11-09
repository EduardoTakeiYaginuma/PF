package insper.pf.controller;

import insper.pf.classes.Carro;
import insper.pf.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

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
