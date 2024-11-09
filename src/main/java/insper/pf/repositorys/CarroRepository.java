package insper.pf.repositorys;

import insper.pf.classes.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends MongoRepository<Carro, String> {

    List<Carro> findByDono(String dono);

}
