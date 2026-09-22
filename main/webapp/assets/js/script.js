"use strict";

/* =========================================================
   INICIALIZAÇÃO
   ========================================================= */

document.addEventListener("DOMContentLoaded", function () {
    setupBankSelection();
    setupAccountSelect();
    setupSelectAccountLinks();
    setupRescueModal();
    onloadData();
    loadBankInTransferPage();
    loadBankLogos();
});




function loadBankLogos() {

    var images = document.querySelectorAll(
        ".bank-logo[data-bank-image]"
    );

    if (!images || images.length === 0) {
        return;
    }

    images.forEach(function (imgElement) {

        var imgData = imgElement.getAttribute(
            "data-bank-image"
        ) || "";

        var bankName = imgElement.getAttribute(
            "data-bank-name"
        ) || "Banco";

        if (!imgData.trim()) {
            return;
        }

        setBankImage(
            imgElement,
            imgData,
            bankName
        );
    });

}



/* =========================================================
   SELEÇÃO DO BANCO NO MAIN.JSP
   ========================================================= */

function setupBankSelection() {

    var banks = document.querySelectorAll(".bank-select");

    if (!banks || banks.length === 0) {
        return;
    }


    banks.forEach(function (bankElement) {

        bankElement.addEventListener(
            "click",
            function (event) {

                /*
                 * Impede a navegação momentaneamente.
                 *
                 * Primeiro salvamos o banco.
                 * Depois fazemos a navegação.
                 */

                event.preventDefault();


                var id = bankElement.getAttribute(
                    "data-id"
                ) || "";


                var img = bankElement.getAttribute(
                    "data-img"
                ) || "";


                var name =
                    bankElement.getAttribute(
                        "data-name"
                    ) || "";


                var contact =
                    bankElement.getAttribute(
                        "data-contact"
                    ) || "";


                /*
                 * Salva banco no localStorage.
                 */

                storeBankData(
                    id,
                    img,
                    name,
                    contact
                );


                /*
                 * Recupera o endereço do link.
                 */

                var url = bankElement.getAttribute("href");


                /*
                 * Só navega depois de salvar.
                 */

                if (url) {

                    window.location.href =
                        url;

                }

            }
        );

    });

}






/* =========================================================
   BANCO NA PÁGINA DE TRANSFERÊNCIA
   ========================================================= */

function loadBankInTransferPage() {

    var bank =
        getStoredBankData();

    if (!bank) {

        console.warn(
            "Nenhum banco selecionado no localStorage."
        );

        return;
    }


    var name =
        document.getElementById(
            "transfer-bank-name"
        );

    var img =
        document.getElementById(
            "transfer-bank-img"
        );


    if (name) {

        name.textContent =
            bank.name || "Banco";

    }


    if (
        img &&
        bank.img
    ) {

        setBankImage(
            img,
            bank.img,
            bank.name
        );

    }

}


/* =========================================================
   CARREGA BANCO SELECIONADO
   ========================================================= */

