package com.anymindassignment.controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.anymindassignment.model.WalletRecord;
import com.anymindassignment.resource.WalletController;
import com.anymindassignment.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class WalletControllerTest {

	@Mock
	private WalletService walletService;

	@InjectMocks
	private WalletController walletController;

	private MockMvc mockMvc;

	@Mock
	ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(walletController).setControllerAdvice(new Exception()).build();
	}

	@Test
	public void testSaveRecord() throws Exception {
		WalletRecord walletRecord = new WalletRecord();
		walletRecord.setAmount(BigDecimal.TEN);
		walletRecord.setDateTime(LocalDateTime.now());

		mockMvc.perform(post("/wallet/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(walletRecord))).andExpect(status().isOk());

		verify(walletService).saveRecord(walletRecord);
	}

	@Test
	public void testSaveRecordInvalidAmount() throws Exception {
		WalletRecord walletRecord = new WalletRecord();
		walletRecord.setAmount(BigDecimal.valueOf(-1));
		walletRecord.setDateTime(LocalDateTime.now());

		mockMvc.perform(post("/wallet/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(walletRecord))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Invalid amount"));

		verify(walletService, never()).saveRecord(walletRecord);
	}

}
