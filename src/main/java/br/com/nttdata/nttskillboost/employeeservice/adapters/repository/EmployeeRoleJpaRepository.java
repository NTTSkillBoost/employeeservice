package br.com.nttdata.nttskillboost.employeeservice.adapters.repository;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface  EmployeeRoleJpaRepository extends JpaRepository<EmployeeRole, UUID> {
    EmployeeRole findByRole(String role);
}
