const token = localStorage.getItem("token");
const user = (() => { try { return JSON.parse(localStorage.getItem("user") || "null"); } catch { return null; } })();
const nav = document.getElementById("nav");
if (nav) {
  const root = location.pathname.includes("/pages/") ? "../" : "";
  const links = [`<a href="${root}index.html">Home</a>`];
  if (token && user) {
    links.push(`<a href="${root}pages/report.html">Report</a>`);
    if (user.role === "ADMIN") links.push(`<a href="${root}pages/admin.html">Admin</a>`);
    links.push(`<button class="link-btn" onclick="logout()">Logout</button>`);
  } else {
    links.push(`<a href="${root}pages/login.html">Login</a>`, `<a href="${root}pages/register.html">Register</a>`);
  }
  nav.innerHTML = links.join("");
}
async function logout(){
  try { if (token) await fetch(API + "/auth/logout", {method:"POST", headers:{Authorization:"Bearer " + token}}); } catch {}
  localStorage.removeItem("token"); localStorage.removeItem("user"); location.href = (location.pathname.includes("/pages/") ? "../" : "") + "index.html";
}
