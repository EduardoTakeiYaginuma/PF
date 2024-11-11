package insper.pf.repositorys;

import insper.pf.classes.Artigo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtigoRepository extends MongoRepository<Artigo, String> {


}
