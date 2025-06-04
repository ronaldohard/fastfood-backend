# ✅ Checklist Fase 2 - Agrupado por Categoria

| Requisito                                       | Categoria       | Status Atual    | Refatorar             | Observações                                             |
|:------------------------------------------------|:----------------|:----------------|:----------------------|:--------------------------------------------------------|
| Checkout Pedido (criar API)                     | APIs & Queries  | Feito (fase 1)  | Sim - Clean Code/Arch | Ajustar para retornar apenas o ID e seguir Clean Arch   |
| Consultar status do pagamento                   | APIs & Queries  | Feito (Mock)    | Sim                   | Validar retorno com status real ou mock                 |
| Listar pedidos com ordenação e filtro           | APIs & Queries  | Parcial         | Sim                   | Adicionar regra de ordenação e ocultar finalizados      |
| Atualizar status do pedido (preparação/cozinha) | APIs & Queries  | Feito           | Sim                   | Revisar fluxo completo para cozinha                     |
| Webhook para confirmação de pagamento           | Webhook         | Feito (Mock)    | Sim                   | Deixar explícito o fluxo do webhook                     |
| Refatorar código para Clean Code                | Clean Code      | Parcial         | Sim                   | Revisar nomes, extração de responsabilidades e serviços |
| Criar manifests YAML (Deployment, Service)      | Kubernetes      | Pendente        | -                     | Estruturar app e DB com deploys separados               |
| Aplicar HPA (autoscaling)                       | Kubernetes      | Pendente        | -                     | Exemplo com métricas de CPU para escalar pods           |
| Usar ConfigMap e Secrets                        | Kubernetes      | Pendente        | -                     | Evitar hardcoded de senhas/URLs nos YAMLs               |
| Expor aplicação corretamente                    | Kubernetes      | Pendente        | -                     | Service tipo NodePort/LoadBalancer                      |
| Documentar APIs no Swagger                      | Swagger/Postman | Feito (parcial) | Sim                   | Revisar resumo, tags e exemplos                         |
| Collection Postman com exemplos                 | Swagger/Postman | Feito           | Não                   | Arquivo JSON incluído                                   |
| Desenhar arquitetura da solução (K8s)           | Desenhos        | Pendente        | -                     | Incluir pods, HPA, DB, app, ingress etc                 |
| Guia de execução das APIs                       | Desenhos        | Parcial         | Sim                   | README precisa incluir a ordem e detalhes da execução   |
| Vídeo demonstrando deploy e APIs                | Desenhos        | Pendente        | -                     | Mostrar execução local ou em nuvem                      |