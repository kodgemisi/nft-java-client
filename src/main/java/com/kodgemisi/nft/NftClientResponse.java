package com.kodgemisi.nft;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created on October, 2017
 *
 * @author destans
 */
@Getter
@Setter
@Slf4j
public class NftClientResponse<T> {

	/**
	 * <p>HTTP status code.</p>
	 * <p>When it's {@code -1} it means an {@link java.io.IOException} is occurred.</p>
	 */
	private int status;

	private String error;

	private List<Map<String, Object>> errors;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime timestamp;

	private String message;

	private String path;

	private String traceId;

	private T payload;


	private NftClientResponse() {
	}

	public static <T> NftClientResponse<T> of(Response<T> response) {

		if (log.isDebugEnabled()) {
			log.debug("Returning response {}", response);
		}

		final NftClientResponse<T> nftClientResponse = new NftClientResponse<>();
		nftClientResponse.setStatus(response.code());

		if (response.isSuccessful()) {
			final T restResponse = response.body();
			nftClientResponse.setPayload(restResponse);
		}
		else {
			try {
				return NtfClient.mapper.readValue(response.errorBody().string(), NftClientResponse.class);
			}
			catch (IOException e) {
				log.error(e.getMessage(), e);
				nftClientResponse.setError(e.getMessage());
			}
		}

		return nftClientResponse;
	}

	public static <T> NftClientResponse<T> of(IOException e) {
		log.error(e.getMessage(), e);
		NftClientResponse<T> nftClientResponse = new NftClientResponse<>();
		nftClientResponse.setStatus(-1);
		nftClientResponse.setError(e.getMessage());

		return nftClientResponse;
	}

	public boolean isSuccess() {
		return (getStatus() >= 200 && getStatus() < 300) && error == null;
	}

	/**
	 * Returning true doesn't guarantee that there is any error message.
	 *
	 * @return true if Http status is other than 2xx. Note that event if it's true there might be no error messages.
	 */
	public boolean hasError() {
		return (getStatus() < 200 && getStatus() >= 300);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("NftClientResponse{");
		sb.append("\nstatus=").append(status);
		sb.append("\nerror='").append(error).append('\'');
		sb.append("\nerrors=").append(errors);
		sb.append("\ntimestamp=").append(timestamp);
		sb.append("\nmessage='").append(message).append('\'');
		sb.append("\npath='").append(path).append('\'');
		sb.append("\ntraceId='").append(traceId).append('\'');
		sb.append("\npayload=").append(payload);
		sb.append('}');
		return sb.toString();
	}
}