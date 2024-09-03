package com.tw.commands;

import java.util.List;

import com.tw.dto.StockDto;

public interface Client {
	void execute(List<StockDto> t);
    void undo();
}
