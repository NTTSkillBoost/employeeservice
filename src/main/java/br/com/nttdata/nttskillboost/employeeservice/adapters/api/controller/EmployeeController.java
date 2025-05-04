package br.com.nttdata.nttskillboost.employeeservice.adapters.api.controller;

import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeRequest;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeResponse;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.mapper.EmployeeMapper;
import br.com.nttdata.nttskillboost.employeeservice.application.CreateEmployeeService;
import br.com.nttdata.nttskillboost.employeeservice.application.DeleteEmployeeService;
import br.com.nttdata.nttskillboost.employeeservice.application.GetEmployeeService;
import br.com.nttdata.nttskillboost.employeeservice.application.UpdateEmployeeService;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Status;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final CreateEmployeeService createEmployeeService;
    private final GetEmployeeService getEmployeeService;
    private final UpdateEmployeeService updateEmployeeService;
    private final DeleteEmployeeService deleteEmployeeService;
    private final EmployeeMapper employeeMapper;

    @Retry(name = "employeeService", fallbackMethod = "fallbackCreate")
    @CircuitBreaker(name = "employeeService", fallbackMethod = "fallbackCreate")
    @Bulkhead(name = "employeeService")
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest dto) {
        // 🔥 Simular falha controlada
        if ("FAIL".equalsIgnoreCase(dto.getName())) {
            throw new RuntimeException("Falha simulada para teste de Resilience4j.");
        }

        Employee employee = employeeMapper.toDomain(dto);
        Employee created = createEmployeeService.create(employee);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        URI location = URI.create(String.format("/v1/employees/%s", created.getId()));
        EmployeeResponse response = employeeMapper.toResponse(created);
        return ResponseEntity.created(location).body(response);
    }

    // 🔎 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable UUID id) {
        Employee employeeById = getEmployeeService.findById(id);
        if (employeeById != null) {
            EmployeeResponse byId = employeeMapper.toResponse(employeeById);
            return ResponseEntity.ok(byId);
        }

        return ResponseEntity.notFound().build();
    }

    // 🔎 Listar todos
    @GetMapping
    public List<EmployeeResponse> listAll() {
        List<Employee> employees = getEmployeeService.findAll();
        return employees.stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    // 🔄 Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable UUID id, @RequestBody EmployeeRequest dto) {
        Employee employee = employeeMapper.toDomain(dto);
        Employee update = updateEmployeeService.update(id, employee);
        if (update != null) {
            EmployeeResponse response = employeeMapper.toResponse(update);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    // ❌ Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (deleteEmployeeService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<EmployeeResponse> fallbackCreate(EmployeeRequest dto, Throwable t) {
        log.error("Fallback method called due to: {}", t.getMessage());

        Employee fallback = new Employee();
        fallback.setName("Fallback " + dto.getName());
        fallback.setEmail("fallback@example.com");
        fallback.setStatus(Status.INACTIVE);
        EmployeeResponse response = employeeMapper.toResponse(fallback);
        return ResponseEntity.ok(response);
    }
}
