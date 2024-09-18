package com.tw.commands.history;

import com.tw.dto.StockHistoryDto;

public interface ClientHistory {
	void execute(StockHistoryDto t);
    void undo();
}
