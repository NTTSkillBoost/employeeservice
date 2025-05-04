package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.UpdateEmployeeUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeService implements UpdateEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public Employee update(UUID id, Employee employee) {
        Employee byId = employeeRepositoryPort.findById(id);
        if (byId == null) {
            return null;
        }

        Employee employeeUpdate = new Employee();
        employeeUpdate.setId(byId.getId());
        employeeUpdate.setName(employee.getName());
        employeeUpdate.setEmail(employee.getEmail());
        employeeUpdate.setPhone(employee.getPhone());
        employeeUpdate.setStatus(employee.getStatus());

        return employeeRepositoryPort.save(employeeUpdate);
    }
}
