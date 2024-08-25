package az.edu.fintechpro.service;

import az.edu.fintechpro.dao.entity.AccountEntity;
import az.edu.fintechpro.dao.repository.AccountRepository;
import az.edu.fintechpro.exception.AccountNotFoundException;
import az.edu.fintechpro.mapper.AccountMapper;
import az.edu.fintechpro.model.dto.AccountDto;
import az.edu.fintechpro.model.enums.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public Long createAccount(AccountDto accountDto) {
        AccountEntity accountEntity = accountMapper.dtoToEntity(accountDto);

        accountEntity = accountRepository.save(accountEntity);

        log.info("Created account with ID: {}", accountEntity.getId());

        return accountEntity.getId();
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        log.info("Retrieved balance for account ID {}: {}", accountId, accountEntity.getBalance());
        return accountEntity.getBalance();
    }

    @Transactional(readOnly = true)
    public AccountDto getAccountById(Long accountId) {
        Optional<AccountEntity> accountOptional = accountRepository.findById(accountId);

        return accountOptional.map(accountMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional(readOnly = true)
    public AccountStatus getAccountStatus(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        log.info("Retrieved account status for account ID {}: {}", accountId, accountEntity.getAccountStatus());
        return accountEntity.getAccountStatus();
    }

    @Transactional
    public void blockAccount(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountEntity.setAccountStatus(AccountStatus.BLOCKED);
        accountRepository.save(accountEntity);

        log.info("Blocked account with ID: {}", accountId);
    }

    @Transactional
    public void activateAccount(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountEntity.setAccountStatus(AccountStatus.ACTIVE);
        accountRepository.save(accountEntity);

        log.info("Activated account with ID: {}", accountId);
    }


    @Transactional
    public void updateAccount(Long accountId, AccountDto accountDto) {

        AccountEntity existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        AccountEntity updatedAccount = accountMapper.dtoToEntity(accountDto);
        updatedAccount.setId(existingAccount.getId());

        accountRepository.save(updatedAccount);

        log.info("Updated account with ID: {}", updatedAccount.getId());
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);

        log.info("Deleted account with ID: {}", accountId);
    }

    @Transactional(readOnly = true)
    public List<AccountDto> getAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();

        return accountMapper.entityListToDtoList(accounts);
    }

    @Transactional
    public boolean transferMoney(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }


        AccountEntity fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Source account not found"));
        AccountEntity toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Destination account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds in source account");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return true;
    }

}

