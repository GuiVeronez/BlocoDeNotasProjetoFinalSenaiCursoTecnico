const API_NOTAS = "http://localhost:8080/api/notas";
const API_USUARIOS = "http://localhost:8080/usuarios";

async function carregarNotas() {
    const usuario = getUsuarioLogado();
    if (!usuario) return logout();

    const response = await fetch(`${API_USUARIOS}/${usuario.id}/notas`);
    const notas = await response.json();

    const lista = document.getElementById("listaNotas");
    lista.innerHTML = "";

    notas.forEach(nota => {
        const div = document.createElement("div");
        div.className = "nota";
        div.innerHTML = `
      <h3>${nota.titulo}</h3>
      <p>${nota.conteudo}</p>
      <button onclick="editarNota(${nota.id}, '${nota.titulo}', '${nota.conteudo}')">Editar</button>
      <button onclick="deletarNota(${nota.id})">Excluir</button>
    `;
        lista.appendChild(div);
    });
}

async function criarNota() {
    const usuario = getUsuarioLogado();
    const titulo = document.getElementById("tituloNota").value;
    const conteudo = document.getElementById("conteudoNota").value;

    await fetch(API_NOTAS, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ titulo, conteudo, usuarioId: usuario.id })
    });

    carregarNotas();
}

async function editarNota(id, titulo, conteudo) {
    const novoTitulo = prompt("Editar título:", titulo);
    const novoConteudo = prompt("Editar conteúdo:", conteudo);

    if (novoTitulo && novoConteudo) {
        await fetch(`${API_NOTAS}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id, titulo: novoTitulo, conteudo: novoConteudo })
        });
        carregarNotas();
    }
}

async function deletarNota(id) {
    if (confirm("Deseja excluir esta nota?")) {
        await fetch(`${API_NOTAS}/${id}`, { method: "DELETE" });
        carregarNotas();
    }
}

// Carrega as notas assim que abrir a página
document.addEventListener("DOMContentLoaded", carregarNotas);