package me.zw.workbook.service;

import me.zw.workbook.domain.Account;
import me.zw.workbook.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account createAccount(String username, String password) {
        Account account = new Account();

        account.setAccountName(username);
        account.setPassword(passwordEncoder.encode(password));

        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String accountname) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByAccountName(accountname);

        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(accountname));

        return new User(account.getAccountName(), account.getPassword(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
