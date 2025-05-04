package br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EmployeeRoleRequest {

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String role;

    @Schema(description = "CPF do funcionário", example = "123.456.789-00")
    private UUID employeeId;
}
