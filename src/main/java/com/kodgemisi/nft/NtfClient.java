package com.kodgemisi.nft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.function.Function;

/**
 * Created on April, 2018
 *
 * @author destan
 */
public class NtfClient {

	private final NftApiEndpoints api;

	protected static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.registerModule(new JavaTimeModule());
	}

	public NtfClient(String appName, String apiKey, String apiUrl) {

		if(appName == null || appName.trim().isEmpty()) {
			throw new IllegalArgumentException("appName is required!");
		}

		if(apiKey == null || apiKey.trim().isEmpty()) {
			throw new IllegalArgumentException("apiKey is required!");
		}

		if(apiUrl == null || apiUrl.trim().isEmpty()) {
			throw new IllegalArgumentException("apiUrl is required!");
		}

		final Function<okhttp3.Response, Integer> responseCount = (okhttp3.Response response) -> {
			int result = 1;
			while ((response = response.priorResponse()) != null) {
				result++;
			}
			return result;
		};

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.authenticator((route, response) -> {
			//Thanks https://stackoverflow.com/a/34819354/878361
			if (responseCount.apply(response) >= 3) {
				return null; // If we've failed 3 times, give up.
			}

			String credential = Credentials.basic(appName, apiKey);
			return response.request().newBuilder().header("Authorization", credential).build();
		});

		final OkHttpClient client = httpClient.build();

		final Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(JacksonConverterFactory.create(mapper))
				.baseUrl(apiUrl.trim())
				.client(client)
				.build();

		api = retrofit.create(NftApiEndpoints.class);
	}

	public NftClientResponse<FraudCheckResponse> check(FraudCheckRequest fraudCheckRequest) {
		try {
			final Response<FraudCheckResponse> response = api.check(fraudCheckRequest).execute();
			return NftClientResponse.of(response);
		}
		catch (IOException e) {
			return NftClientResponse.of(e);
		}
	}

	public NftClientResponse<Void> reportFraud(String transactionId) {
		try {
			final Response<Void> response = api.reportFraud(new FraudReportRequest(transactionId)).execute();
			return NftClientResponse.of(response);
		}
		catch (IOException e) {
			return NftClientResponse.of(e);
		}
	}

	public NftClientResponse<Void> reportAbuse(String transactionId) {
		try {
			final Response<Void> response = api.reportAbuse(new FraudReportRequest(transactionId)).execute();
			return NftClientResponse.of(response);
		}
		catch (IOException e) {
			return NftClientResponse.of(e);
		}
	}

}