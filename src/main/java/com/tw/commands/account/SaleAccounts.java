package com.tw.commands.account;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dto.AccountHistoryDto;
import com.tw.entity.AccountHistory;
import com.tw.entity.Sale;
import com.tw.repository.AccountHistoryRepository;
import com.tw.repository.SaleRepository;
import com.tw.utility.Constants;

@Component
@Transactional
public class SaleAccounts implements ClientAccounts {

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private AccountHistoryRepository accountHistoryRepository;

	@Override
	public void execute(AccountHistoryDto t) {

		@SuppressWarnings("deprecation")
		Sale p = saleRepository.getOne(t.getSaleId());

		AccountHistory a = new AccountHistory();
		a.setAmount(p.getNetAmt());
		a.setSale(p);
		a.setVoucherno(t.getVoucherno());
		a.setStatus(p.getStatus());
		a.setModified(Calendar.getInstance());
		a.setCreated(Calendar.getInstance());
		a.setNetAmt(t.getNetAmt());
		a.setPaidAmt(t.getPaidAmt());
		a.setRemainingAmt(t.getRemainingAmt());
		if (p.getCustomer() != null) {
			if (p.getCustomer().getId() != null) {
				a.setCustomer(p.getCustomer());
			}

		}
		a.setType(Constants.SALE_INVOICE);
		accountHistoryRepository.save(a);
	}

	@Override
	public void undo() {

	}

}
