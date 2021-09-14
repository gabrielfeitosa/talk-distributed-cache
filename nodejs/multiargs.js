const redis = require("redis");
const client = redis.createClient();

client.flushall();

redis.debug_mode = true;

// Adicionando item a item
for (let index = 0; index < 2; index++) {
  client.set(`key-${index}`, `value-${index}`);
}

// Executando multiargs
function fillArray() {
  let array = [];
  for (let index = 0; index < 2; index++) {
    array.push(`key-${index}`);
    array.push(`value-${index}`);
  }
  return array;
}

client.mset(fillArray());
