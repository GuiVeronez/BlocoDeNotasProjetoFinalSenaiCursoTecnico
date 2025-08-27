document.addEventListener("DOMContentLoaded", () => {
    const usuario = getUsuarioLogado();
    if(!usuario){
        window.location.href = "index.html";
        return;
    }
    carregarNotas(usuario.id);
});

async function carregarNotas(usuarioId){
    const lista = document.getElementById("listaNotas");
    lista.innerHTML = `<div class="placeholder">Carregando notas...</div>`;

    try{
        const resp = await fetch(`${API_USUARIOS}/${usuarioId}/notas`);
        if(!resp.ok){
            lista.innerHTML = `<div class="placeholder">Não foi possível carregar as notas.</div>`;
            return;
        }

        const notas = await resp.json();
        if(!Array.isArray(notas) || notas.length === 0){
            lista.innerHTML = `<div class="placeholder">Nenhuma nota encontrada.</div>`;
            return;
        }

        lista.innerHTML = "";
        notas.forEach(nota => {
            const card = document.createElement("article");
            card.className = "nota";
            card.innerHTML = `
                <h3>${escapeHtml(nota.titulo || "")}</h3>
                <p>${escapeHtml(nota.conteudo || "")}</p>
                <div class="row">
                  <button class="btn btn-primary" 
                          data-id="${nota.id}" 
                          data-titulo="${escapeAttr(nota.titulo||"")}" 
                          data-conteudo="${escapeAttr(nota.conteudo||"")}"
                          onclick="abrirEdicao(this)">Editar</button>
                  <button class="btn btn-ghost" onclick="deletarNota(${nota.id})">Excluir</button>
                </div>
            `;
            lista.appendChild(card);
        });
    }catch(e){
        console.error(e);
        lista.innerHTML = `<div class="placeholder">Erro inesperado.</div>`;
    }
}

async function criarNota(){
    const usuario = getUsuarioLogado();
    if(!usuario){ logout(); return; }

    const titulo   = document.getElementById("tituloNota").value.trim();
    const conteudo = document.getElementById("conteudoNota").value.trim();
    if(!titulo && !conteudo) return;

    await fetch(API_NOTAS, {
        method: "POST",
        headers: { "Content-Type":"application/json" },
        body: JSON.stringify({ titulo, conteudo, usuarioId: usuario.id })
    });

    document.getElementById("tituloNota").value = "";
    document.getElementById("conteudoNota").value = "";
    carregarNotas(usuario.id);
}

function abrirEdicao(btn){
    const id = btn.dataset.id;
    const tituloAtual = btn.dataset.titulo;
    const conteudoAtual = btn.dataset.conteudo;
    editarNota(id, tituloAtual, conteudoAtual);
}

async function editarNota(id, tituloAtual, conteudoAtual){
    const usuario = getUsuarioLogado();
    if(!usuario){ logout(); return; }

    const novoTitulo = prompt("Editar título:", tituloAtual);
    if(novoTitulo === null) return;

    const novoConteudo = prompt("Editar conteúdo:", conteudoAtual);
    if(novoConteudo === null) return;

    await fetch(`${API_NOTAS}/${id}`, {   // <-- id vai na URL
        method: "PUT",
        headers: { "Content-Type":"application/json" },
        body: JSON.stringify({ titulo: novoTitulo, conteudo: novoConteudo, usuarioId: usuario.id })
    });

    carregarNotas(usuario.id);
}

async function deletarNota(id){
    const usuario = getUsuarioLogado();
    if(!usuario){ logout(); return; }
    if(!confirm("Deseja excluir esta nota?")) return;

    await fetch(`${API_NOTAS}/${id}`, {  // <-- id vai na URL
        method: "DELETE"
    });

    carregarNotas(usuario.id);
}
