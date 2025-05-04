package br.com.nttdata.nttskillboost.employeeservice.adapters.repository;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRoleRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeRoleRepositoryAdapter implements EmployeeRoleRepositoryPort {

    private final EmployeeRoleJpaRepository employeeRoleRepository;

    @Override
    public EmployeeRole save(EmployeeRole employeeRole) {
        return employeeRoleRepository.save(employeeRole);
    }

    @Override
    public EmployeeRole findById(UUID id) {
        Optional<EmployeeRole> byId = employeeRoleRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new EntityNotFoundException("Funcionário com ID " + id + " não encontrado");
        }
    }

    @Override
    public List<EmployeeRole> findAll() {
        return employeeRoleRepository.findAll();
    }

    @Override
    public EmployeeRole findByRoleName(String role) {
        return employeeRoleRepository.findByRole(role);
    }
}
