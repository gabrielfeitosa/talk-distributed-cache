# talk-distributed-cache

## TTL
---
TTL de 60 segundos em dois comandos
```sh
SET chave valor 
EXPIRE chave 60
```

TTL de 60 segundos em um único comando
```sh
SET chave valor ex 60
```

TTL em uma data e horário específico (Simular um timestamp https://www.unixtimestamp.com/)
```sh
SET chave valor
EXPIREAT chave `timestamp`
SET chave valor exat `timestamp` #Versão 6.2+
```