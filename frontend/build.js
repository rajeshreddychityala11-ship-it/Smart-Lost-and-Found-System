const fs = require("fs");
const base = process.env.API_BASE_URL || "http://localhost:8080/api";
const api = base.replace(/\/$/, "");
fs.writeFileSync("js/config.js", `const API = ${JSON.stringify(api)};\n`);
console.log(`Configured API: ${api}`);
