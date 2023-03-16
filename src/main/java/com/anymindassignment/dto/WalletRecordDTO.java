package com.anymindassignment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletRecordDTO {

	@NotNull
	private LocalDateTime dateTime;

	@NotNull
	@DecimalMin(value = "0.01", inclusive = true)
	private BigDecimal amount;

	@NotBlank
	private String currency;

}
