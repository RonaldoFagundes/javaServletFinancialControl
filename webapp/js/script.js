
document.addEventListener("DOMContentLoaded", init);


function init() {
	setupAccountSelect();
	setupSelectAccountLinks();	
}



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
		{ label: "Cartão Crédito", move: "crc" },
		{ label: "Saque", move: "sqe" }
	],
	Poupança: [
		{ label: "Extrato", move: "ext" },
		{ label: "Transferência", move: "trf" },
		{ label: "Pagamentos", move: "pay" },
		{ label: "Saque", move: "sqe" }
	]
};



function setupAccountSelect() {

	const select = document.getElementById("accountSelect");
	if (!select) return;

	select.addEventListener("change", (e) => {

		const option = e.target.selectedOptions[0];
		if (!option.value) return;

		const { bank, number, type, amount, fk } = option.dataset;


		renderAccountCard({number, type,  amount });
		renderServices(type, option.value , amount, bank, number, fk);
	});
}


function setupSelectAccountLinks() {

	document.addEventListener("click", (e) => {

		const link = e.target.closest(".selectAccount");
		if (!link) return;

		e.preventDefault();

		const { id, bank, number, type, amount, fk } = link.dataset;	
		
		renderAccountCard({ number, type, amount });
		renderServices(type, id, amount, bank, number, fk);
	});
}


function renderAccountCard({ number, type, amount }) {

	const container = document.getElementById("accountDetails");
	if (!container) return;

	container.innerHTML = "";

	const card = document.createElement("div");
	card.className = "account-card";

	const formattedAmount = formatCurrency(amount);

	card.innerHTML = `
				       <h3 class="account-title">Detalhes da Conta</h3>
				       <div class="account-info">
				           ${createRow("Number", number)}
				           ${createRow("Type", type)}				           
				           <div class="account-row">
				               <span class="account-label">Amount:</span>
				               <span class="account-value amount-value" data-hidden="true">••••••</span>
				               <span class="toggle-amount" title="Mostrar/ocultar">👁️</span>
				           </div>
				       </div>
				   `;

	container.appendChild(card);

	// Adiciona funcionalidade do olho
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


function renderServices(type, id, amount, bank, number, fk) {

	const limite = 1000;
	const saldo = Number(amount) || 0 ;

	const container = document.getElementById("container-sevice");
	if (!container) return;

	container.innerHTML = "";

	const services = serviceMap[type] || [];
	if (!services.length) return;

	const wrapper = document.createElement("div");
	wrapper.className = "services-container";

	services.forEach(({ label, move }) => {

		//const disabled = move === "newInvest" && saldo < limite;
		
		/*		
		const disabled = (move === "newInvest" && saldo < limite) || 
		                ((move === "trf" || move === "pay") && saldo <= 0);	
		*/
		
		const disabled = (move === "newInvest" && saldo < limite) || 
				        ((move === "trf" || move === "pay" || move === "sqe") && saldo <= 0);	
						
		const data = { 
			idAcc: id, 
			bank: bank,
			number: number,  
			type: type,
			fkBnk: fk
		 }; 

		if (["trf", "pay", "sqe", "newInvest", "readInvest"].includes(move)) {			
			data.amount = saldo;
		}

		const link = createPostLink(label, move, data, disabled);

		wrapper.appendChild(link);
	});

	container.appendChild(wrapper);
}


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


function storeBankData(id, img, name, contact) {
    const bankData = {
        id: id,
        img: img,
        name: name,
        contact: contact
    };

    // Armazenando os dados como um objeto JSON no localStorage
    localStorage.setItem('selectedBank', JSON.stringify(bankData));
}



function getStoredBankData() {
    const bankData = localStorage.getItem('selectedBank');
    return bankData ? JSON.parse(bankData) : null;
}

	
	
function onloadData(){
	
	const bank = getStoredBankData();
	           
	   if (bank) {
	       document.getElementById("bank-name").innerText = bank.name;
	       document.getElementById("bank-img").src = "data:image/png;base64," + bank.img; 		   
		   
		   document.getElementById("bank-source").value = bank.name;		                
	    }
} 








/*
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
*/

/*
function renderServices2(type, id, amount) {	

	const limite = parseInt(1000.00);
	const saldo  = parseInt(amount);

	const container = document.getElementById("container-sevice");
	if (!container) return;

	container.innerHTML = "";

	const services = serviceMap[type] || [];
	if (!services.length) return;

	const wrapper = document.createElement("div");
	wrapper.className = "services-container";

	services.forEach(({ label, move }) => {

		const a = document.createElement("a");
		a.textContent = label;
		a.href = "#";
		a.className = "service-card";

		if (move === "newInvest") {				
			if (saldo < limite) {		
				a.className = "link-desabilitado";
			}		
		}

		a.addEventListener("click", function(e) {
			e.preventDefault();

			const form = document.createElement("form");
			form.method = "POST";
			form.action = move;

			const idInput = document.createElement("input");
			idInput.type = "hidden";
			idInput.name = "idAcc";
			idInput.value = id;
			form.appendChild(idInput);

			if (move === "trf" || move === "pay" || move === "newInvest" || move === "readInvest" ) {
				const amountInput = document.createElement("input");
				amountInput.type = "hidden";
				amountInput.name = "amount";
				amountInput.value = saldo;
				form.appendChild(amountInput);
			}

			document.body.appendChild(form);
			form.submit();
		});

		wrapper.appendChild(a);
	});

	container.appendChild(wrapper);
}
*/



/*
function renderServices1(type, id, amount) {	
	  
	const limite = parseInt(30000.00);
	const saldo  = parseInt(amount);
	
	const container = document.getElementById("container-sevice");
	if (!container) return;

	container.innerHTML = "";

	const services = serviceMap[type] || [];
	if (!services.length) return;

	const wrapper = document.createElement("div");
	wrapper.className = "services-container";
	services.forEach(({ label, move }) => {

		const a = document.createElement("a");
		a.textContent = label;
						
		a.className = "service-card";
		
		if(move === "newInvest"){		
			
							
			if(saldo < limite){		
				a.className = "link-desabilitado"; 				
		    }else{
				a.href = `${move}?idAcc=${id}&amount=${saldo}`;
			}
					
		}else if(move==="trf"){
			
			//creatTrsContainer();
			
			a.href = `${move}?idAcc=${id}&amount=${saldo}`;	
		
		}else if(move==="pay"){
			
			a.href = `${move}?idAcc=${id}&amount=${saldo}`;
							
			
		}else{
			a.href = `${move}?idAcc=${id}`;
		}	
		
		
		
			
		//a.href = `${move}?idAcc=${id}&amount=${saldo}`;
		
		/
		if(move==="trf" || move==="pay" || move==="newInvest"){
			a.href = `${move}?idAcc=${id}&amount=${saldo}`;
		}else{
			a.href = `${move}?idAcc=${id}`;
		}
		/
		
		//a.href = `selectAccount?idAcc=${id}&move=${move}`;		
		//a.href = `${move}?idAcc=${id}`;
		
		
		/
		if( type==="Investimentos" && move==="invC"){			
			a.href = "cad_investment.jsp";
		}else{
			a.href = `${move}?idAcc=${id}`;
		}
		/
		//a.href = `${move}?idAcc=${id}`;		
		
		//a.className = "service-card";

		wrapper.appendChild(a);
	});

	container.appendChild(wrapper);
}
*/


/*
function storeBankData(id, img, name) {
    const bankData = {
        id: id,
        img: img,
        name: name
    };
    localStorage.setItem('selectedBank', JSON.stringify(bankData)); // Armazena no localStorage
}
*/
