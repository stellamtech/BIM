package com.tw.commands.history;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dto.StockHistoryDto;
import com.tw.entity.Item;
import com.tw.entity.Sale;
import com.tw.entity.SaleItem;
import com.tw.entity.StockHistory;
import com.tw.repository.ItemRepository;
import com.tw.repository.SaleRepository;
import com.tw.repository.StockHistoryRepository;
import com.tw.utility.Constants;

@Component
@Transactional
public class SaleStockHistory implements ClientHistory {

	@Autowired
	private StockHistoryRepository stockHistoryRepository;

	@Autowired
	private ItemRepository itemrepo;

	@Autowired
	private SaleRepository saleRepository;

	@Override
	public void execute(StockHistoryDto t) {

		Sale sale = saleRepository.getOne(t.getForengId());
		Optional<StockHistory> stockop;
		//for (SaleItem s : sale.getSaleItem()) {
		for (int j = 0; j < sale.getSaleItem().size(); j++) {
				SaleItem s=sale.getSaleItem().get(j);
			stockop = stockHistoryRepository.findOneBySaleitemId(s.getId());
			if (stockop.isPresent()) {
				StockHistory h = stockHistoryRepository.findBySaleitemId(s.getId());
				StockHistory stocedit = stockHistoryRepository.getOne(h.getId());
				Item i = itemrepo.getOne(s.getItem().getId());
				stocedit.setItem(i);
				stocedit.setQty(s.getQty());
				stocedit.setType(Constants.SALE_STOCK);
				stocedit.setVoucherno(s.getSale().getSaleno());
				stocedit.setSaleitem(s);
				stocedit.setStatus(sale.getStatus());
				stocedit.setModified(Calendar.getInstance());
				if(sale.getCustomer()!=null) {
					stocedit.setCustomer(sale.getCustomer());
				}
				stockHistoryRepository.save(stocedit);

			} else {
				StockHistory stock = new StockHistory();
				if (s.getItem().getId() != null) {
					Item i = itemrepo.getOne(s.getItem().getId());
					stock.setItem(i);
					stock.setQty(s.getQty());
					stock.setType(Constants.SALE_STOCK);
					stock.setVoucherno(s.getSale().getSaleno());
					stock.setSaleitem(s);
					stock.setStatus(sale.getStatus());
					stock.setModified(Calendar.getInstance());
					stock.setCreated(Calendar.getInstance());
					if(sale.getCustomer()!=null) {
						stock.setCustomer(sale.getCustomer());
					}
					stockHistoryRepository.save(stock);
				}
			}

		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
