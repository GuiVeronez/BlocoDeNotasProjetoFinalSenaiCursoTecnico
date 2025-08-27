function mostrarLogin(){
    document.getElementById("cadastro").classList.add("hidden");
    const login = document.getElementById("login");
    login.classList.remove("hidden");
    login.classList.add("fade-in");
}
function mostrarCadastro(){
    document.getElementById("login").classList.add("hidden");
    const cad = document.getElementById("cadastro");
    cad.classList.remove("hidden");
    cad.classList.add("fade-in");
}

async function cadastrar(){
    const nome  = document.getElementById("cad_nome").value.trim();
    const email = document.getElementById("cad_email").value.trim();
    const senha = document.getElementById("cad_senha").value.trim();
    if(!nome || !email || !senha){ alert("Preencha todos os campos."); return; }

    const resp = await fetch(API_USUARIOS, {
        method: "POST",
        headers: { "Content-Type":"application/json" },
        body: JSON.stringify({ nome, email, senha })
    });

    if(resp.ok){
        alert("Conta criada! Faça login.");
        mostrarLogin();
    }else{
        alert("Erro ao cadastrar.");
    }
}

async function login(){
    const email = document.getElementById("log_email").value.trim();
    const senha = document.getElementById("log_senha").value.trim();
    if(!email || !senha){ alert("Informe e-mail e senha."); return; }

    const resp = await fetch(`${API_USUARIOS}/login`, {
        method: "POST",
        headers: { "Content-Type":"application/json" },
        body: JSON.stringify({ email, senha })
    });

    if(resp.ok){
        const usuario = await resp.json();
        salvarUsuarioLogado(usuario);
        window.location.href = "notas.html";
    }else{
        alert("Credenciais inválidas.");
    }
}

// Se já estiver logado, pula auth
document.addEventListener("DOMContentLoaded", () => {
    if(window.location.pathname.endsWith("/index.html")){
        const u = getUsuarioLogado();
        if(u) window.location.href = "notas.html";
    }
});
