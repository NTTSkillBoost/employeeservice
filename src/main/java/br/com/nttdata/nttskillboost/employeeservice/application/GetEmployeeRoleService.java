package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.GetEmployeeRoleUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetEmployeeRoleService implements GetEmployeeRoleUseCase {

    private final EmployeeRoleRepositoryPort employeeRoleRepositoryPort;

    @Override
    public EmployeeRole findById(UUID id) {
        return employeeRoleRepositoryPort.findById(id);
    }

    @Override
    public List<EmployeeRole> findAll() {
        return employeeRoleRepositoryPort.findAll();
    }
}
