package com.example;

import com.kodgemisi.nft.FraudCheckRequest;
import com.kodgemisi.nft.FraudCheckResponse;
import com.kodgemisi.nft.NftClientResponse;
import com.kodgemisi.nft.NtfClient;

public class ClientDemo {

	/**
	 * Note that when running this method you should uncomment {@code slf4j-api} in {@code pom.xml}
	 * otherwise {@code java.lang.NoClassDefFoundError: org/slf4j/LoggerFactory} exception is thrown.
	 */
	public static void main(String[] args) {

		NtfClient client = new NtfClient("Space App", "", "http://nft-web.test.kodgemisi.com:8081/api/v1/");

		final FraudCheckRequest fraudCheckRequest = FraudCheckRequest.builder().trackToken("XQD87A").addPaymentInfo("CC", "2321321").build();

		NftClientResponse<FraudCheckResponse> response = client.check(fraudCheckRequest);

		System.out.println(response);

		FraudCheckResponse fraudCheckResponse = response.getPayload();

		System.out.println(fraudCheckResponse);
	}
}
