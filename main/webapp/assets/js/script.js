"use strict";


/* =========================================================
   INICIALIZAÇÃO
   ========================================================= */

document.addEventListener("DOMContentLoaded", function () {

    setupAccountSelect();

    setupSelectAccountLinks();

    onloadData();

});


/* =========================================================
   SERVIÇOS DISPONÍVEIS POR TIPO DE CONTA
   ========================================================= */

var serviceMap = {

    "Investimentos": [
        {
            label: "Extrato",
            move: "ext"
        },
        {
            label: "Transferência",
            move: "trf"
        },
        {
            label: "Aplicar",
            move: "newInvest"
        },
        {
            label: "Resgate",
            move: "readInvest"
        }
    ],

    "Digital": [
        {
            label: "Extrato",
            move: "ext"
        },
        {
            label: "Transferência",
            move: "trf"
        },
        {
            label: "Pagamentos",
            move: "pay"
        },
        {
            label: "Cartão Crédito",
            move: "crc"
        }
    ],

    "Corrente": [
        {
            label: "Extrato",
            move: "ext"
        },
        {
            label: "Transferência",
            move: "trf"
        },
        {
            label: "Pagamentos",
            move: "pay"
        },
        {
            label: "Cartão Crédito",
            move: "crc"
        },
        {
            label: "Saque",
            move: "sqe"
        }
    ],

    "Poupança": [
        {
            label: "Extrato",
            move: "ext"
        },
        {
            label: "Transferência",
            move: "trf"
        },
        {
            label: "Pagamentos",
            move: "pay"
        },
        {
            label: "Saque",
            move: "sqe"
        }
    ]
};


/* =========================================================
   SELECT DE CONTAS
   ========================================================= */

function setupAccountSelect() {

    var select =
        document.getElementById("accountSelect");

    if (!select) {
        return;
    }

    select.addEventListener("change", function (event) {

        var option =
            event.target.options[
                event.target.selectedIndex
            ];

        if (!option || !option.value) {
            clearAccountScreen();
            return;
        }

        var id =
            option.value;

        var bank =
            option.getAttribute("data-bank") || "";

        var number =
            option.getAttribute("data-number") || "";

        var type =
            option.getAttribute("data-type") || "";

        var amount =
            option.getAttribute("data-amount") || "0";

        var fk =
            option.getAttribute("data-fk") || "";


        renderAccountCard(
            number,
            type,
            amount
        );


        renderServices(
            type,
            id,
            amount,
            bank,
            number,
            fk
        );

    });
}


/* =========================================================
   LIMPA A TELA DA CONTA
   ========================================================= */

function clearAccountScreen() {

    var details =
        document.getElementById("accountDetails");

    if (details) {
        details.innerHTML = "";
    }


    var services =
        document.getElementById("container-service");

    if (!services) {

        services =
            document.getElementById(
                "container-sevice"
            );
    }

    if (services) {
        services.innerHTML = "";
    }
}


/* =========================================================
   LINKS PARA SELEÇÃO DE CONTA
   ========================================================= */

function setupSelectAccountLinks() {

    document.addEventListener(
        "click",
        function (event) {

            var element =
                event.target.closest(
                    ".selectAccount"
                );

            if (!element) {
                return;
            }

            event.preventDefault();


            var id =
                element.getAttribute("data-id");

            var bank =
                element.getAttribute("data-bank") || "";

            var number =
                element.getAttribute("data-number") || "";

            var type =
                element.getAttribute("data-type") || "";

            var amount =
                element.getAttribute("data-amount") || "0";

            var fk =
                element.getAttribute("data-fk") || "";


            renderAccountCard(
                number,
                type,
                amount
            );


            renderServices(
                type,
                id,
                amount,
                bank,
                number,
                fk
            );

        }
    );
}


/* =========================================================
   CARTÃO DE DETALHES DA CONTA
   ========================================================= */

