package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountsDao;
import dao.BankDao;
import dao.InvestmentsDao;
import dao.LoginDao;
import dao.RescueDao;
import dao.TransactionsDao;
import model.AccountsModel;
import model.BankModel;
import model.InvestmentsModel;
import model.LoginModel;
import model.RescueModel;
import model.TransactionsModel;
import service.InvestmentsService;


/**
 * Servlet implementation class PostController
 */
@WebServlet(urlPatterns = {
		
		"/Controller",
		"/main",
		"/login",
			
		"/selectBank",
		"/newBank",
		"/creatBank",		
		
		"/readInvest",				
			
		"/newInvest",
		"/creatInvest",		
		
		"/selectInvest",		
		"/creatRescue",			
		
		"/ext",
		"/trf",
		"/pay",
		"/sqe",
		"/crc",
		
		"/creatTrs"
		
		})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	LoginModel lgm = new LoginModel();
	LoginDao lgd = new LoginDao();
	
	BankModel bnkm = new BankModel();
	BankDao bnkd = new BankDao();	
	
    AccountsModel actm = new AccountsModel();
    AccountsDao actd = new AccountsDao();    
	
	InvestmentsModel invm = new InvestmentsModel();	
	InvestmentsDao invd = new InvestmentsDao();	
	InvestmentsService invs = new InvestmentsService();
	
	RescueModel resm = new RescueModel();
	RescueDao   resd = new RescueDao(); 	
	
	TransactionsModel trsm = new TransactionsModel();
	TransactionsDao   trsd = new TransactionsDao();
	
	String action ;	
	
	boolean autoRescue = false;
	boolean pay = false;
	
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// dao.test();
		
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
		action = request.getServletPath();
		
		System.out.println("GET Action: " + action);
		
		 
		 switch (action) {
	        case "/main":
	            home(request, response);
	            break;

	        case "/selectBank":
	            selectedBank(request, response);  
	            break;
	            
	        case "/newBank":
	        	response.sendRedirect("banks/cad_bank.jsp");   
	            break;
	         
	        /*    
	        case "/readInvest":
	            investments(request, response);
	            break;
	        */
	            
	        /*    
	        case "/newInvest":
	        	newInvest(request, response); 
	            break;   
	        */     
	        case "/selectInvest":
	        	selectInvest(request, response);
	            break;            
	               
	     	case "/crc":
				 creditCard(request, response);
			     break;

	       default:
	        	home(request, response);
	            //response.sendRedirect("home.jsp"); // redireciona para login se rota não reconhecida
	            break;
	    }
		 
		 
		 /*
		if (action.equals("/main")) {			
			home(request, response);
		//}else if (action.equals("/login")) {
			//access(request, response);
		}else if (action.equals("/selectBank")) {
			selectBank(request, response);	
		}else if (action.equals("/selectAccount")) {
			selectAccount(request, response);
		}else if (action.equals("/cadInvest")) {
			cadInvest(request, response);
		}
		*/
		
	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
		 action = request.getServletPath();
		 System.out.println("POST Action: " + action);		 
	 		 
		 switch(action) {
		 
		 case "/login" :
			 access(request, response);
		 break;
		 
		 case "/creatBank" :
			 insertBank(request, response);
		 break;
		 
		 case "/readInvest":
	            investments(request, response);
	     break;
	            
		 case "/newInvest":
	         newInvest(request, response); 
	     break;   
		 
		 case "/creatInvest" :
			 insertInvest(request, response);
		 break;
		 
		 case "/creatRescue" :
			 insertRescue(request, response);
		 break;
		 
		 case "/trf" :
			 newTransactions(request, response);
		 break;
		 
		 case  "/pay":
			 newTransactions(request, response);
		 break;
		 
		 case  "/sqe":
			 newTransactions(request, response);
		 break;	
		 			 
		 case  "/creatTrs":
			 createTransactions(request, response);
		 break;	
		 
		 case "/ext":
	        selectTransactions(request, response);
	    break;
		 		 
		default:	
			response.sendRedirect("home.jsp");
	  	break;	
		 
		 }
		 
		 
		    /*
		    if("/login".equals(action)) {  
		        access(request, response);
		        
		    }else if("/creatBank".equals(action)) {
		    	insertBank(request, response); 
		        
		    }else if("/creatInvest".equals(action)) {
		        insertInvest(request, response);
		        
		    }else if("/creatRescue".equals(action)) {
		        insertRescue(request, response);
		        
		    }else{
		        response.sendRedirect("home.jsp");
		    }
		    */
		    
		    
		
		/*
	    String user = request.getParameter("user");
	    String password = request.getParameter("password");
	    
	    // Validação simples
	    if ("rfagundes".equals(user) && "12345".equals(password)) {

	        ArrayList<BankModel> list = bnkd.listBanks();
	        request.setAttribute("banks", list);

	        RequestDispatcher rd = request.getRequestDispatcher("bancs.jsp");
	        rd.forward(request, response);

	    } else {
	        // Redireciona de volta ao login com mensagem de erro
	        request.setAttribute("error", "Usuário ou senha inválidos!");
	        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
	        rd.forward(request, response);
	    }
	    */   
		    
	}
	
	
	
	
	
	
	
	
	protected void creditCard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   System.out.println("creditCard");
	}
	
	
	
	
	

				
	
	protected void home(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("home.jsp");
	}
	
		
	
	
	
	
	protected void access(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String user = request.getParameter("user");
		String password = request.getParameter("password");		
		
		if(user.equals("rfagundes") && password.equals("12345") ) {
			
			ArrayList<BankModel> list = bnkd.listBanks();
			request.setAttribute("banks" , list);
			RequestDispatcher rd = request.getRequestDispatcher("banks/banks.jsp");
			rd.forward(request, response);	
			
		}else {			
			
			request.setAttribute("error", "Usuário ou senha inválidos!");
	        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
	        rd.forward(request, response);
	    }
		
		/*
		lgm.setUser(user);
		lgm.setPassword(password);
		
		if(lgd.getLogIn(lgm)) {
			System.out.println("access permitido!!");
		}
		*/
	}
	
	
	
	
	
	/*
	protected void banks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		ArrayList<BankModel> list = bnkd.listBanks();
		request.setAttribute("banks" , list);
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);	
		
		//response.sendRedirect("list_investment.jsp");
		
				/
				for(int i =0; i<list.size(); i++) {			
					System.out.println(list.get(i).getBrokerName());
					System.out.println(list.get(i).getType());
					System.out.println(list.get(i).getRateType());
					System.out.println(list.get(i).getRate());
					System.out.println(list.get(i).getValue());			
				}
				/		
	}
	*/
	
	
		
	
	
	
	protected void selectedBank(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		String idBnk = request.getParameter("idBnk");
	
		actm.setFkbnk(Integer.parseInt(idBnk));
		
		ArrayList<AccountsModel> list = actd.listAccounts(actm);
		
		request.setAttribute("accounts" , list);
		RequestDispatcher rd = request.getRequestDispatcher("accounts/accounts.jsp");
		rd.forward(request, response);
		/*
		for(int i =0; i<list.size(); i++) {			
			System.out.println(list.get(i).getNumber());
			System.out.println(list.get(i).getType());
			System.out.println(list.get(i).getDesc());
			System.out.println(list.get(i).getAmount());		
		}
		*/		
	}
	
	
	
	
	
	
	protected void insertBank(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		   System.out.println("function insertBank");		
	}
	
	
	
	
	
	
	
	
	/*
	protected void selectAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idAcc = request.getParameter("idAcc");
		
		String move = request.getParameter("move");	
		
       // int idAc = Integer.parseInt(idAcc);
		
		//int idAcc = Integer.parseInt(request.getParameter("idAcc"));
		
		System.out.println("idAcc "+idAcc+" movimentaçõao "+move);
				
		//invm.setFkBka(Integer.parseInt(idAcc));
		
		/
		   <% for(int i =0; i < list.size(); i++){ %>
        	
        	<tr>
        	  <td><%= list.get(i).getNumber()%></td>       	  
        	  <td><%= list.get(i).getType()%></td>
        	  <td><%= list.get(i).getDesc()%></td>  
        	  <td><%= list.get(i).getAmount()%></td>
        	  
        	  <td><a href="selectAccount?idAcc=<%=list.get(i).getId()%>">Select</a></td>  
        	</tr>
        	 
        <% } %>	 
		 
		  /
	}
	*/
	
	
		
	
	
	
	
	
	protected void investments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		/*parametros via js*/
		BigDecimal amountAct = new BigDecimal(request.getParameter("amount"));
		actm.setAmount(amountAct);
		
				   
        // String idAcc = request.getParameter("idAcc");
         int idAcc = Integer.parseInt(request.getParameter("idAcc"));
         
         invm.setFkBka(idAcc);
         
         ArrayList<InvestmentsModel> list = invd.listInvestments(invm);
         
         
 		 //String move = request.getParameter("move");	
 		
        // int idAc = Integer.parseInt(idAcc);
 		
 		//int idAcc = Integer.parseInt(request.getParameter("idAcc"));
 		
 		//System.out.println("function investments  idAcc "+idAcc+" movimentaçõao "+move);	
		
		/*
		for(int i =0; i<list.size(); i++) {		
			
			System.out.println(list.get(i).getBrokerName());
			System.out.println(list.get(i).getType());
			System.out.println(list.get(i).getRateType());
			System.out.println(list.get(i).getRate());		
			System.out.println(list.get(i).getValue());
		}
		*/
			
		request.setAttribute("investments" , list);
		RequestDispatcher rd = request.getRequestDispatcher("investments/list_investment.jsp");
		rd.forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	/*
	protected void investments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/
        String idAcc = request.getParameter("idAcc");
		
		invm.setFkBka(Integer.parseInt(idAcc));
		
		ArrayList<InvestmentsModel> list = invd.listInvestments(invm);
		request.setAttribute("investments" , list);
		RequestDispatcher rd = request.getRequestDispatcher("list_investment.jsp");
		rd.forward(request, response);	
		/
			
		ArrayList<InvestmentsModel> lis = invd.listInvestments(invm);
				
		request.setAttribute("investments" , list);
		RequestDispatcher rd = request.getRequestDispatcher("list_investment.jsp");
		rd.forward(request, response);	
			
		
		//response.sendRedirect("list_investment.jsp");
						
				for(int i =0; i < lis.size(); i++) {			
					System.out.println(lis.get(i).getBrokerName());
					System.out.println(lis.get(i).getType());
					System.out.println(lis.get(i).getRateType());
					System.out.println(lis.get(i).getRate());
					System.out.println(lis.get(i).getValue());			
				}
								
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	protected void selectInvest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		 int idInv = Integer.parseInt(request.getParameter("idInv"));		 
		 
		 invm.setId(idInv);
		 
		 invd.selectInvestById(invm);		 
		 
		 request.setAttribute("investment", invm);

		 RequestDispatcher rd = request.getRequestDispatcher("investments/slctd_investment.jsp");
		 rd.forward(request, response);
		 		 
		 /*
		 System.out.println(
				 invm.getBrokerName()+" "+
		         invm.getType()+" "+
				 invm.getOpen()+" "+
		         invm.getExpery()+" "+
				 invm.getRateType()+" "+
		         invm.getRate()+" "+
				 invm.getValue()+" "+
		         invm.getProfitability()+" "+
				 invm.getRescue()+" "+
		         invm.getAmount());
		*/
		 
	}
	
	
	
	
	
	
	
	
	
	
	protected void newInvest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		int idAcc = Integer.parseInt(request.getParameter("idAcc"));		
		 		 
		 request.setAttribute("fkbka", idAcc);
		       
		 /*parametros via js*/
		 BigDecimal amountAct = new BigDecimal(request.getParameter("amount"));
		 actm.setAmount(amountAct);
		 
		 
		 //int amount = Integer.parseInt(request.getParameter("amount"));
		 //String amount = request.getParameter("amount");
		 //System.out.println(amount);

		 RequestDispatcher rd = request.getRequestDispatcher("investments/cad_investment.jsp");
		 rd.forward(request, response);
		//response.sendRedirect("investments/cad_investment.jsp");
		
	}
	
	
	
	
	
	
	
	protected void insertInvest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
				
		String move = "out" ;
        String typeTrs = "Investment" ;
        String form = "Digital" ;		
		
		String rateString = request.getParameter("rate");	
		String priceString = request.getParameter("price");	
		BigDecimal rate = new BigDecimal(rateString);	
		BigDecimal price = new BigDecimal(priceString);		
		BigDecimal balance = actm.getAmount().subtract(price);
		
		
		String trsDate = request.getParameter("open");		
		String source = request.getParameter("broker");		
		String desc  = request.getParameter("desc");		
		
		int fkBka = Integer.parseInt(request.getParameter("fk"));		
		
		/*
		invm.setBroker(source);
        invm.setCat(request.getParameter("cat"));
        invm.setType(request.getParameter("type"));     
        invm.setOpen(trsDate); 
        invm.setExpery(request.getParameter("expery"));        
        invm.setRateType(request.getParameter("rate_type"));
        invm.setRate(rate);                
        invm.setValue(price);        
        invm.setDesc(desc);        
        invm.setFkBka(fkBka);
        */
		
		System.out.println(source);
        System.out.println(request.getParameter("cat"));
        System.out.println(request.getParameter("type"));		
        System.out.println(trsDate);		
        System.out.println(request.getParameter("expery"));	
        System.out.println(request.getParameter("rate_type"));		
        System.out.println(rate);
        System.out.println(price);        
        System.out.println(desc);		
        System.out.println(fkBka);
        
        //if ( invd.insert(invm) ) {        	
        
        	this.insertTransactions(move, trsDate, typeTrs, source, form, desc, price, balance, fkBka);
        	
        	 
            /* 
             trsm.setMov(mov);
             trsm.setDate(request.getParameter("open"));
             trsm.setType(type);
             trsm.setSource(request.getParameter("broker"));
             trsm.setForm(form);
             trsm.setDesc(request.getParameter("desc"));
             trsm.setValue(price);
             trsm.setBalance(balance);
             trsm.setFkbka(Integer.parseInt(request.getParameter("fk")));  
             
             trsd.insert(trsm);
             */
             
             
        //  }else {
        	
        //	  System.out.println("erro insertInvest ");
       // }
        	
        	
        	
                
       // response.sendRedirect("readInvest");
        
        //int idAcc = Integer.parseInt(request.getParameter("idAcc"));
        
        //invm.setFkBka(fkBka);
        
        ArrayList<InvestmentsModel> list = invd.listInvestments(invm);
        
        request.setAttribute("investments" , list);
   		RequestDispatcher rd = request.getRequestDispatcher("investments/list_investment.jsp");
   		rd.forward(request, response);
		  
		
		   /*
           System.out.println(request.getParameter("broker"));
           System.out.println(request.getParameter("cat"));
           System.out.println(request.getParameter("type"));		
           System.out.println(request.getParameter("open"));		
           System.out.println(request.getParameter("expery"));	
           System.out.println(request.getParameter("rate_type"));		
           System.out.println(rate);
           System.out.println(price);
           System.out.println(actm.getAmount());
           System.out.println(balance);
           System.out.println(request.getParameter("desc"));		
           System.out.println(request.getParameter("fk"));
           */
           
         
        /*
          transactions 
          
          mov   = in/out
          date  = request.getParameter("open")
          type  = Investment/Rescue
          
          souce = request.getParameter("broker")
          form  = Digital
          desc  = request.getParameter("desc")
          value = price
          fkbka = Integer.parseInt(request.getParameter("fk")
          
         */
       
	}

	
	
	
	
	
	
	
	
	protected void insertRescue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		BigDecimal iofValue = BigDecimal.ZERO;		
		BigDecimal irTx = BigDecimal.ZERO;
		BigDecimal irValue = BigDecimal.ZERO;		
		BigDecimal liquid = BigDecimal.ZERO;	
		
		//BigDecimal balance = BigDecimal.ZERO;
				
		String move = "in" ;		
        String typeTrs = "Rescue" ;        
        String form = "Digital" ; 
                
        /*parametros via url*/
        int idInv = Integer.parseInt(request.getParameter("idInv"));
        int fkBka = Integer.parseInt(request.getParameter("fkBka"));
    
        /*parametros via url*/
		String openDate  = request.getParameter("open");	
		String type  = request.getParameter("type");
		String source = request.getParameter("broker"); 
		
		
				
		 /*parametros via form*/
		String rescueDate  = request.getParameter("rescue-date");	
	    String desc  = request.getParameter("desc");			
				
		String rescue =  request.getParameter("rescue");		
		BigDecimal rescueValue = new BigDecimal(rescue);	
		
		
		BigDecimal balance = actm.getAmount().add(rescueValue);		
		
		
		iofValue = invs.getIofValue(openDate, rescueDate);	
					
		
		 if( type != "Lci" || type != "Lca" ) {	
			 
			    irTx = invs.getIrTx(openDate, rescueDate);		
			    irValue = invs.getIrValue(irTx, rescueValue);			    
			    
			    liquid = rescueValue.subtract(irValue);	
			    
		 }else {			 
				 liquid = rescueValue;
		 }
			 
		
		 //balance = amount.add(liquid);		
		 balance = actm.getAmount().add(liquid);		
									
			
		 System.out.println("function insertRescue "+
				 rescueDate+" "+
				 rescueValue+" "+
				 iofValue+" "+
				 irTx+" "+
				 irValue+" "+
				 desc+" "+
				 idInv
			    );
		
		 this.insertTransactions(move, rescueDate, typeTrs, source, form, desc, liquid, balance, fkBka);
		 		 
		 /*
		 resm.setDate(rescueDate);
		 resm.setIrValue(rescueValue);	
		 resm.setIof(rescueValue);		 
		 resm.setIrTx(ir_tx);
		 resm.setIrValue(ir_value);
		 resm.setDesc(desc);
		 resm.setFkivt(idInv);
		 		 
		 if(resd.insert(resm)) {			 
			 this.insertTransactions(move, rescueDate , typeTrs, source, form, desc, total, balance, fkBka);
		 }else {
			 
		 }
		 */	 
	      
	}
	
	
	
	
	
	
	private void insertTransactions(
			String move,
			String trsDate,
			String type,
			String source,
			String form,
			String desc,
			BigDecimal valueTrs,
			BigDecimal balance,
			int fkBka
			) {
		
		/*
         trsm.setMov(move);
         trsm.setDate(trsDate);
         trsm.setType(type);
         trsm.setSource(source);
         trsm.setForm(form);
         trsm.setDesc(desc);
         trsm.setValue(valueTrs);
         trsm.setBalance(balance);
         trsm.setFkbka(fkBka);  
         
         trsd.insert(trsm);
        */
         
         System.out.println(
        		 "insertTransactions "+
                  move+" "+
        		  trsDate+" "+
                  type+" "+
        		  source+" "+
                  form+" "+
        		  desc+" "+
                  valueTrs+" "+
        		  balance+" "+
                  fkBka);		
	}
	
	
	
	
	
	
	
	
	
	protected void newTransactions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		
	     int idAcc = Integer.parseInt(request.getParameter("idAcc"));		
		 
		 //request.setAttribute("fkbka", idAcc);     	   
		       
		 /*parametros via js*/
		 BigDecimal amountAcc = new BigDecimal(request.getParameter("amount"));
		// actm.setAmount(amountAct);
		 
		 String number = request.getParameter("number");
	     String type = request.getParameter("type");	
	     
	     
	     String bank = request.getParameter("bank");
	     
	     
	    int fkBank = Integer.parseInt(request.getParameter("fkBnk"));	        
	    
	    // dados conta selecionada 
		request.setAttribute("trsType" , action);
		request.setAttribute("idAcc" , idAcc);
		request.setAttribute("bank" , bank);
		request.setAttribute("numberAcc" , number);
		request.setAttribute("typeAcc" , type);
		request.setAttribute("amountAcc" , amountAcc);
		
			
		ArrayList<AccountsModel> list = new ArrayList<>();
		         
         // envia list vazio	no jsp
		request.setAttribute("accounts" , list);		
		
		
		if ("/trf".equals(action) && "Investimentos".equals(type)) {
						
			// System.out.println("transf da conta invest para conta digital, seta os dados da conta digital ");
			 
			 autoRescue = true;
			 			 
			 // setar esses atributos no jsp //
			 actm.setId(idAcc);
			 actm.setFkbnk(fkBank);				 
			 actd.uniqueAccount(actm);
			 
			 String numberForward = actm.getNumber();
			 String typeForward = actm.getType();
			 
			 int idAcForward = actm.getId();
			 
			 
			 request.setAttribute("idAcForward", idAcForward );
			 request.setAttribute("numberForward", numberForward);
			 request.setAttribute("typeForward", typeForward );
			 
			// request.setAttribute("amountForward", actm.getAmount());
			// request.setAttribute("fkForward", actm.getFkbnk());
				
			 /*
			 System.out.println( "Id "+actm.getId()+
			                    " Number "+actm.getNumber()+
			                    " Type "+actm.getType()+
			                    " amount "+actm.getAmount()+
			                    " fk "+actm.getFkbnk()				         
			 );			 
			 */			 
		}
		
					
		
		
		if ("/trf".equals(action) && !"Investimentos".equals(type)) {
			
			//System.out.println("transf de outras contas, seta os dados de todas as contas");
			
			   actm.setId(idAcc);			
			   list = actd.accountsAll(actm);			   
						
				// envia list accounts no jsp
				request.setAttribute("accounts" , list);		
			
				/*
				for(int i =0; i<list.size(); i++) {					
					System.out.println(list.get(i).getId());
					System.out.println(list.get(i).getBank());
					System.out.println(list.get(i).getType());
					System.out.println(list.get(i).getNumber());
				}
				*/			
				
			}
		  	
		
		
          if ("/pay".equals(action)) {			
			 pay = true;			
           }
		
         
         
		
		/*
		
		if(action.equals("/trf")) {			
			
		 System.out.println(" type "+type+" idAcc "+idAcc+" fk "+fkBank);
			
			
		   if( type.equals("Investimentos")) {
			   
			   ArrayList<AccountsModel> list = new ArrayList<>();
	           
	            // envia list vazio	no jsp
				request.setAttribute("accounts" , list);
				
			   // setar esses atributos no jsp //
				 actm.setId(idAcc);
				 actm.setFkbnk(fkBank);				 
				 actd.uniqueAccount(actm);
				 
				 System.out.println( "Id "+actm.getId()+
				                    " Number "+actm.getNumber()+
				                    " Type "+actm.getType()+
				                    " amount "+actm.getAmount()+
				                    " fk "+actm.getFkbnk()				         
						 );		
				 
			    }else {
			    					
			    
			actm.setId(idAcc);			
			ArrayList<AccountsModel> list = actd.accountsAll(actm);	
			
			// envia list accounts no jsp
			request.setAttribute("accounts" , list);			
		
			//
			for(int i =0; i<list.size(); i++) {					
				System.out.println(list.get(i).getId());
				System.out.println(list.get(i).getBank());
				System.out.println(list.get(i).getType());
				System.out.println(list.get(i).getNumber());
			}		
		    //		
		    }	   
		   
						
		}else {		
			
           ArrayList<AccountsModel> list = new ArrayList<>();
           
            // envia list vazio	no jsp
			request.setAttribute("accounts" , list);
			
		}
	
	   */
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("transactions/cad_trs.jsp");
		rd.forward(request, response);
		
		// response.sendRedirect("transactions/cad_trs.jsp");		 
		//  System.out.println("insertTransactions "+idAcc+" "+amountAcc+" "+number+" "+type);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	protected void createTransactions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		//conta origem content-account
		String trsType = request.getParameter("trsType");
		int idAcc = Integer.parseInt(request.getParameter("idAcc"));
		String bank = request.getParameter("bank");
		String typeAcc = request.getParameter("typeAcc");
		String numberAcc = request.getParameter("numberAcc");
		BigDecimal amountAcc = new BigDecimal(request.getParameter("amountAcc"));
		
		/*
		System.out.println("conta origem content-account  "+
				" \n trsType = "+trsType+
				" \n idAcc = "+idAcc+
				" \n typeAcc = "+typeAcc+
				" \n numberAcc = "+numberAcc+
				" \n amountAcc = "+amountAcc		
				);	
		*/
		
					
				//container-way
				String form = request.getParameter("form");					
				
				//container-form-trs
				String trsDate = request.getParameter("trsDate");
				String strValue = request.getParameter("trsValue");							
				//container-source-bg
				String desc = request.getParameter("desc");			
				
				//content-option
				String typeTrf = request.getParameter("type");
				String trf = request.getParameter("trf");				
					
				//content-source-trf
				String source = request.getParameter("source");			
					
		 if( autoRescue ){
			 
			//container-account-forward
				String typeForward = request.getParameter("typeForward");
				String numberForward = request.getParameter("numberForward");
				int idAcForward = Integer.parseInt(request.getParameter("idAcForward"));
				
			 
			 System.out.println(
					 "setar dados de saida da conta origem "+typeAcc+"\n "+
					 " move = out \n "+
			         " data"+trsDate+" \n "+
			         " type = rescue \n "+
					 " source Rico "+typeAcc+" \n "+
			         " form "+form+" \n "+
					 " desc "+desc+" \n "+
			         " value "+strValue+" \n "+
					 " fk "+idAcc+" \n ");				 
			 
			 System.out.println(
					 "setar dados de entrada da conta destino "+typeForward+"\n "+
					 " move = "+trf+" \n "+
			         " data"+trsDate+" \n "+
			         " type = rescue \n "+
					 " source Rico "+typeForward+" \n "+
			         " form "+form+" \n "+
					 " desc "+desc+" \n "+
			         " value "+strValue+" \n "+
					 " fk "+idAcForward);				 
		  };			 
			  
		  
	    if( autoRescue == false){				 
				 
		   if("out".equals(trf)) {	
		 			
			 if ("pessoal".equals(source)) {	
				 
			  //content-accounts
				String idAccountSelected = request.getParameter("accountSelect");
				
				String bankSelected = request.getParameter("selected-bank");			
												
				System.out.println(
						 "setar dados de saida da conta origem "+typeAcc+" \n "+
						 " move = "+trf+" \n "+
				         " data = "+trsDate+" \n "+
				         " type = "+typeTrf+" \n "+
				         " source "+bankSelected+" \n "+
				         " form "+form+" \n "+
						 " desc "+desc+" \n "+
				         " value "+strValue+" \n "+
						 " fk "+idAcc+" \n ");	
				
				 System.out.println(
						 "setar dados de entrada da conta destino "+bankSelected+"\n "+
						 " move = "+trf+" \n "+
				         " data"+trsDate+" \n "+
				         " type = "+typeTrf+" \n "+
						 " source "+bank+" \n "+
				         " form "+form+" \n "+
						 " desc "+desc+" \n "+
				         " value "+strValue+" \n "+
						 " fk "+idAccountSelected);				 
				 
				   }else{				 
					   String who = request.getParameter("who");
					   System.out.println(
								 "setar dados de saida da conta transf para terceiros \n "+
								 " move = "+trf+" \n "+
						         " data = "+trsDate+" \n "+
						         " type = "+typeTrf+" \n "+				         
						         " source "+who+" \n "+
						         " form "+form+" \n "+
								 " desc "+desc+" \n "+
						         " value "+strValue+" \n "+
								 " fk "+idAcc+" \n ");
				        }			 
				 				 
		        }else {		        	
		        	String sourceIn = request.getParameter("sourceIn");		        	
		        	System.out.println(
								 "setar dados de entrada na conta transf de terceiros \\n "+
								 " move = "+trf+" \n "+
						         " data ="+trsDate+" \n "+
						         " type ="+typeTrf+" \n "+						
						         " origem ="+sourceIn+" \n "+
						         " form "+form+" \n "+
								 " desc "+desc+" \n "+
						         " value "+strValue+" \n "+
								 " fk "+idAcc);			        	
		            }		    
		  }
		  
		  		  
		  
		  
		  if ( pay  ) {	
			  System.out.println(
						 "setar dados de saida da conta pagamentos \n "+
						 " move = out  \n "+
				         " data = "+trsDate+" \n "+
				         " type = "+typeTrf+" \n "+				         
				         " form "+form+" \n "+
						 " desc "+desc+" \n "+
				         " value "+strValue+" \n "+
						 " fk "+idAcc+" \n ");
		        }
		
		
		
		
		  
		  
		
		/*	
			
		BigDecimal amountAcc = new BigDecimal(request.getParameter("amountAcc"));
		
		String strValue = request.getParameter("trsValue");		
		BigDecimal trsValue = new BigDecimal(strValue);		
		//BigDecimal trsValue = new BigDecimal(request.getParameter("trsValue"));		
		
		BigDecimal balanceOut = amountAcc.subtract(trsValue);
				
		int idAcc = Integer.parseInt(request.getParameter("idAcc"));
		
		
		// destino //
		
		String typeMove = request.getParameter("type-move");
		
		String bankSource = request.getParameter("bank-source");		
		
		String strAmountSelected = request.getParameter("selected-amount");		
		BigDecimal amountSelected = new BigDecimal(strAmountSelected);
		BigDecimal balanceIn  = amountSelected.add(trsValue);
		
		String idAccountSelected = request.getParameter("accountSelect");
		
		//String typeAcc = request.getParameter("typeAcc");
		//String number = request.getParameter("numberAcc");
		
		//String typeSelected = request.getParameter("selected-type");
		
		//String numberSelected = request.getParameter("selected-number");
		
		System.out.println("createTransactions salvar no banco de dados saida "+
		" \n trf = "+trf+
		" \n trsDate = "+trsDate+
		" \n typeTrf = "+typeTrf+
		" \n source = "+source+
		" \n bankSelected = "+bankSelected+
		" \n form = "+form+
		" \n desc = "+desc+
		" \n trsValue = "+trsValue+
		" \n balanceOut = "+balanceOut+
		" \n idAcc = "+ idAcc+		
		" \n salvar no banco de dados entrada "+
		" \n typeMove = "+typeMove+
		" \n bankSource = "+bankSource+
		" \n balanceIn = "+balanceIn+
		" \n idAccountSelected = "+idAccountSelected		
		);	
		
		*/
		
		
		
	}
	
	
	
	
	
	
	
	
	protected void selectTransactions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int idAcc = Integer.parseInt(request.getParameter("idAcc"));
		
		   trsm.setFkbka(idAcc);
		   
		   ArrayList<TransactionsModel> report = trsd.report(trsm);
		   
		   for(int i=0; i<report.size(); i++) {
			   
			   System.out.println(report.get(i).getMov());
			   System.out.println(report.get(i).getDate());
			   System.out.println(report.get(i).getType());
			   System.out.println(report.get(i).getSource());
			   System.out.println(report.get(i).getForm());
			   System.out.println(report.get(i).getDesc());
			   System.out.println(report.get(i).getAllin());
			   System.out.println(report.get(i).getAllout());
			   System.out.println(report.get(i).getTotalDay());
		   }
		
		   
	}
	
	
	
	
	
	
	
	
	
	

}
