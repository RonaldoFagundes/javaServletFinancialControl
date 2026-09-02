"use strict";


/* =========================================================
   INICIALIZAÇÃO
   ========================================================= */

document.addEventListener("DOMContentLoaded", function () {

    setupLoginValidation();

    setupBankValidation();

    setupAccountValidation();

    setupInvestmentValidation();

    setupTransactionValidation();

});


/* =========================================================
   FUNÇÕES AUXILIARES
   ========================================================= */

/*
 * Exibe mensagem de erro.
 */
function showError(input, message) {

    if (!input) {
        return false;
    }

    input.classList.add("input-error");

    var error =
        input.parentElement.querySelector(".validation-error");

    if (!error) {

        error =
            document.createElement("span");

        error.className =
            "validation-error";

        input.parentElement.appendChild(error);
    }

    error.textContent =
        message;

    return false;
}


/*
 * Remove mensagem de erro.
 */
function clearError(input) {

    if (!input) {
        return;
    }

    input.classList.remove(
        "input-error"
    );

    var error =
        input.parentElement.querySelector(
            ".validation-error"
        );

    if (error) {
        error.remove();
    }
}


/*
 * Verifica se o campo está vazio.
 */
function isEmpty(value) {

    return (
        value === null ||
        typeof value === "undefined" ||
        String(value).trim() === ""
    );
}


/*
 * Validação básica de URL.
 */
function isValidUrl(value) {

    try {

        var url =
            new URL(value);

        return (
            url.protocol === "http:" ||
            url.protocol === "https:"
        );

    } catch (error) {

        return false;
    }
}


/*
 * Remove caracteres não numéricos.
 */
function onlyNumbers(value) {

    return String(value || "")
        .replace(/\D/g, "");
}


/* =========================================================
   LOGIN
   ========================================================= */

function setupLoginValidation() {

    var form =
        document.querySelector(
            'form[action$="/login"], form#loginForm'
        );

    if (!form) {
        return;
    }

    form.addEventListener(
        "submit",
        function (event) {

            var user =
                form.querySelector(
                    '[name="user"]'
                );

            var password =
                form.querySelector(
                    '[name="password"]'
                );

            var valid = true;


            clearError(user);
            clearError(password);


            if (
                !user ||
                isEmpty(user.value)
            ) {

                showError(
                    user,
                    "Informe o usuário."
                );

                valid = false;
            }


            if (
                !password ||
                isEmpty(password.value)
            ) {

                showError(
                    password,
                    "Informe a senha."
                );

                valid = false;
            }


            if (!valid) {

                event.preventDefault();

                return false;
            }

            return true;
        }
    );
}


/* =========================================================
   BANCO
   ========================================================= */

function setupBankValidation() {

    var form =
        document.querySelector(
            "#bankForm"
        );

    if (!form) {
        return;
    }

    form.addEventListener(
        "submit",
        function (event) {

            var name =
                form.querySelector(
                    '[name="name"]'
                );

            var number =
                form.querySelector(
                    '[name="number"]'
                );

            var contact =
                form.querySelector(
                    '[name="contact"]'
                );

            var valid = true;


            clearError(name);
            clearError(number);
            clearError(contact);


            /*
             * Nome do banco
             */

            if (
                !name ||
                isEmpty(name.value)
            ) {

                showError(
                    name,
                    "Informe o nome do banco."
                );

                valid = false;
            }


            /*
             * Número do banco
             */

            if (
                number &&
                !isEmpty(number.value)
            ) {

                var bankNumber =
                    onlyNumbers(
                        number.value
                    );

                if (
                    bankNumber.length < 3
                ) {

                    showError(
                        number,
                        "Informe um número de banco válido."
                    );

                    valid = false;
                }
            }


            /*
             * Site / contato
             */

            if (
                contact &&
                !isEmpty(contact.value)
            ) {

                if (
                    !isValidUrl(
                        contact.value.trim()
                    )
                ) {

                    showError(
                        contact,
                        "Informe uma URL válida."
                    );

                    valid = false;
                }
            }


            if (!valid) {

                event.preventDefault();

                return false;
            }

            return true;
        }
    );
}


/* =========================================================
   CONTA
   ========================================================= */

