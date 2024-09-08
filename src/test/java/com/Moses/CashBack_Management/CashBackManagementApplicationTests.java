package com.Moses.CashBack_Management;

import com.Moses.CashBack_Management.dto.CashRewardDTO;
import com.Moses.CashBack_Management.entity.CashReward;
import com.Moses.CashBack_Management.entity.Customer;
import com.Moses.CashBack_Management.repository.CashBackHistoryRepository;
import com.Moses.CashBack_Management.repository.CashRewardRepository;
import com.Moses.CashBack_Management.service.CashRewardServiceImpl;
import com.Moses.CashBack_Management.exception.RewardNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CashBackManagementApplicationTests {

	@Mock
	private CashRewardRepository cashRewardRepository;

	@Mock
	private CashBackHistoryRepository cashBackHistoryRepository;

	@InjectMocks
	private CashRewardServiceImpl cashRewardServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetCashRewardByCustomerId_Success() {

		Long customerId = 1L;
		Customer customer = new Customer(customerId, "Sijibomi Aderinlewo", "aderinlewo@moses.com", "1234567890");
		CashReward cashReward = new CashReward(BigDecimal.valueOf(200.00), BigDecimal.valueOf(100.00), customer);
		when(cashRewardRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cashReward));


		CashRewardDTO result = cashRewardServiceImpl.getCashRewardByCustomerId(customerId);


		assertNotNull(result, "Cash reward should not be null");
		assertEquals(customerId, result.getCustomerId(), "Customer ID should match");
		assertEquals(BigDecimal.valueOf(200.00), result.getTotalCashBack(), "Total cashback should match");
		assertEquals(BigDecimal.valueOf(100.00), result.getCurrentBalance(), "Current balance should match");

		// Verify that the repository method was called exactly once
		verify(cashRewardRepository, times(1)).findByCustomerId(customerId);
	}

	@Test
	public void testGetCashRewardByCustomerId_NotFound() {

		Long customerId = 1L;
		when(cashRewardRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());


		RewardNotFoundException exception = assertThrows(RewardNotFoundException.class, () -> {
			cashRewardServiceImpl.getCashRewardByCustomerId(customerId);
		});

		assertEquals("Np reward found for" + customerId, exception.getMessage());


		verify(cashRewardRepository, times(1)).findByCustomerId(customerId);
	}

	@Test
	void contextLoads() {
	}
}
