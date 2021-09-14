const redis = require("redis");
const client = redis.createClient();

//Chave como hash
client.flushall();
for (let index = 0; index < 1000000; index++) {
  // client.set(`index-${index}`, JSON.stringify({ name: `index${index}` })); //92.68M
  client.hset(
    "session",
    `index-${index}`,
    JSON.stringify({ name: `index${index}` })
  ); //29.54M
}