function renderAccountCard(
    number,
    type,
    amount
) {

    var container =
        document.getElementById(
            "accountDetails"
        );

    if (!container) {
        return;
    }


    container.innerHTML = "";


    var card =
        document.createElement("div");

    card.className =
        "account-card";


    var title =
        document.createElement("h3");

    title.className =
        "account-title";

    title.textContent =
        "Detalhes da Conta";


    card.appendChild(title);


    var info =
        document.createElement("div");

    info.className =
        "account-info";


    info.appendChild(
        createRowElement(
            "Número",
            number
        )
    );


    info.appendChild(
        createRowElement(
            "Tipo",
            type
        )
    );


    /* =====================================================
       SALDO
       ===================================================== */

    var balanceRow =
        document.createElement("div");

    balanceRow.className =
        "account-row";


    var balanceLabel =
        document.createElement("span");

    balanceLabel.className =
        "account-label";

    balanceLabel.textContent =
        "Saldo:";


    var valueSpan =
        document.createElement("span");

    valueSpan.className =
        "account-value amount-value";

    valueSpan.setAttribute(
        "data-hidden",
        "true"
    );

    valueSpan.textContent =
        "••••••";


    var toggle =
        document.createElement("button");

    toggle.type =
        "button";

    toggle.className =
        "toggle-amount";

    toggle.title =
        "Mostrar/ocultar saldo";

    toggle.setAttribute(
        "aria-label",
        "Mostrar ou ocultar saldo"
    );

    toggle.textContent =
        "👁️";


    balanceRow.appendChild(
        balanceLabel
    );

    balanceRow.appendChild(
        valueSpan
    );

    balanceRow.appendChild(
        toggle
    );


    info.appendChild(
        balanceRow
    );


    card.appendChild(
        info
    );


    container.appendChild(
        card
    );


    var formattedAmount =
        formatCurrency(amount);


    /* =====================================================
       MOSTRAR / OCULTAR SALDO
       ===================================================== */

    function toggleAmount() {

        var hidden =
            valueSpan.getAttribute(
                "data-hidden"
            );


        if (hidden === "true") {

            valueSpan.textContent =
                formattedAmount;

            valueSpan.setAttribute(
                "data-hidden",
                "false"
            );

            toggle.textContent =
                "🙈";

        } else {

            valueSpan.textContent =
                "••••••";

            valueSpan.setAttribute(
                "data-hidden",
                "true"
            );

            toggle.textContent =
                "👁️";
        }
    }


    toggle.addEventListener(
        "click",
        toggleAmount
    );
}


/* =========================================================
   CRIA LINHA DE INFORMAÇÃO
   ========================================================= */

function createRowElement(
    label,
    value
) {

    var row =
        document.createElement("div");

    row.className =
        "account-row";


    var labelElement =
        document.createElement("span");

    labelElement.className =
        "account-label";

    labelElement.textContent =
        label + ":";


    var valueElement =
        document.createElement("span");

    valueElement.className =
        "account-value";

    valueElement.textContent =
        value !== null &&
        typeof value !== "undefined"
            ? String(value)
            : "";


    row.appendChild(
        labelElement
    );

    row.appendChild(
        valueElement
    );


    return row;
}


/* =========================================================
   CRIA LINK DE SERVIÇO
   ========================================================= */

function createPostLink(
    label,
    move,
    data,
    disabled
) {

    var link =
        document.createElement("a");


    link.textContent =
        label;


    link.href =
        "#";


    if (disabled) {

        link.className =
            "link-desabilitado";

        link.setAttribute(
            "aria-disabled",
            "true"
        );

        return link;
    }


    link.className =
        "service-card";


    link.addEventListener(
        "click",
        function (event) {

            event.preventDefault();

            submitPost(
                move,
                data
            );

        }
    );


    return link;
}


/* =========================================================
   ENVIA POST PARA O CONTROLLER
   ========================================================= */

function submitPost(
    action,
    data
) {

    var form =
        document.createElement("form");


    form.method =
        "POST";


    form.action =
        getContextPath() +
        "/" +
        action;


    for (
        var key in data
    ) {

        if (
            !Object.prototype.hasOwnProperty.call(
                data,
                key
            )
        ) {
            continue;
        }


        if (
            data[key] === null ||
            typeof data[key] === "undefined"
        ) {
            continue;
        }


        var input =
            document.createElement("input");


        input.type =
            "hidden";


        input.name =
            key;


        input.value =
            String(data[key]);


        form.appendChild(
            input
        );
    }


    document.body.appendChild(
        form
    );


    form.submit();
}


/* =========================================================
   SERVIÇOS DA CONTA
   ========================================================= */

function renderServices(
    type,
    id,
    amount,
    bank,
    number,
    fk
) {

    var limite =
        1000;


    var saldo =
        parseMoney(amount);


    /* =====================================================
       LOCALIZA O CONTAINER
       ===================================================== */

    var container =
        document.getElementById(
            "container-service"
        );


    /*
     * Compatibilidade com o nome antigo:
     * container-sevice
     */

    if (!container) {

        container =
            document.getElementById(
                "container-sevice"
            );
    }


    if (!container) {
        return;
    }


    container.innerHTML =
        "";


    var services =
        serviceMap[type] || [];


    if (services.length === 0) {

        var message =
            document.createElement("p");

        message.textContent =
            "Nenhum serviço disponível para este tipo de conta.";

        container.appendChild(
            message
        );

        return;
    }


    var wrapper =
        document.createElement("div");

    wrapper.className =
        "services-container";


    for (
        var i = 0;
        i < services.length;
        i++
    ) {

        var service =
            services[i];


        var label =
            service.label;


        var move =
            service.move;


        var disabled =
            false;


        /* =================================================
           APLICAÇÃO
           ================================================= */

        if (
            move === "newInvest" &&
            saldo < limite
        ) {

            disabled =
                true;
        }


        /* =================================================
           TRANSFERÊNCIA / PAGAMENTO / SAQUE
           ================================================= */

        if (
            (
                move === "trf" ||
                move === "pay" ||
                move === "sqe"
            ) &&
            saldo <= 0
        ) {

            disabled =
                true;
        }


        /* =================================================
           DADOS PARA O CONTROLLER
           ================================================= */

        var data = {

            idAcc: id,

            bank: bank,

            number: number,

            type: type,

            fkBnk: fk

        };


        /* =================================================
           SERVIÇOS QUE RECEBEM SALDO
           ================================================= */

        if (
            move === "trf" ||
            move === "pay" ||
            move === "sqe" ||
            move === "newInvest" ||
            move === "readInvest"
        ) {

            data.amount =
                saldo;
        }


        var link =
            createPostLink(
                label,
                move,
                data,
                disabled
            );


        wrapper.appendChild(
            link
        );
    }


    container.appendChild(
        wrapper
    );
}


