package com.Moses.CashBack_Management.service;

import com.Moses.CashBack_Management.dto.CashBackHistoryDTO;
import com.Moses.CashBack_Management.dto.CashRewardDTO;
import com.Moses.CashBack_Management.entity.CashBackHistory;
import com.Moses.CashBack_Management.entity.CashReward;

import java.util.List;

public interface CashRewardService {

    CashRewardDTO getCashRewardByCustomerId(Long customerId);

    List<CashBackHistoryDTO> getCashRewardHistoryByCustomerId(Long customerId);


}
