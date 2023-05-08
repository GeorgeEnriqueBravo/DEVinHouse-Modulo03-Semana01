package com.exxercicio.demo.repository;

import com.exxercicio.demo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
    Veiculo findByPlaca(String placa);

    void deleteByPlaca(String placa);
}
