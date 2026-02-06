package com.example.core.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepo extends JpaRepository<Workcolumn, Integer> {
    // Usando SQL Nativo (el de toda la vida de Postgres)
    @Query(value = "SELECT * FROM workcolumn", nativeQuery = true)
    List<Workitem> listarTodas();
}
