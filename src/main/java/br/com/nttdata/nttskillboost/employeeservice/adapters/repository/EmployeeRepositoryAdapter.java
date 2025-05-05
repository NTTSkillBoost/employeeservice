package br.com.nttdata.nttskillboost.employeeservice.adapters.repository;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

    private final EmployeeJpaRepository employeeRepository;

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(UUID id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new EntityNotFoundException("Funcionário com ID " + id + " não encontrado");
        }
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByIdAndPersonType(UUID id, String personType) {
        return employeeRepository.findByIdAndPersonType(id, personType)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário com ID " + id + " e tipo de pessoa " + personType + " não encontrado"));
    }
}