function loadSelectedBank() {

    var bank =
        getStoredBankData();

    if (!bank) {
        return null;
    }


    var bankName =
        document.getElementById(
            "bank-name"
        );

    var bankImg =
        document.getElementById(
            "bank-img"
        );


    if (bankName) {

        bankName.textContent =
            bank.name || "Banco";

    }


    if (
        bankImg &&
        bank.img
    ) {

        setBankImage(
            bankImg,
            bank.img,
            bank.name
        );

    }


    return bank;
}


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
        document.getElementById(
            "accountSelect"
        );

    if (!select) {
        return;
    }


    select.addEventListener(
        "change",
        function (event) {

            var option =
                event.target.options[
                event.target.selectedIndex
                ];


            if (
                !option ||
                !option.value
            ) {

                clearAccountScreen();

                return;
            }


            var id =
                option.value;


            var number =
                option.getAttribute(
                    "data-number"
                ) || "";


            var type =
                option.getAttribute(
                    "data-type"
                ) || "";


            var amount =
                option.getAttribute(
                    "data-amount"
                ) || "0";


            var fk =
                option.getAttribute(
                    "data-fk"
                ) || "";


            /*
             * IMPORTANTE:
             *
             * O banco NÃO vem mais
             * da option.
             *
             * Ele vem do localStorage.
             */

            var selectedBank =
                getStoredBankData();


            if (!selectedBank) {

                console.warn(
                    "Nenhum banco encontrado no localStorage."
                );

                return;
            }


            var bank =
                selectedBank.name || "";


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
   LIMPA TELA DA CONTA
   ========================================================= */

function clearAccountScreen() {

    var details =
        document.getElementById(
            "accountDetails"
        );


    if (details) {

        details.innerHTML =
            "";

    }


    var services =
        document.getElementById(
            "container-service"
        );


    if (!services) {

        services =
            document.getElementById(
                "container-sevice"
            );

    }


    if (services) {

        services.innerHTML =
            "";

    }

}


/* =========================================================
   LINKS DE SELEÇÃO DE CONTA
   ========================================================= */

function setupSelectAccountLinks() {

    document.addEventListener(
        "click",
        function (event) {

            if (
                !event.target.closest
            ) {

                return;

            }


            var element =
                event.target.closest(
                    ".selectAccount"
                );


            if (!element) {
                return;
            }


            event.preventDefault();


            var id =
                element.getAttribute(
                    "data-id"
                ) || "";


            var number =
                element.getAttribute(
                    "data-number"
                ) || "";


            var type =
                element.getAttribute(
                    "data-type"
                ) || "";


            var amount =
                element.getAttribute(
                    "data-amount"
                ) || "0";


            var fk =
                element.getAttribute(
                    "data-fk"
                ) || "";


            /*
             * Banco vem do localStorage.
             */

            var selectedBank =
                getStoredBankData();


            if (!selectedBank) {

                console.warn(
                    "Nenhum banco encontrado no localStorage."
                );

                return;
            }


            var bank =
                selectedBank.name || "";


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


    container.innerHTML =
        "";


    var card =
        document.createElement(
            "div"
        );


    card.className =
        "account-card";


    var title =
        document.createElement(
            "h3"
        );


    title.className =
        "account-title";


    title.textContent =
        "Detalhes da Conta";


    card.appendChild(
        title
    );


    var info =
        document.createElement(
            "div"
        );


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
        document.createElement(
            "div"
        );


    balanceRow.className =
        "account-row";


    var balanceLabel =
        document.createElement(
            "span"
        );


    balanceLabel.className =
        "account-label";


    balanceLabel.textContent =
        "Saldo:";


    var valueSpan =
        document.createElement(
            "span"
        );


    valueSpan.className =
        "account-value amount-value";


    valueSpan.setAttribute(
        "data-hidden",
        "true"
    );


    valueSpan.textContent =
        "••••••";


    var toggle =
        document.createElement(
            "button"
        );


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
        formatCurrency(
            amount
        );


    /* =====================================================
       MOSTRAR / OCULTAR SALDO
       ===================================================== */

    toggle.addEventListener(
        "click",
        function () {

            var hidden =
                valueSpan.getAttribute(
                    "data-hidden"
                );


            if (
                hidden === "true"
            ) {

                valueSpan.textContent =
                    formattedAmount;


                valueSpan.setAttribute(
                    "data-hidden",
                    "false"
                );


                toggle.textContent =
                    "◉̸";

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
    );

}


/* =========================================================
   CRIA LINHA
   ========================================================= */

function createRowElement(label,value) {

    var row = document.createElement("div");
    row.className = "account-row";

    var labelElement = document.createElement("span");
    labelElement.className = "account-label";
    labelElement.textContent = label + ":";
    var valueElement = document.createElement("span");
    valueElement.className ="account-value";
    valueElement.textContent = value !== null && typeof value !== "undefined"
            ? String(value)
            : "";

    row.appendChild(labelElement);
    row.appendChild(valueElement);
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

    var link = document.createElement("a");
    link.textContent = label;
    link.href = "#";
    if (disabled) {
        link.className = "link-desabilitado";
        link.setAttribute( "aria-disabled", "true");
        return link;
    }

    link.className = "service-card";
    link.addEventListener(
        "click",
        function (event) {
            event.preventDefault();
            submitPost( move, data);
        }
    );
    return link;
}


/* =========================================================
   ENVIA POST PARA CONTROLLER
   ========================================================= */

function submitPost(action, data) {

    var contextPath = getContextPath();
    var form = document.createElement("form");
    form.method = "POST";
    form.action = contextPath +"/" + action;

    for (var key in data ) {

        if (!Object.prototype.hasOwnProperty.call(data,key)) {
            continue;
        }

        if (data[key] === null || typeof data[key] === "undefined") {
            continue;
        }

        var input = document.createElement("input");
        input.type = "hidden";
        input.name = key;
        input.value = String(data[key]);
        form.appendChild(input);
    }

    document.body.appendChild(form);
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

    var limite = 1000;

    var saldo = parseMoney(amount);

    var container = document.getElementById("container-service");
    if (!container) {
        container = document.getElementById("container-sevice");
    }

    if (!container) {
        return;
    }

    container.innerHTML = "";

    var services = serviceMap[type] || [];

    if (services.length === 0) {
        var message = document.createElement("p");
        message.textContent = "Nenhum serviço disponível para este tipo de conta.";
        container.appendChild(message);
        return;
    }

    var wrapper = document.createElement("div");
    wrapper.className = "services-container";

    for (var i = 0; i < services.length; i++ ) {
        var service = services[i];
        var label = service.label;
        var move = service.move;
        var disabled =false;

        /* =================================================
           APLICAÇÃO
           ================================================= */

        if ( move === "newInvest" && saldo < limite) {
            disabled = true;
        }


        /* =================================================
           TRANSFERÊNCIA / PAGAMENTO / SAQUE
           ================================================= */

        if ((   move === "trf" ||
                move === "pay" ||
                move === "sqe" ) && saldo <= 0) {
            disabled = true;
        }


        /* =================================================
           DADOS
           ================================================= */

        var data = {
            idAcc: id,
            bank: bank,
            number: number,
            type: type,
            fkBnk: fk
        };


        if (
            move === "trf" ||
            move === "pay" ||
            move === "sqe" ||
            move === "newInvest" ||
            move === "readInvest" ) {
            data.amount = saldo;
        }


        var link = createPostLink(
                label,
                move,
                data,
                disabled
            );


        wrapper.appendChild(link);
    }
       container.appendChild(wrapper);
}


/* =========================================================
   CONVERTE VALOR MONETÁRIO
   ========================================================= */

function parseMoney(value) {

    if (value === null || typeof value === "undefined" ) {
        return 0;
    }

    var text = String(value).trim();
    if (!text) {
        return 0;
    }

    text = text.replace(/R\$/gi,"");
    text = text.replace(/\s/g,"");

    /*
     * 1.000,50
     */

    if (text.indexOf(".") !== -1 &&
        text.indexOf(",") !== -1 ) {

        text = text.replace(/\./g, "");
        text = text.replace(",",".");
    }

    /*
     * 1000,50
     */

    else if (text.indexOf(",") !== -1) {
        text = text.replace(",",".");
    }

    var number = parseFloat(text);

    return isNaN(number)
        ? 0
        : number;

}


/* =========================================================
   FORMATA MOEDA
   ========================================================= */

function formatCurrency(value){

    var number = parseMoney(value);

    return number.toLocaleString(
        "pt-BR",
        {
            style: "currency",
            currency: "BRL"
        }
    );
}


/* =========================================================
   LOCAL STORAGE - SALVA BANCO
   ========================================================= */

function storeBankData(
    id,
    img,
    name,
    contact
) {

    var bankData = {
        id: id || "",
        img: img || "",
        name: name || "",
        contact: contact || ""
    };


    try {
        localStorage.setItem("selectedBank",JSON.stringify(bankData));
        console.log("Banco salvo no localStorage:",bankData);
    } catch (error) {
        console.error("Erro ao salvar banco:",error);
    }

}


/* =========================================================
   LOCAL STORAGE - RECUPERA BANCO
   ========================================================= */

function getStoredBankData() {

    try {
        var bankData = localStorage.getItem("selectedBank");

        if (!bankData) {
            return null;
        }

        var bank = JSON.parse(bankData);

        if (!bank ||typeof bank !== "object") {
            return null;
        }

        return bank;

    } catch (error) {

        console.error("Erro ao ler selectedBank:",error);
        return null;
    }

}


/* =========================================================
   CONFIGURA IMAGEM DO BANCO
   ========================================================= */

function setBankImage(
    imgElement,
    imgData,
    bankName
) {

    if (!imgElement) {
        return false;
    }


    if (imgData === null || typeof imgData === "undefined") {
        imgElement.style.display = "none";
        return false;
    }


    var src = String(imgData).trim();

    if (!src) {
        imgElement.style.display = "none";
        return false;
    }


    /*
     * Remove espaços e quebras
     * somente quando for Base64 puro.
     */

    src = src.replace(/\s/g, "");


    /*
     * ============================================
     * DATA URI
     * ============================================
     *
     * Exemplo:
     *
     * data:image/png;base64,AAAA...
     *
     * data:image/jpeg;base64,AAAA...
     */

    if (src.indexOf("data:image/") === 0) {
        imgElement.src = src;
    }


    /*
     * ============================================
     * URL HTTP/HTTPS
     * ============================================
     */

    else if (
        src.indexOf("http://") === 0 ||
        src.indexOf("https://") === 0
    ) {
        imgElement.src = src;

    }


    /*
     * ============================================
     * CAMINHO
     * ============================================
     */

    else if (
        src.indexOf("/") === 0 ||
        src.indexOf("./") === 0 ||
        src.indexOf("../") === 0
    ) {
        imgElement.src = src;
    }


    /*
     * ============================================
     * BASE64 PURO
     * ============================================
     */

    else {
        /*
         * JPEG
         */

        if (src.indexOf("/9j/") === 0){
            imgElement.src ="data:image/jpeg;base64," + src;
        }


        /*
         * PNG
         *
         * Base64 PNG normalmente começa
         * com iVBORw0KGgo
         */

        else if ( src.indexOf("iVBORw0KGgo") === 0 ) {
            imgElement.src = "data:image/png;base64," +src;
        }

        /*
         * GIF
         */

        else if (src.indexOf("R0lGOD") === 0) {
            imgElement.src ="data:image/gif;base64," + src;
        }


        /*
         * WebP
         *
         * Dependendo de como o Base64 foi
         * gerado, pode ser necessário tratar
         * especificamente no backend.
         */

        else {
            /*
             * Mantém PNG como fallback.
             */
            imgElement.src =  "data:image/png;base64," + src;

        }

    }


    imgElement.alt = bankName || "Banco";

    /*
     * ============================================
     * EVENTO DE ERRO
     * ============================================
     */

    imgElement.onerror =  function () {
            console.error("Erro ao carregar imagem do banco:",bankName);
            console.error("Imagem recebida:",imgData);
            imgElement.style.display = "none";
        };


    /*
     * ============================================
     * EVENTO DE SUCESSO
     * ============================================
     */

    imgElement.onload = function () {
            imgElement.style.display = "block";
        };


    /*
     * Caso a imagem já esteja em cache,
     * o onload pode já ter ocorrido.
     */

    if (imgElement.complete) {
        if (imgElement.naturalWidth > 0 ) {
            imgElement.style.display = "block";
        }
    }

    return true;
}



/* =========================================================
   CARREGA BANCO DO LOCALSTORAGE NA PÁGINA
   ========================================================= */

function onloadData() {

    var bankName = document.getElementById("bank-name");
    var bankImg = document.getElementById("bank-img");
    var bankSource = document.getElementById("bank-source");
    /*
     * Página não possui elementos de banco.
     */

    if (
        !bankName &&
        !bankImg &&
        !bankSource
    ) {
        return;
    }


    /*
     * Recupera banco.
     */

    var bank = getStoredBankData();
    if (!bank) {
        console.warn( "selectedBank não encontrado no localStorage.");
        return;
    }


    /*
     * Nome.
     */

    if (bankName) {
        bankName.textContent = bank.name || "Banco";
    }

    /*
     * Imagem.
     */

    if (bankImg && bank.img ) {
        setBankImage(
            bankImg,
            bank.img,
            bank.name
        );
    }


    /*
     * Campo hidden.
     */

    if (bankSource) {
        bankSource.value =  bank.name || "";
    }

}




/* =========================================================
   CONTEXTO DA APLICAÇÃO
   ========================================================= */

function getContextPath() {

    if ( typeof window.contextPath !== "undefined" && window.contextPath !== null) {
        return String(window.contextPath).replace(/\/$/,"");
    }

    var pathname = window.location.pathname;

    if (!pathname) {
        return "";
    }

    var firstSlash = pathname.indexOf("/", 1);

    if (firstSlash === -1) {
        return "";
    }
    return pathname.substring(0, firstSlash);
}




/* =========================================================
   MODAL DE RESGATE
   ========================================================= */

function setupRescueModal() {

    var modal = document.getElementById("rescueModal");
    var openButton = document.getElementById("btn-open-rescue" );
    var closeButton = document.getElementById("btn-close-modal");
    var cancelButton = document.getElementById("btn-cancel-rescue");

    if (!modal || !openButton) {
        return;
    }

    function openModal() {
        modal.classList.add("show");
        document.body.classList.add("modal-open");
    }

    function closeModal() {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");
    }

    openButton.addEventListener("click", function (event) {
            event.preventDefault();
            openModal();
        }
    );

    if (closeButton) {
        closeButton.addEventListener("click",function () {
                closeModal();
            }
        );
    }

    if (cancelButton) {
        cancelButton.addEventListener("click", function () {
                closeModal();
            }
        );
    }

    modal.addEventListener("click",function (event) {
            if (event.target === modal) {
                closeModal();
            }
        }
    );

    document.addEventListener("keydown", function (event) {
            if (event.key === "Escape" && modal.classList.contains("show")) {
                closeModal();
            }
        }
    );
}




/* =========================================================
   ESCAPA HTML
   ========================================================= */

function escapeHtml(value) {

    if ( value === null ||typeof value === "undefined") {
        return "";
    }

    var div = document.createElement("div");
    div.textContent = String(value);

    return div.innerHTML;
}
