const API_URL = "http://localhost:8080/usuarios";

async function login() {
    const email = document.getElementById("loginEmail").value;
    const senha = document.getElementById("loginSenha").value;

    const response = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, senha })
    });

    if (response.ok) {
        const usuario = await response.json();
        salvarUsuarioLogado(usuario);
        window.location.href = "notas.html";
    } else {
        alert("Login inválido");
    }
}

async function cadastrar() {
    const nome = document.getElementById("cadastroNome").value;
    const email = document.getElementById("cadastroEmail").value;
    const senha = document.getElementById("cadastroSenha").value;

    const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, email, senha })
    });

    if (response.ok) {
        alert("Usuário cadastrado com sucesso!");
    } else {
        alert("Erro ao cadastrar usuário");
    }







}

function mostrarLogin() {
    document.getElementById("cadastroForm").classList.add("hidden");
    document.getElementById("loginForm").classList.remove("hidden");
}

function mostrarCadastro() {
    document.getElementById("loginForm").classList.add("hidden");
    document.getElementById("cadastroForm").classList.remove("hidden");
}