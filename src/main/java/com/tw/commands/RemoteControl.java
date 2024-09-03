package com.tw.commands;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tw.dto.StockDto;

@Component
public class RemoteControl {
	 
	 
	 public void addstock(Client commandBase,List<StockDto> t) {
		 //  this.commandBase=commandBase;
		   commandBase.execute(t);
	 }
	
	 public void removestock(Client commandBase,List<StockDto> t) {
		   //this.commandBase=commandBase;
		   commandBase.execute(t);
	 }
	 public void undo(){
		 //commandBase.undo();
    }
}
