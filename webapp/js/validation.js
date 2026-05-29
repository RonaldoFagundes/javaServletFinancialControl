/**
 * 
 */
	
document.addEventListener("DOMContentLoaded", () => {
    const errorMsg = document.body.dataset.error;
    if (errorMsg) {
        const errorContainer = document.createElement("p");
        errorContainer.classList.add("error-msg");
        errorContainer.textContent = errorMsg;

        const form = document.querySelector(".login-form");
        form.prepend(errorContainer);
    }
	
	onloadTrs();	
});



/*
function onloadTrfOut(){
	const contentSourceTrf = document.getElementById("content-source-trf");		
	
	//contentSourceTrf.className = "content-show";
	contentSourceTrf.classList.add("content-show"); 	
}
*/





const trfRadios = document.querySelectorAll('input[name="trf"]');
const contentSource = document.getElementById('content-source-trf');

trfRadios.forEach(radio => {
    radio.addEventListener('change', () => {
        if (radio.value === 'out' && radio.checked) {
            contentSource.classList.add('show');
        } else {
            contentSource.classList.remove('show');
        }
    });
});



const sourceRadios = document.querySelectorAll('input[name="source"]');

const contentAccounts = document.getElementById('content-accounts');
const contentForward = document.getElementById("content-forward");

sourceRadios.forEach(radio => {
	radio.addEventListener('change',()=>{
		
		
		if(radio.value==='pessoal' && radio.checked ){
			contentAccounts.classList.add('show');
			contentForward.classList.add('remove');
		}else{
			contentAccounts.classList.remove('show');
			contentForward.classList.remove('remove');
		}
		
		/*
		if(radio.value==='outros' && radio.checked ){
			contentForward.classList.add('show');
		}else{
			contentForward.classList.remove('show');
		}
		*/
				
		
	});
});








function onloadTrs() {
 
    const containerTrf = document.getElementById("container-trf");
	
	const containerPay = document.getElementById("container-pay");
	
	const containerWay = document.getElementById("container-way");	
		
	const containerAccountForward = document.getElementById("container-account-forward");	
		
		
	let trs_type = frmTrs.trsType.value;
	
	    // console.log("trs_type "+trs_type);
	
	let type_acc = frmTrs.typeAcc.value;
		
		console.log("type_acc "+type_acc);
		
		

    if (!containerTrf) {
        console.error("Elemento #container-trs não encontrado");
        return;
    }

	
	if (trs_type === "/trf") {
		
		
		if(type_acc === "Investimentos"){			
			
			 containerWay.classList.add("show");

			 containerTrf.classList.remove("show");	  
		//	 containerPay.classList.add("show");
		 
		     containerAccountForward.classList.add("show"); 
			 
		}else{
		
	         containerWay.classList.add("show");	  
	  
	         containerTrf.classList.add("show");
			 
	         containerPay.classList.remove("show");
			 
			 containerAccountForward.remove("show");	
	  
	   }
	   
	  
	}else if(trs_type === "/pay") { 
		
	       containerWay.classList.add("show");
		
      	   containerTrf.classList.remove("show");	 
		   
	       containerPay.classList.add("show"); 
	
	}else{
		
	      containerWay.classList.remove("show");
		  
	      containerTrf.classList.remove("show");
		  
	      containerPay.classList.remove("show");
	}
	

}

	
	
	
		
	
	/*
    // Verifica se trsType é definido
    if (typeof trsType === "undefined") {
        trsType = 'trf'; // Definindo valor padrão caso trsType esteja indefinido
        console.warn("trsType estava indefinido, definindo valor padrão.");
    }

    // Modifica a classe do container com base em trsType
    if (trsType === "trf") {
        containerTrs.className = "content-trs";  // Adiciona a classe content-trs
        containerTrs.innerHTML = "<h2>Transferências</h2><p>Conteúdo relacionado a Transferências</p>";  // Adiciona conteúdo HTML
    } else if (trsType === "pay") {
        containerTrs.className = "content-pay";  // Adiciona a classe content-pay
        containerTrs.innerHTML = "<h2>Pagamentos</h2><p>Conteúdo relacionado a Pagamentos</p>";  // Adiciona conteúdo HTML
    } else {
        containerTrs.className = "content-default";  // Caso padrão
        containerTrs.innerHTML = "<h2>Conteúdo Geral</h2><p>Conteúdo relacionado a outro tipo de transação.</p>";  // Conteúdo padrão
    }
	*/
	
//}







