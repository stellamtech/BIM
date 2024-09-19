package com.tw.commands.history;

import org.springframework.stereotype.Component;

import com.tw.dto.StockHistoryDto;

@Component
public class RemoteControlHistory {
	 
	 
	 public void addstock(ClientHistory commandBase,StockHistoryDto t) {
		 //  this.commandBase=commandBase;
		   commandBase.execute(t);
	 }
	
	 public void removestock(ClientHistory commandBase,StockHistoryDto t) {
		   //this.commandBase=commandBase;
		   commandBase.execute(t);
	 }
	 public void undo(){
		 //commandBase.undo();
    }
}
