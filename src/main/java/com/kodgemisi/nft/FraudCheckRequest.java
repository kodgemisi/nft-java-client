package com.kodgemisi.nft;

import lombok.*;

import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class FraudCheckRequest {

	/**
	 * Empty paymentInfo collection results in {@code 400 - Bad Request}
	 */
	private Collection<PaymentInfo> paymentInfo = new HashSet<>();

	/**
	 * An optional string given by you in the request. This string will appear in the response as verbatim. See API docs for further information.
	 */
	private String trackToken;

	public static FraudCheckRequest.FraudCheckRequestBuilder builder() {
		return new FraudCheckRequest.FraudCheckRequestBuilder();
	}

	public static class FraudCheckRequestBuilder {
		private Collection<PaymentInfo> paymentInfo = new HashSet<>();
		private String trackToken;

		FraudCheckRequestBuilder() {
		}

		/**
		 * You must add at least one PaymentInfo. Failing to do so results in {@code 400 - Bad Request}.
		 *
		 * @param type
		 * @param value
		 * @return
		 */
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