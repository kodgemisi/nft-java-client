package com.kodgemisi.nft;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class FraudCheckResponse {

	private String tid;

	private double score;

	private boolean abuse;

	private String trackToken;

	private Integer remainingQuota;

	private Long expirationTime;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FraudCheckResponse that = (FraudCheckResponse) o;
		return Objects.equals(tid, that.tid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tid);
	}
}