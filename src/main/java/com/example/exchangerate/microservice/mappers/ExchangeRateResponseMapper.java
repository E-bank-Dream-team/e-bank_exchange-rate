package com.example.exchangerate.microservice.mappers;

import com.example.exchangerate.generated.dto.InlineResponse200Dto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateResponseMapper {

    InlineResponse200Dto toDto(Double value);

}
