package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.CreateEmployeeUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeService implements CreateEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public Employee create(Employee employee) {
        if (employeeRepositoryPort.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe funcionário com este email.");
        }
        return employeeRepositoryPort.save(employee);
    }
}
