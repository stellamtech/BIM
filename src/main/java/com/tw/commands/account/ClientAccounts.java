package com.tw.commands.account;

import com.tw.dto.AccountHistoryDto;

public interface ClientAccounts {
	void execute(AccountHistoryDto t);
    void undo();
}
