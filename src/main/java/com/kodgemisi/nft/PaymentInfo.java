package com.kodgemisi.nft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentInfo {

	private String type;

	private String value;

	@Override
	public int hashCode() {
		return type != null ? type.hashCode() : 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PaymentInfo that = (PaymentInfo) o;

		return type != null ? type.equals(that.type) : that.type == null;
	}
}