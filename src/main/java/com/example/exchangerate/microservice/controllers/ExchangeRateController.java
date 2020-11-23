package com.example.exchangerate.microservice.controllers;

import com.example.exchangerate.microservice.services.ExchangeRateService;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService service) {
        this.exchangeRateService = service;
    }

    @RequestMapping(value = "/exchangeRate", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<String> getExchangeRate(@RequestParam(name = "baseCurrency") String baseCurrency,
            @RequestParam(name = "targetCurrency") String targetCurrency) {

        JSONObject json = new JSONObject();
        json.put("value", exchangeRateService.getExchangeRate(baseCurrency, targetCurrency));

        return ResponseEntity.ok(json.toString());
    }
}
