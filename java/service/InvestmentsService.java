package service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;




public class InvestmentsService {
	
	
	
	public BigDecimal getIofValue(String openDateStr , String rescueDateStr ) {	
		
		BigDecimal iofValue = BigDecimal.ZERO;	
		
		LocalDate openDate = LocalDate.parse(openDateStr);
        LocalDate rescueDate = LocalDate.parse(rescueDateStr);   
        
        long days = ChronoUnit.DAYS.between(openDate, rescueDate);
        
        if(days < 30 ) {
        	
        }
		
		return iofValue;
	}
	
	
	
		
	public BigDecimal getIrTx(String openDateStr , String rescueDateStr ) {		
	
		BigDecimal irTx = BigDecimal.ZERO;	
		BigDecimal ir_180 = new BigDecimal(22.5); 
		BigDecimal ir_181_360 = new BigDecimal(20.0);		
		BigDecimal ir_361_720 = new BigDecimal(17.5);
		BigDecimal ir_721 = new BigDecimal(15);
				
		LocalDate openDate = LocalDate.parse(openDateStr);
        LocalDate rescueDate = LocalDate.parse(rescueDateStr);   		
		
        long days = ChronoUnit.DAYS.between(openDate, rescueDate);  
		
        if (days <= 180) { 
        	irTx = ir_180;        	
       	} else if (days > 180 && days <= 360) {
        	irTx = ir_181_360;
        } else if (days > 360 && days <= 760) { 
        	irTx = ir_361_720;
        } else { 
        	irTx = ir_721;
        }
		return irTx;
	}
	
	
	
	public BigDecimal getIrValue(BigDecimal rescueValue, BigDecimal irTx) {
		
		BigDecimal irValue = BigDecimal.ZERO;		
		irValue = rescueValue.multiply(irTx).divide(new BigDecimal(100));		
		return irValue;
	}
	
	
	

}
