package com.example.exchangerate.microservice.controllers;

import com.example.exchangerate.generated.api.ExchangerateApi;
import com.example.exchangerate.generated.dto.InlineResponse200Dto;
import com.example.exchangerate.microservice.mappers.ExchangeRateResponseMapper;
import com.example.exchangerate.microservice.services.ExchangeRateService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController implements ExchangerateApi {

    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateResponseMapper exchangeRateResponseMapper;

    public ExchangeRateController(ExchangeRateService service, ExchangeRateResponseMapper exchangeRateResponseMapper) {
        this.exchangeRateService = service;
        this.exchangeRateResponseMapper = exchangeRateResponseMapper;
    }

    @Override
    @RequestMapping(value = "/exchangeRate", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<InlineResponse200Dto> getExchangeRate(@RequestParam(name = "baseCurrency") String baseCurrency,
            @RequestParam(name = "targetCurrency") String targetCurrency) {

        Double exchangeRate = exchangeRateService.getExchangeRate(baseCurrency, targetCurrency);

        return ResponseEntity.ok(exchangeRateResponseMapper.toDto(exchangeRate));
    }
}
