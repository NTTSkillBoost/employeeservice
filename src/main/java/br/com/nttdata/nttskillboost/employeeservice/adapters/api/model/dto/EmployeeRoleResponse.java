package br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EmployeeRoleResponse {

    @Schema(description = "ID do funcionário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String role;

    @Schema(description = "CPF do funcionário", example = "123.456.789-00")
    private UUID employeeId;
}
