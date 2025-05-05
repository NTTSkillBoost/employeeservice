package br.com.nttdata.nttskillboost.employeeservice.adapters.api.controller;

import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeRoleRequest;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeRoleResponse;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.mapper.EmployeeRoleMapper;
import br.com.nttdata.nttskillboost.employeeservice.application.CreateEmployeeRoleService;
import br.com.nttdata.nttskillboost.employeeservice.application.DeleteEmployeeRoleService;
import br.com.nttdata.nttskillboost.employeeservice.application.GetEmployeeRoleService;
import br.com.nttdata.nttskillboost.employeeservice.application.UpdateEmployeeRoleService;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Status;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/employee-roles")
@RequiredArgsConstructor
public class EmployeeRoleController {

    private final CreateEmployeeRoleService createEmployeeRoleService;
    private final GetEmployeeRoleService getEmployeeRoleService;
    private final UpdateEmployeeRoleService updateEmployeeRoleService;
    private final DeleteEmployeeRoleService deleteEmployeeRoleService;
    private final EmployeeRoleMapper employeeRoleMapper;

    @Retry(name = "employeeService", fallbackMethod = "fallbackCreate")
    @CircuitBreaker(name = "employeeService", fallbackMethod = "fallbackCreate")
    @Bulkhead(name = "employeeService")
    @PostMapping
    public ResponseEntity<EmployeeRoleResponse> create(@Valid @RequestBody EmployeeRoleRequest dto) {
        // 🔥 Simular falha controlada
        if ("FAIL".equalsIgnoreCase(dto.getRole())) {
            throw new RuntimeException("Falha simulada para teste de Resilience4j.");
        }

        EmployeeRole employee = employeeRoleMapper.toDomain(dto);
        EmployeeRole created = createEmployeeRoleService.create(employee);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        URI location = URI.create(String.format("/v1/employees/%s", created.getId()));
        EmployeeRoleResponse response = employeeRoleMapper.toResponse(created);
        return ResponseEntity.created(location).body(response);
    }

    // 🔎 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRoleResponse> findById(@PathVariable UUID id) {
        EmployeeRole employeeById = getEmployeeRoleService.findById(id);
        if (employeeById != null) {
            EmployeeRoleResponse byId = employeeRoleMapper.toResponse(employeeById);
            return ResponseEntity.ok(byId);
        }

        return ResponseEntity.notFound().build();
    }

    // 🔎 Listar todos
    @GetMapping
    public List<EmployeeRoleResponse> listAll() {
        List<EmployeeRole> employeeRoles = getEmployeeRoleService.findAll();
        return employeeRoles.stream()
                .map(employeeRoleMapper::toResponse)
                .toList();
    }

    // 🔄 Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRoleResponse> update(@PathVariable UUID id, @Valid @RequestBody EmployeeRoleRequest dto) {
        EmployeeRole employeeRole = employeeRoleMapper.toDomain(dto);
        EmployeeRole update = updateEmployeeRoleService.update(id, employeeRole);
        if (update != null) {
            EmployeeRoleResponse response = employeeRoleMapper.toResponse(update);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    // ❌ Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (deleteEmployeeRoleService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<EmployeeRoleResponse> fallbackCreate(EmployeeRoleRequest dto, Throwable t) {
        log.error("Fallback method called due to: {}", t.getMessage());

        EmployeeRole fallback = new EmployeeRole();
        fallback.setRole(dto.getRole());
        fallback.setStatus(Status.INACTIVE);
        EmployeeRoleResponse response = employeeRoleMapper.toResponse(fallback);
        return ResponseEntity.ok(response);
    }
}
