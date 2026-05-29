/**
 * 
 */

document.addEventListener("DOMContentLoaded", init);

/* ===============================
   FUNÇÃO INICIAL
   =============================== */
function init() {
    setupAccountSelect();
    setupSelectAccountLinks();
    onloadData();
}

/* ===============================
   MAPA DE SERVIÇOS
   =============================== */
const serviceMap = {
    Investimentos: [
        { label: "Extrato", move: "ext" },
        { label: "Transferência", move: "trf" },
        { label: "Aplicar", move: "newInvest" },
        { label: "Resgate", move: "readInvest" }
    ],
    Digital: [
        { label: "Extrato", move: "ext" },
        { label: "Transferência", move: "trf" },
        { label: "Pagamentos", move: "pay" },
        { label: "Cartão Crédito", move: "crc" }
    ],
    Corrente: [
        { label: "Extrato", move: "ext" },
        { label: "Transferência", move: "trf" },
        { label: "Pagamentos", move: "pay" },
        { label: "Cartão Crédito", move: "crc" }
    ],
    Poupança: [
        { label: "Extrato", move: "ext" },
        { label: "Transferência", move: "trf" },
        { label: "Pagamentos", move: "pay" }
    ]
};

/* ===============================
   CONFIGURAÇÃO DO SELECT DE CONTAS
   =============================== */
function setupAccountSelect() {
    const select = document.getElementById("accountSelect");
    if (!select) return;

    select.addEventListener("change", (e) => {
        const option = e.target.selectedOptions[0];
        if (!option.value) return;

        const { number, type, desc, amount } = option.dataset;
        renderAccountCard({ number, type, desc, amount });
        renderServices(type, option.value, amount);
    });
}

/* ===============================
   CONFIGURAÇÃO DE LINKS DE CONTA
   =============================== */
function setupSelectAccountLinks() {
    document.addEventListener("click", (e) => {
        const link = e.target.closest(".selectAccount");
        if (!link) return;

        e.preventDefault();

        const { id, number, type, desc, amount } = link.dataset;
        renderAccountCard({ number, type, desc, amount });
        renderServices(type, id, amount);
    });
}

/* ===============================
   RENDERIZAÇÃO DO CARTÃO DE CONTA
   =============================== */
function renderAccountCard({ number, type, desc, amount }) {
    const container = document.getElementById("accountDetails");
    if (!container) return;

    container.innerHTML = "";

    const card = document.createElement("div");
    card.className = "account-card";

    const formattedAmount = formatCurrency(amount);

    card.innerHTML = `
        <h3 class="account-title">Detalhes da Conta</h3>
        <div class="account-info">
            ${createRow("Número", number)}
            ${createRow("Tipo", type)}
            <div class="account-row">
                <span class="account-label">Saldo:</span>
                <span class="account-value amount-value" data-hidden="true">••••••</span>
                <span class="toggle-amount" title="Mostrar/ocultar">👁️</span>
            </div>
        </div>
    `;

    container.appendChild(card);

    // Funcionalidade de mostrar/ocultar saldo
    const toggle = card.querySelector(".toggle-amount");
    const valueSpan = card.querySelector(".amount-value");

    toggle.addEventListener("click", () => {
        if (valueSpan.dataset.hidden === "true") {
            valueSpan.textContent = formattedAmount;
            valueSpan.dataset.hidden = "false";
        } else {
            valueSpan.textContent = "••••••";
            valueSpan.dataset.hidden = "true";
        }
    });
}

/* ===============================
   RENDERIZAÇÃO DE LINKS DE SERVIÇOS (POST)
   =============================== */
function createPostLink(label, move, data, disabled = false) {
    const a = document.createElement("a");
    a.textContent = label;
    a.href = "#";
    a.className = disabled ? "link-desabilitado" : "service-card";

    if (disabled) return a;

    a.addEventListener("click", function (e) {
        e.preventDefault();

        const form = document.createElement("form");
        form.method = "POST";
        form.action = move;

        Object.entries(data).forEach(([key, value]) => {
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = key;
            input.value = value;
            form.appendChild(input);
        });

        document.body.appendChild(form);
        form.submit();
    });

    return a;
}

function renderServices(type, id, amount) {
    const limite = 1000;
    const saldo = Number(amount);

    const container = document.getElementById("container-sevice");
    if (!container) return;

    container.innerHTML = "";

    const services = serviceMap[type] || [];
    if (!services.length) return;

    const wrapper = document.createElement("div");
    wrapper.className = "services-container";

    services.forEach(({ label, move }) => {
        const disabled = move === "newInvest" && saldo < limite;
        const data = { idAcc: id };

        if (["trf", "pay", "newInvest"].includes(move)) {
            data.amount = saldo;
        }

        const link = createPostLink(label, move, data, disabled);
        wrapper.appendChild(link);
    });

    container.appendChild(wrapper);
}

/* ===============================
   FUNÇÕES AUXILIARES
   =============================== */
function createRow(label, value) {
    return `
        <div class="account-row">
            <span class="account-label">${label}:</span>
            <span class="account-value">${value}</span>
        </div>
    `;
}

function formatCurrency(value) {
    return Number(value).toLocaleString("pt-BR", {
        style: "currency",
        currency: "BRL"
    });
}

/* ===============================
   ARMAZENAMENTO DE DADOS DO BANCO
   =============================== */
function storeBankData({ id, img, name, contact = null }) {
    const bankData = { id, img, name };
    if (contact) bankData.contact = contact;

    localStorage.setItem("selectedBank", JSON.stringify(bankData));
}

function getStoredBankData() {
    const bankData = localStorage.getItem("selectedBank");
    return bankData ? JSON.parse(bankData) : null;
}

/* ===============================
   CARREGAR DADOS SALVOS NA PÁGINA
   =============================== */
function onloadData() {
    const bank = getStoredBankData();
    if (!bank) return;

    const nameEl = document.getElementById("bank-name");
    const imgEl = document.getElementById("bank-img");

    if (nameEl) nameEl.innerText = bank.name;
    if (imgEl) imgEl.src = "data:image/png;base64," + bank.img;
}



