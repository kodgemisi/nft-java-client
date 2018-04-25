package com.kodgemisi.nft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FraudCheckRequest {

	private Collection<PaymentInfo> paymentInfo = new HashSet<>();

	private String trackToken;

	public static FraudCheckRequest.FraudCheckRequestBuilder builder() {
		return new FraudCheckRequest.FraudCheckRequestBuilder();
	}

	public static class FraudCheckRequestBuilder {
		private Collection<PaymentInfo> paymentInfo = new HashSet<>();
		private String trackToken;

		FraudCheckRequestBuilder() {
		}

		public FraudCheckRequest.FraudCheckRequestBuilder addPaymentInfo(String type, String value) {
			this.paymentInfo.add(new PaymentInfo(type, value));
			return this;
		}

		public FraudCheckRequest.FraudCheckRequestBuilder trackToken(String trackToken) {
			this.trackToken = trackToken;
			return this;
		}

		public FraudCheckRequest build() {
			return new FraudCheckRequest(this.paymentInfo, this.trackToken);
		}

	}

}