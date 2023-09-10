package com.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Account;
import com.bank.entity.User;
import com.bank.repo.UserRepo;


@Service
public class TransactionServiceImpl implements TransactionService{
	
    
	@Autowired
	private UserRepo userRepository;
	
	
	@Override
    public User debitAccount(Long userId, Long amount) throws Exception{
		
		Optional<User> userOpt=userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            throw new Exception("User not found");
        }
        
        User user=userOpt.get();
        
        Account account=user.getAccount();

        if (account.getBalance() - amount < 0) {
            throw new Exception("insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        
        return userRepository.save(user);
    }

    @Override
    public User creditAccount(Long userId, Long amount) throws Exception{
        
        Optional<User> userOpt=userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            throw new Exception("User not found");
        }
        
        User user=userOpt.get();
        
        Account account=user.getAccount();

        if (account.getBalance() + amount > 10000000) {
            throw new Exception("Credit amount too large");
        }
        account.setBalance(account.getBalance() + amount);
        
        return userRepository.save(user);
	}
	

}
