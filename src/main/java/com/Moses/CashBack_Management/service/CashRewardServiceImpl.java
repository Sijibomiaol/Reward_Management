package com.Moses.CashBack_Management.service;


import com.Moses.CashBack_Management.dto.CashBackHistoryDTO;
import com.Moses.CashBack_Management.dto.CashRewardDTO;
import com.Moses.CashBack_Management.entity.CashBackHistory;
import com.Moses.CashBack_Management.entity.CashReward;
import com.Moses.CashBack_Management.exception.RewardNotFoundException;
import com.Moses.CashBack_Management.repository.CashBackHistoryRepository;
import com.Moses.CashBack_Management.repository.CashRewardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CashRewardServiceImpl implements CashRewardService {
    @Autowired
    private CashRewardRepository cashRewardRepository;
    @Autowired
    private CashBackHistoryRepository cashBackHistoryRepository;

    @Override
    public CashRewardDTO getCashRewardByCustomerId(Long customerId) {
        log.info("Fetching Customer Reward : {}", customerId);
        CashReward cashReward = cashRewardRepository.findByCustomerId(customerId).orElseThrow(() -> new RewardNotFoundException("Np reward found for" + customerId));
        return new CashRewardDTO(
                cashReward.getCustomer().getId(),
                cashReward.getTotalCashBack(),
                cashReward.getCurrentBalance()
        );


    }
    @Override
    public List<CashBackHistoryDTO> getCashRewardHistoryByCustomerId(Long customerId) {
        log.info("Fetching CashReward History : {}", customerId);
        List<CashBackHistory> cashBackHistories = cashBackHistoryRepository.getCashBackHistoriesByCustomerId(customerId);
        if (cashBackHistories.isEmpty()) {
            log.info("Error Retrieving Customer CashBack History : {}", customerId);
            throw new RewardNotFoundException("Cashback history not found for customer" +customerId);
        }
        return cashBackHistories.stream().map(history->new CashBackHistoryDTO(
            history.getId(),
            history.getDate(),
            history.getCashReward().getTransactionId(),
            history.getAmountEarned(),
            history.getDescription(),
            history.getCustomer().getId())).collect(Collectors.toList());
    }


}
