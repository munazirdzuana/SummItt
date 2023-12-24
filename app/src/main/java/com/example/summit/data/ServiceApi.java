package com.example.summit.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {
    @Headers({
            "Authorization: Bearer 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",
            "Content-Type: application/json"
    })
    @POST("models/csebuetnlp/mT5_multilingual_XLSum")
    Call<List<Res>> query(
            @Body Req payload
    );
}

