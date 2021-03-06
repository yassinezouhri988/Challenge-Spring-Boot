package org.sid.dao;

import org.sid.entities.DocImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface DocImageRepository extends JpaRepository<DocImage,Long>{

}
