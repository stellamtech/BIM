package com.tw.commands.history;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dto.StockHistoryDto;
import com.tw.entity.Item;
import com.tw.entity.StockHistory;
import com.tw.entity.StockInOut;
import com.tw.repository.ItemRepository;
import com.tw.repository.StockHistoryRepository;
import com.tw.repository.StockInOutRepository;
import com.tw.utility.Constants;

@Component
@Transactional
public class StockInOutHistory implements ClientHistory {

	@Autowired
	private StockHistoryRepository stockHistoryRepository;

	@Autowired
	private ItemRepository itemrepo;

	@Autowired
	private StockInOutRepository stockinoutrepo;

	@Override
	public void execute(StockHistoryDto t) {

		StockInOut c = stockinoutrepo.getOne(t.getForengId());
		Optional<StockHistory> stockop;

		stockop = stockHistoryRepository.findOneBySioId(c.getId());
		if (stockop.isPresent()) {
			StockHistory h = stockHistoryRepository.findBySioId(c.getId());
			StockHistory stocedit = stockHistoryRepository.getOne(h.getId());
			Item i = itemrepo.getOne(c.getItem().getId());
			stocedit.setItem(i);
			stocedit.setQty(c.getQty());
			if (c.getType().equals(Constants.STOCKIN)) {
				stocedit.setType(Constants.STOCK_IN);
			} else {
				stocedit.setType(Constants.STOCK_OUT);
			}

			stocedit.setVoucherno("");
			stocedit.setSio(c);
			stocedit.setModified(Calendar.getInstance());
			stockHistoryRepository.save(stocedit);

		} else {
			StockHistory stock = new StockHistory();
			if (c.getItem().getId() != null) {
				Item i = itemrepo.getOne(c.getItem().getId());
				stock.setItem(i);
				stock.setQty(c.getQty());
				if (c.getType().equals(Constants.STOCKIN)) {
					stock.setType(Constants.STOCK_IN);
				} else {
					stock.setType(Constants.STOCK_OUT);
				}

				stock.setVoucherno("");
				stock.setSio(c);
				stock.setModified(Calendar.getInstance());
				stock.setCreated(Calendar.getInstance());
				stockHistoryRepository.save(stock);
			}

		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
