package com.tw.commands;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dto.StockDto;
import com.tw.entity.Item;
import com.tw.entity.Stock;
import com.tw.repository.ItemRepository;
import com.tw.repository.StockInOutRepository;
import com.tw.repository.StockRepository;

@Component
@Transactional
public class StockInOutCommand implements Client{

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockInOutRepository stockInOutRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
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
				 Double saleReturnqyt=stock.getSaleReturnqty();
				 double stockIn =stockInOutRepository.getSumofStockInByItemId(s.getItem().getId());
				 Double finalPurchase=grnstock +  openingStock + creditQuantity+stockIn + saleReturnqyt;
				
				 Double saleQty =stock.getSaleqty();
				 Double debitQty =stock.getDebitqty();
				 double stockOut =stockInOutRepository.getSumofStockOutByItemId(s.getItem().getId());
				 Double finalSale =saleQty + debitQty+stockOut;
				 
				 Double finalStock =finalPurchase - finalSale;
				 stock.setStockqty(finalStock);
				 stock.setStockin(stockIn);
				 stock.setStockout(stockOut);
				 
				 stock.setModified(Calendar.getInstance());
				 stockRepository.save(stock);
				 
			}else {
				Stock stock=new Stock();
				@SuppressWarnings("deprecation")
				Item it =itemRepository.getOne(s.getItem().getId());
				stock.setItem(it);
				if(s.getStockin()>0) {
					stock.setStockin(s.getStockin());
					stock.setStockqty(s.getStockin());
					stock.setStockout(0);
				}else{
					stock.setStockout(s.getStockout());
					stock.setStockqty(s.getStockout());
					stock.setStockin(0);
				}
				stock.setModified(Calendar.getInstance());
				stock.setCreated(Calendar.getInstance());
				stockRepository.save(stock);
			}
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
