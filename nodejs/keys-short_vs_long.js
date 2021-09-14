const redis = require("redis");
const client = redis.createClient();

// Chave curta vs longa
client.flushall();
for (let index = 0; index < 1000000; index++) {
  // client.set(`curta-${index}`, `index-${index}`); //77.50M 65.70M
  // client.set(`uma-chave-maior-${index}`, `index-${index}`); //85.13M
  // client.set(`uma-chave-muito-maior-que-a-anterior-${index}`, `index-${index}`); //108.02M 96.22M
}
