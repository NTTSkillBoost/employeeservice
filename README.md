Swagger UI: http://192.168.100.197:8881/api/swagger-ui/index.html
OpenAPI JSON: http://192.168.100.197:8881/api/v3/api-docs

# Gerar o arquivo openapi.yaml
curl http://localhost:8881/api/v3/api-docs.yaml -o openapi.yaml

✅ Retry realmente tenta 3 vezes.
✅ CircuitBreaker abre após falhas.
✅ Bulkhead limita concorrência.
✅ Fallback é acionado quando necessário.


# employeeservice
versão com application properties sem config server

==
/src
├── application (Casos de Uso)
├── domain (Entidades e regras de negócio)
├── infrastructure (Banco de dados e APIs externas)
├── adapters (Controllers, Repositories, etc.)
├── ports (Interfaces de comunicação)

Isso garante baixo acoplamento e alta testabilidade.

docker exec -it employee-service-postgres psql -U postgres
DROP DATABASE "nkbost-employee-db";
CREATE DATABASE "nkbost-employee-db";