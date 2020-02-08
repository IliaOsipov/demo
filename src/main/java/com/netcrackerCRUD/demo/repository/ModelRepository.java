package com.netcrackerCRUD.demo.repository;

import com.netcrackerCRUD.demo.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}