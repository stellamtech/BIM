package com.tw.commands;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dto.StockDto;
import com.tw.entity.Stock;
import com.tw.repository.SaleItemRepository;
import com.tw.repository.StockRepository;

@Component
@Transactional
public class SaleStock implements Client{

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private SaleItemRepository saleItemRepository;
	
	@Override
	public void execute(List<StockDto> t) {
		
		for (StockDto s : t) {
			Optional<Stock> stockop;
			stockop=stockRepository.findOneByItemId(s.getItem().getId());
			if (stockop.isPresent()) {
				 Stock stock=stockRepository.findByItemId(s.getItem().getId());
				 Double grnstock=stock.getGrnqty();
				 Double openingStock =stock.getOpeningqty();
				 Double creditQuantity=stock.getCreditqty();
				 Double stockIn =stock.getStockin();
				 Double finalPurchase=grnstock +  openingStock + creditQuantity+stockIn;
				
				 double saleQty =saleItemRepository.getSumByItemId(s.getItem().getId());
				 
				 Double debitQty =stock.getDebitqty();
				 Double stockOut =stock.getStockout();
				 Double finalSale =saleQty + debitQty+stockOut;
				 
				 Double finalStock =finalPurchase - finalSale;
				 stock.setStockqty(finalStock);
				 stock.setSaleqty(saleQty);
				 stock.setModified(Calendar.getInstance());
				 stockRepository.save(stock);
				 
			}
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
