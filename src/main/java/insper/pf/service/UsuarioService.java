package insper.pf.service;

import insper.pf.classes.LoginDTO;
import insper.pf.classes.ReturnUsuarioDTO;
import insper.pf.classes.Usuario;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

@Service
public class UsuarioService {

    public Usuario getUsuario(String cpf, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Usuario> response = restTemplate.exchange(
                "http://184.72.80.215/usuario/" + cpf,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Usuario>() {}
        );
        return response.getBody();
    }

    public String login(LoginDTO loginDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(
        "http://184.72.80.215/usuario/login", loginDTO, String.class);
        String token = response.getBody();

        //RestTemplate restTemplate_ = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ReturnUsuarioDTO> response_ = restTemplate.exchange(
                "http://184.72.80.215/usuario/validate",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ReturnUsuarioDTO>() {}
        );
        ReturnUsuarioDTO usuarioDTO = response_.getBody();
        if (usuarioDTO == null) {
            throw new IllegalArgumentException("Token inválido");
        }
        return token;
    }
}
