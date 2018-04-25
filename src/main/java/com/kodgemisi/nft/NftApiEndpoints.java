package com.kodgemisi.nft;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NftApiEndpoints {

  @POST("check")
  Call<FraudCheckResponse> check(@Body FraudCheckRequest fraudCheckRequest);

  @POST("fraud-reports")
  Call<Void> reportFraud(@Body FraudReportRequest fraudReportRequest);

  @POST("abuse-reports")
  Call<Void> reportAbuse(@Body FraudReportRequest fraudReportRequest);
}