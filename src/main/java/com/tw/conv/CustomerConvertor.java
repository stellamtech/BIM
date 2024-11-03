package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.CustomerWiseDto;
import com.tw.entity.Sale;

public class CustomerConvertor implements Function<Sale, CustomerWiseDto> {


	@Override
	public CustomerWiseDto apply(Sale r) {
		CustomerWiseDto t = new CustomerWiseDto();

		t.setCustomerName(r.getCustomerName());
		t.setCustomerMobile(r.getCustomerMobile());
		t.setSaleNo(r.getSaleno());
		t.setSaleDate(r.getSaledate());
		t.setPaidAmount(r.getPaidAmt());
		t.setRemainingAmount(r.getRemainingAmt());
		t.setNetAmount(r.getNetAmt());
		t.setStatus(r.getStatus());
		t.setPaidflag(r.getPaidflag());
		t.setStockflag(r.getStockflag());
		return t;
	}

}
