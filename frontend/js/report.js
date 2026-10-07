const API=localStorage.getItem("apiBase")||"http://localhost:8080/api";if(!localStorage.getItem("token"))location.href="login.html";
document.getElementById("date").value=new Date().toISOString().slice(0,10);
reportForm.addEventListener("submit",async e=>{e.preventDefault();let body={name:name.value,description:description.value,location:location.value,date:date.value,type:type.value,category:category.value,contact:contact.value,imageUrl:imageUrl.value};
 let r=await fetch(API+"/items",{method:"POST",headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify(body)});let d=await r.json();msg.textContent=r.ok?"Report submitted successfully!":d.error||"Unable to submit";if(r.ok)setTimeout(()=>location.href="../index.html",700)});
