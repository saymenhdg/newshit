package smweb.chillana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smweb.chillana.model.LikesModel;

public interface LikesRepository extends JpaRepository<LikesModel,Integer> {
}
