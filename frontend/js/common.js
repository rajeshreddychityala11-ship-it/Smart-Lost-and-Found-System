function currentUser(){try{return JSON.parse(localStorage.getItem("user")||"null")}catch(e){return null}}
function authHeaders(){let t=localStorage.getItem("token");return t?{"Authorization":"Bearer "+t,"Content-Type":"application/json"}:{"Content-Type":"application/json"}}
function logout(){fetch(API+"/auth/logout",{method:"POST",headers:authHeaders()}).finally(()=>{localStorage.clear();location.href="/index.html"})}
function nav(){let u=currentUser(), el=document.getElementById("nav");if(!el)return;
 el.innerHTML=u?`<a href="${location.pathname.includes('/pages/')?'':'pages/'}report.html">Report</a>${u.role==="ADMIN"?`<a href="${location.pathname.includes('/pages/')?'':'pages/'}admin.html">Admin</a>`:""}<span class="muted">Hi, ${u.name}</span><button class="btn" onclick="logout()">Logout</button>`:
 `<a href="${location.pathname.includes('/pages/')?'':'pages/'}login.html">Login</a><a class="btn primary" href="${location.pathname.includes('/pages/')?'':'pages/'}register.html">Register</a>`}
nav();
