package com.tw.commands.account;

import org.springframework.stereotype.Component;

import com.tw.dto.AccountHistoryDto;

@Component
public class RemoteControlAccounts {

	public void addaccounts(ClientAccounts commandBase, AccountHistoryDto t) {
		// this.commandBase=commandBase;
		commandBase.execute(t);
	}

	public void removeaccounts(ClientAccounts commandBase, AccountHistoryDto t) {
		// this.commandBase=commandBase;
		commandBase.execute(t);
	}

	public void undo() {
		// commandBase.undo();
	}
}