function login() {
    const form = document.getElementById("frmLogin");
    const user = form.user.value.trim();
    const password = form.password.value.trim();

    // Limpar mensagens anteriores
    document.getElementById("errorUser").textContent = "";
    document.getElementById("errorPassword").textContent = "";

    let valid = true;

    if (!user) {
        document.getElementById("errorUser").textContent = "Preencha o campo User!";
        form.user.focus();
        valid = false;
    }

	
	if (!password) {
	        document.getElementById("errorPassword").textContent = "Preencha o campo Password!";
	        if (valid) form.password.focus(); // foca no primeiro erro
	        valid = false;
	    }

	    if (valid) {
	        form.submit(); // envia o form via POST
	    }
	}
	
	
/*		
function login(){
	 let user = frmLogin.user.value;
	 let password = frmLogin.password.value;
	 
	 if (user === "") {
	 	alert("preencha o campo User!");
	 	frmLogin.user.focus();
	 	return false
	}else if (password === "") {
		alert("preencha o campo password!");
		frmLogin.password.focus();
		return false
				
	}else{
	   document.forms["frmLogin"].submit();
	}	
	 
}
*/






function validateInvestment() {
	
	
	let broker = frmInvestment.broker.value;

	let cat = frmInvestment.cat.value;
	
	let type = frmInvestment.type.value;
	
	let open = frmInvestment.open.value;
	
	let expery = frmInvestment.expery.value;

	let rate_type = frmInvestment.rate_type.value;

	let rate = frmInvestment.rate.value;

	let price = frmInvestment.price.value;
	
	let desc = frmInvestment.desc.value;


	if (broker === "") {
		alert("preencha o campo Broker!");
		frmInvestment.broker.focus();
		return false
		
	} else if (cat === "") {
		alert("preencha o campo Categoria!");
		frmInvestment.cat.focus();
		return false
				
	} else if (type === "") {
		alert("preencha o campo type!");
		frmInvestment.type.focus();
		return false
		
	} else if (open === "") {
		alert("preencha o campo Open!");
		frmInvestment.open.focus();
		return false
		
	} else if (expery === "") {
		alert("preencha o campo vencimento!");
		frmInvestment.expery.focus();
		return false

	} else if (rate_type === "") {
		alert("preencha o campo typo taxa!");
		frmInvestment.rate_type.focus();
		return false
		
	} else if (rate === "") {
		alert("preencha o campo taxa!");
		frmInvestment.rate.focus();
		return false

	} else if (price === "") {
		alert("preencha o campo valor!");
		frmInvestment.price.focus();
		return false
		
	} else if (desc === "") {
		alert("preencha o campo Desc!");
		frmInvestment.desc.focus();
		return false	
		
	} else {
		document.forms["frmInvestment"].submit();
	}
	
}	
	
	
	






function validateRescue(){
		
	let rescue  = frmRescue.rescue.value ;
	let desc    = frmRescue.desc.value ;
		
	if (rescue === "") {
	  // alert("preencha o campo rescue_value!");
	   document.getElementById("erroValue").textContent = "Preencha o campo Valor!";
	   frmRescue.rescue.focus();
	   return false
	} else {
	
	  document.forms["frmRescue"].submit();
	}
	
}		
	





document.getElementById("accountSelect").addEventListener("change", function() {
        const selectedOption = this.options[this.selectedIndex];
        document.getElementById("selected-bank").value = selectedOption.getAttribute("data-bank");
        document.getElementById("selected-type").value = selectedOption.getAttribute("data-type");
        document.getElementById("selected-number").value = selectedOption.getAttribute("data-number");
		document.getElementById("selected-amount").value = selectedOption.getAttribute("data-amount");
		
		document.getElementById("type-move").value = "in";
		
		//document.getElementById("bank-source").value = document.getElementById("bank-name").value;		
		
    });
 

	
	
	
	
function validateTrs(){
	
	//let  amount  =  frmTrs.amountAcc.value;	
	//let valueTrs = frmTrs.trsValue.value;
	
	//let bankName =  document.getElementById("bank-name").value;
	//document.getElementById("bank-source").textContent = bankName;
	
	let typeTrfOption = frmTrs.trf.value;
	
	
	
	let amount = parseFloat(frmTrs.amountAcc.value); 
	let valueTrs = parseFloat(frmTrs.trsValue.value); 
	
	document.getElementById("errorTrsValue").textContent = "";
	
	if (isNaN(valueTrs) || valueTrs <= 0) {
	        document.getElementById("errorTrsValue").textContent = "Preencha o campo Valor corretamente!";
	        frmTrs.trsValue.focus();
	        return false;
	 }
		 
		
	 
	 
	if(typeTrfOption == "out") {	
			
      if (amount < valueTrs) {
	        document.getElementById("errorTrsValue").textContent = "Saldo insuficiente!";
	        frmTrs.trsValue.focus();
	        return false;
	  }	
	
	}	
	
		
	
		document.forms["frmTrs"].submit();
		return true;	
	
}



