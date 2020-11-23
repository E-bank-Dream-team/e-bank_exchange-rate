package com.example.exchangerate.microservice.api;

import static org.mockito.BDDMockito.given;

import java.util.Map;

import com.example.exchangerate.microservice.controllers.ExchangeRateController;
import com.example.exchangerate.microservice.mappers.ExchangeRateResponseMapper;
import com.example.exchangerate.microservice.mappers.ExchangeRateResponseMapperImpl;
import com.example.exchangerate.microservice.services.ExchangeRateService;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(MockitoJUnitRunner.class)
public abstract class RestBase {

    @Mock
    ExchangeRateService exchangeRateService;

    @Spy
    ExchangeRateResponseMapper exchangeRateResponseMapper = new ExchangeRateResponseMapperImpl();

    @InjectMocks
    ExchangeRateController exchangeRateController;

    @Before
    public void setup() {
        // Mock Exchange Rate service
        given(exchangeRateService.getExchangeRate("EUR", "GBP")).willReturn(getExchangeRate("EUR", "GBP"));

        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders.standaloneSetup(exchangeRateController));
    }

    private Double getExchangeRate(String baseCurrency, String targetCurrency) {
        final Map<String, Double> exchangeRatesToEUR = Map.of("EUR", 1.0, "USD", 1.18550, "GBP", 0.90872, "CHF",
                1.07361);

        Double targetRate = exchangeRatesToEUR.get(targetCurrency);
        Double baseRate = exchangeRatesToEUR.get(baseCurrency);
        if (targetRate == null || baseRate == null) {
            throw new IllegalArgumentException("Unknown currency: " + baseCurrency + " or " + targetCurrency);
        }
        return targetRate / baseRate;
    }

}
