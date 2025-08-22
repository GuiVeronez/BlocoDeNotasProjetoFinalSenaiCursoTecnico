function salvarUsuarioLogado(usuario) {
    localStorage.setItem("usuario", JSON.stringify(usuario));
}

function getUsuarioLogado() {
    return JSON.parse(localStorage.getItem("usuario"));
}

function logout() {
    localStorage.removeItem("usuario");
    window.location.href = "index.html";
}