package com.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Account;
import com.bank.entity.User;
import com.bank.repo.AccountRepo;
import com.bank.repo.UserRepo;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepo ar;
	
	@Autowired
	private UserRepo ur;



	@Override
	public Account deleteAccount(Long accountId) throws Exception {
		Optional<Account> ac=ar.findById(accountId);
		if(ac.isEmpty())
			throw new Exception("account not found with id "+accountId);
		
		ar.delete(ac.get());
		return ac.get();
	}

	
	@Override
	public User addUserToAccount(Long accountId, User user) throws Exception {
		Optional<Account> ac=ar.findById(accountId);
		if(ac.isEmpty())
			throw new Exception("account not found with id "+accountId);
		
		Account account=ac.get();
		
		user.setAccount(account);
		
		return ur.save(user);
		
	}


	@Override
	public Account createAccount(Account account) throws Exception {

		Account ac=ar.save(account);
		
		return ac;
	}

	@Override
	public String removeUserFromAccount(Long accountId, Long userId) throws Exception {
		System.out.println("hello");
		Optional<Account> ac=ar.findById(accountId);
		if(ac.isEmpty())
			throw new Exception("account not found");
		
		Account account=ac.get();
		boolean isRemoved=account.getUsers().removeIf(u-> u.getUserId()==userId);
		
		if(!isRemoved)
			throw new Exception("user not linked with account");
		
		User user=ur.findById(userId).get();
		
		user.setAccount(null);
		
		ur.save(user);
		
		ar.save(account);
		
		
		return "user id "+userId+" removed from account "+accountId;
		
		
	}

}
