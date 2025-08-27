// ENDPOINTS (ajuste se necessário)
const API_USUARIOS = "http://localhost:8080/usuarios";
const API_NOTAS    = "http://localhost:8080/api/notas";

// Sessão
function salvarUsuarioLogado(usuario){ localStorage.setItem("usuario", JSON.stringify(usuario)); }
function getUsuarioLogado(){
    const raw = localStorage.getItem("usuario");
    try{ return raw ? JSON.parse(raw) : null; }catch{ return null; }
}
function logout(){
    localStorage.removeItem("usuario");
    window.location.href = "index.html";
}
function trocarConta(){ logout(); }

// Anti-XSS simples
function escapeHtml(str=""){
    return String(str).replace(/[&<>"']/g, s => ({
        "&":"&amp;","<":"&lt;",">":"&gt;","\"":"&quot;","'":"&#39;"
    }[s]));
}
function escapeAttr(str=""){ return escapeHtml(str).replace(/\n/g,"\\n"); }
