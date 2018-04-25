package com.example;

import com.kodgemisi.nft.FraudCheckRequest;
import com.kodgemisi.nft.FraudCheckResponse;
import com.kodgemisi.nft.NftClientResponse;
import com.kodgemisi.nft.NtfClient;

public class ClientDemo {

	public static void main(String[] args) {
		NtfClient client = new NtfClient("Space App", "", "http://nft-web.test.kodgemisi.com:8081/api/v1/");

		final FraudCheckRequest fraudCheckRequest = FraudCheckRequest.builder()
				.trackToken("XQD87A")
				.addPaymentInfo("CC", "2321321")
				.build();

		NftClientResponse<FraudCheckResponse> response = client.check(fraudCheckRequest);

		System.out.println(response);

		FraudCheckResponse fraudCheckResponse = response.getPayload();
	}
}
