package smweb.chillana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smweb.chillana.model.CommentsModel;

public interface CommentsRepository extends JpaRepository<CommentsModel,Integer>{

}