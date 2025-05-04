package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.GetEmployeeUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetEmployeeService implements GetEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public Employee findById(UUID id) {
        return employeeRepositoryPort.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepositoryPort.findAll();
    }
}