/* =========================================================
   COMPATIBILIDADE COM FUNÇÃO ANTIGA
   ========================================================= */

function createRow(
    label,
    value
) {

    return (

        '<div class="account-row">' +

            '<span class="account-label">' +
                escapeHtml(label) +
                ':' +
            '</span>' +

            '<span class="account-value">' +
                escapeHtml(value) +
            '</span>' +

        '</div>'
    );
}


/* =========================================================
   CONVERTE VALOR MONETÁRIO
   ========================================================= */

function parseMoney(value) {

    if (
        value === null ||
        typeof value === "undefined"
    ) {

        return 0;
    }


    var text =
        String(value).trim();


    if (!text) {
        return 0;
    }


    /*
     * Trata valores brasileiros:
     *
     * 1.000,50
     * 1000,50
     * R$ 1.000,50
     */

    text =
        text.replace(
            /R\$/g,
            ""
        );


    text =
        text.replace(
            /\s/g,
            ""
        );


    /*
     * Se possui ponto e vírgula,
     * assume formato brasileiro.
     */

    if (
        text.indexOf(".") !== -1 &&
        text.indexOf(",") !== -1
    ) {

        text =
            text.replace(
                /\./g,
                ""
            );

        text =
            text.replace(
                ",",
                "."
            );

    } else if (
        text.indexOf(",") !== -1
    ) {

        text =
            text.replace(
                ",",
                "."
            );
    }


    var number =
        parseFloat(text);


    return isNaN(number)
        ? 0
        : number;
}


/* =========================================================
   FORMATA VALOR EM REAIS
   ========================================================= */

function formatCurrency(
    value
) {

    var number =
        parseMoney(value);


    return number.toLocaleString(
        "pt-BR",
        {
            style: "currency",
            currency: "BRL"
        }
    );
}


/* =========================================================
   LOCAL STORAGE - BANCO
   ========================================================= */

function storeBankData(
    id,
    img,
    name,
    contact
) {

    var bankData = {

        id: id,

        img: img || "",

        name: name || "",

        contact: contact || ""

    };


    try {

        localStorage.setItem(
            "selectedBank",
            JSON.stringify(bankData)
        );

    } catch (error) {

        console.error(
            "Não foi possível salvar o banco:",
            error
        );
    }
}


/* =========================================================
   RECUPERA BANCO DO LOCAL STORAGE
   ========================================================= */

function getStoredBankData() {

    try {

        var bankData =
            localStorage.getItem(
                "selectedBank"
            );


        if (!bankData) {
            return null;
        }


        return JSON.parse(
            bankData
        );

    } catch (error) {

        console.error(
            "Erro ao ler selectedBank:",
            error
        );

        return null;
    }
}


/* =========================================================
   CARREGA DADOS DO BANCO
   ========================================================= */

function onloadData() {

    var bank =
        getStoredBankData();


    if (!bank) {
        return;
    }


    var bankName =
        document.getElementById(
            "bank-name"
        );


    var bankImg =
        document.getElementById(
            "bank-img"
        );


    var bankSource =
        document.getElementById(
            "bank-source"
        );


    if (bankName) {

        bankName.textContent =
            bank.name || "";
    }


    if (
        bankImg &&
        bank.img
    ) {

        /*
         * Verifica se a imagem já é
         * uma URL ou Base64.
         */

        if (
            bank.img.indexOf(
                "data:image"
            ) === 0
        ) {

            bankImg.src =
                bank.img;

        } else {

            bankImg.src =
                "data:image/png;base64," +
                bank.img;
        }


        bankImg.alt =
            bank.name || "Banco";
    }


    if (bankSource) {

        bankSource.value =
            bank.name || "";
    }
}


/* =========================================================
   CONTEXTO DA APLICAÇÃO
   ========================================================= */

function getContextPath() {

    /*
     * Preferencialmente o JSP deve definir:
     *
     * var contextPath =
     *     '${pageContext.request.contextPath}';
     */

    if (
        typeof window.contextPath !== "undefined" &&
        window.contextPath !== null
    ) {

        return window.contextPath;
    }


    /*
     * Fallback.
     */

    var pathname =
        window.location.pathname;


    var firstSlash =
        pathname.indexOf(
            "/",
            1
        );


    if (firstSlash === -1) {

        return "";
    }


    return pathname.substring(
        0,
        firstSlash
    );
}


/* =========================================================
   ESCAPA HTML
   ========================================================= */

function escapeHtml(
    value
) {

    if (
        value === null ||
        typeof value === "undefined"
    ) {

        return "";
    }


    var div =
        document.createElement(
            "div"
        );


    div.textContent =
        String(value);


    return div.innerHTML;
}