function setupAccountValidation() {

    var form =
        document.querySelector(
            "#accountForm"
        );

    if (!form) {
        return;
    }

    form.addEventListener(
        "submit",
        function (event) {

            var number =
                form.querySelector(
                    '[name="number"]'
                );

            var type =
                form.querySelector(
                    '[name="type"]'
                );

            var balance =
                form.querySelector(
                    '[name="balance"]'
                );

            var bank =
                form.querySelector(
                    '[name="fkBnk"], [name="idBnk"]'
                );

            var valid = true;


            clearError(number);
            clearError(type);
            clearError(balance);
            clearError(bank);


            /*
             * Número da conta
             */

            if (
                !number ||
                isEmpty(number.value)
            ) {

                showError(
                    number,
                    "Informe o número da conta."
                );

                valid = false;
            }


            /*
             * Tipo da conta
             */

            if (
                !type ||
                isEmpty(type.value)
            ) {

                showError(
                    type,
                    "Selecione o tipo da conta."
                );

                valid = false;
            }


            /*
             * Banco
             */

            if (
                bank &&
                isEmpty(bank.value)
            ) {

                showError(
                    bank,
                    "Selecione o banco."
                );

                valid = false;
            }


            /*
             * Saldo
             */

            if (
                balance &&
                !isEmpty(balance.value)
            ) {

                var amount =
                    parseAmount(
                        balance.value
                    );

                if (amount < 0) {

                    showError(
                        balance,
                        "O saldo não pode ser negativo."
                    );

                    valid = false;
                }
            }


            if (!valid) {

                event.preventDefault();

                return false;
            }

            return true;
        }
    );
}


/* =========================================================
   INVESTIMENTO
   ========================================================= */

function setupInvestmentValidation() {

    var form =
        document.querySelector(
            "#investmentForm"
        );

    if (!form) {
        return;
    }

    form.addEventListener(
        "submit",
        function (event) {

            var amount =
                form.querySelector(
                    '[name="amount"]'
                );

            var description =
                form.querySelector(
                    '[name="desc"], [name="description"]'
                );

            var valid = true;


            clearError(amount);
            clearError(description);


            /*
             * Valor do investimento
             */

            if (
                !amount ||
                isEmpty(amount.value)
            ) {

                showError(
                    amount,
                    "Informe o valor do investimento."
                );

                valid = false;

            } else {

                var value =
                    parseAmount(
                        amount.value
                    );

                if (value <= 0) {

                    showError(
                        amount,
                        "O valor deve ser maior que zero."
                    );

                    valid = false;
                }
            }


            /*
             * Descrição
             */

            if (
                description &&
                !isEmpty(description.value) &&
                description.value.trim().length < 3
            ) {

                showError(
                    description,
                    "A descrição deve possuir pelo menos 3 caracteres."
                );

                valid = false;
            }


            if (!valid) {

                event.preventDefault();

                return false;
            }

            return true;
        }
    );
}


/* =========================================================
   TRANSAÇÕES
   ========================================================= */

function setupTransactionValidation() {

    var form =
        document.querySelector(
            "#transactionForm"
        );

    if (!form) {
        return;
    }

    form.addEventListener(
        "submit",
        function (event) {

            var amount =
                form.querySelector(
                    '[name="amount"]'
                );

            var account =
                form.querySelector(
                    '[name="idAcc"], [name="account"]'
                );

            var valid = true;


            clearError(amount);
            clearError(account);


            /*
             * Conta
             */

            if (
                account &&
                isEmpty(account.value)
            ) {

                showError(
                    account,
                    "Selecione uma conta."
                );

                valid = false;
            }


            /*
             * Valor
             */

            if (
                !amount ||
                isEmpty(amount.value)
            ) {

                showError(
                    amount,
                    "Informe o valor."
                );

                valid = false;

            } else {

                var value =
                    parseAmount(
                        amount.value
                    );

                if (value <= 0) {

                    showError(
                        amount,
                        "O valor deve ser maior que zero."
                    );

                    valid = false;
                }
            }


            if (!valid) {

                event.preventDefault();

                return false;
            }

            return true;
        }
    );
}


/* =========================================================
   CONVERSÃO DE VALORES
   ========================================================= */

function parseAmount(value) {

    if (
        value === null ||
        typeof value === "undefined"
    ) {

        return 0;
    }


    if (
        typeof value === "number"
    ) {

        return isFinite(value)
            ? value
            : 0;
    }


    var text =
        String(value).trim();


    if (!text) {
        return 0;
    }


    /*
     * Formato brasileiro:
     *
     * 1.234,56
     * 1234,56
     */

    if (
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
    }


    var number =
        Number(text);


    return isFinite(number)
        ? number
        : 0;
}


/* =========================================================
   FORMATA MOEDA
   ========================================================= */

function formatCurrency(value) {

    var number =
        parseAmount(value);


    return number.toLocaleString(
        "pt-BR",
        {
            style: "currency",
            currency: "BRL"
        }
    );
}
