const redis = require("redis");
const client = redis.createClient();

function getTimestampEndOfDay() {
  let expireAt = new Date();
  expireAt.setHours(23, 59, 59);
  return Math.round(expireAt.getTime() / 1000);
}

// Expira em 60 segundos
client.set("chave_ex", "valor_ex", "EX", 60, redis.print);

// //Expira no final do dia
client.set("chave_exat", "valor_exat", redis.print);
client.expireat("chave_exat", getTimestampEndOfDay(), redis.print);

// Expirar com data específica no comando set - Versão 6.2+
client.set(
  "chave_exat2",
  "valor_exat2",
  "EXAT",
  getTimestampEndOfDay(),
  redis.print
);
