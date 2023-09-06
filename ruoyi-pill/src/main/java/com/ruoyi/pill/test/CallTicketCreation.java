package com.ruoyi.pill.test;

import com.dtflys.forest.annotation.*;
import com.dtflys.forest.http.ForestResponse;

import java.util.Map;

public interface CallTicketCreation {
    @Request(
            url = "https://api-eu.decathlon.net/smax/auth",
            type = "GET",
            headers = {
                    "x-api-key: 387de160-d170-45a2-95c3-6460d4717b45",
            }
    )
    ForestResponse<String> getTokens();

    @Request(
            url = "https://api-eu.decathlon.net/smax/Create",
            type = "POST",
            headers = {
                    "Content-Type: application/json",
                    "x-api-key: 387de160-d170-45a2-95c3-6460d4717b45",
                    "Cookie: LWSSO_COOKIE_KEY=${myToken}",
                    "Authorization:${myToken}",
            }
    )
    ForestResponse<String> sendTickets(@Var("myToken") String token,@JSONBody Map<String, Object> user);

    @Get(
            url = "https://api-eu.decathlon.net/smax/auth",
            headers = {
                    "x-api-key: 387de160-d170-45a2-95c3-6460d4717b45",
            }
    )
    ForestResponse<String> getTokens2();

    @Post(
            url = "https://api-eu.decathlon.net/smax/Create",
            headers = {
                    "Content-Type: application/json",
                    "x-api-key: 387de160-d170-45a2-95c3-6460d4717b45",
                    "Cookie: LWSSO_COOKIE_KEY=${myToken}",
                    "Authorization:${myToken}",
            }
    )
    ForestResponse<String> createTickets(@Var("myToken") String token,@JSONBody Map<String, Object> jsonBody);
}
