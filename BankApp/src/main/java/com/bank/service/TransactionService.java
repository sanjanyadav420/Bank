package com.bank.service;

import com.bank.entity.User;

public interface TransactionService {
	
	public User creditAccount(Long userId, Long amount) throws Exception;

	public User debitAccount(Long userId, Long amount) throws Exception;
	
	
        
	
	
}
