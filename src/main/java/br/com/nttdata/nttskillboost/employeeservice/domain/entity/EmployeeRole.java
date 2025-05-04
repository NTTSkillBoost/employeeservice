package br.com.nttdata.nttskillboost.employeeservice.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_employee_role")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "EmployeeRole Entity", description = "Operações relacionadas a Função do funcionário")
public class EmployeeRole extends AuditDomain {

    @Schema(description = "ID do EmployeeRole", example = "123e4567-e89b-12d3-a456-426614174000")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Schema(description = "Employee", example = "Employee")
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Schema(description = "Role", example = "Role")
    private String role;

    //@Version
    //private Long version;
}
