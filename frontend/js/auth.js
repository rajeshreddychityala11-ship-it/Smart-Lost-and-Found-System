const API=localStorage.getItem("apiBase")||"https://smart-lost-found-api.onrender.com/api";const f=document.getElementById("form");
f.addEventListener("submit",async e=>{e.preventDefault();let isReg=!!document.getElementById("name"),body=isReg?{name:name.value,email:email.value,password:password.value}:{email:email.value,password:password.value};
 let r=await fetch(API+(isReg?"/auth/register":"/auth/login"),{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(body)});let d=await r.json();msg.textContent=d.error||d.message||"";
 if(r.ok){if(!isReg){localStorage.setItem("token",d.token);localStorage.setItem("user",JSON.stringify(d.user));location.href="../index.html"}else setTimeout(()=>location.href="login.html",700)}});
