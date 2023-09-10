package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.User;

public interface AccountService {

      
    public User addUserToAccount(Long accountId, User user)throws Exception;
    
    public String removeUserFromAccount(Long accountId, Long userId)throws Exception;
	
    public Account createAccount(Account account)throws Exception;
    
    public Account deleteAccount(Long accountId) throws Exception;
  

}
