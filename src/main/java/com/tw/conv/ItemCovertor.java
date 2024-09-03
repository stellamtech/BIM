package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.ItemDto;
import com.tw.entity.Item;

public class ItemCovertor implements Function<Item, ItemDto> {

	@Override
	public ItemDto apply(Item t) {
		ItemDto items = new ItemDto();
		items.setId(t.getId());
		// items.setInventoryAccount(t.getInventoryAccount());
		items.setItemName(t.getItemName());

		items.setItemCode(t.getItemCode());

		return items;
	}

}
