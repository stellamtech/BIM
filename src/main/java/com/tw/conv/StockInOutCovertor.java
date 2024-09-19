package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.StockInOutDto;
import com.tw.entity.StockInOut;

public class StockInOutCovertor implements Function<StockInOut,StockInOutDto>{

	@Override
	public StockInOutDto apply(StockInOut t) {
		StockInOutDto s = new StockInOutDto();
		s.setId(t.getId());
		s.setDate(t.getDate());
		s.setItemId(t.getItem().getId());
		s.setPurchasevalue(t.getPurchasevalue());
		s.setQty(t.getQty());
		s.setReason(t.getReason());
		s.setRefno(t.getRefno());
		s.setSalesvalue(t.getSalesvalue());
		s.setStockin(t.getStockin());
		s.setStockout(t.getStockout());
		s.setType(t.getType());
		s.setItemName(t.getItem().getItemName());
		s.setItemCode(t.getItem().getItemCode());
		return s;
	}


}
