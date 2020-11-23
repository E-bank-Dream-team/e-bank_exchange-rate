import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description("""
		Represents a successful scenario to get exchange rate
		
		```
		given:
			/exchangeRate endpoint
		when:
			make GET request
		then:
			we'll get exchange rate calculation
		```
		
		""")
	request {
		method('GET')
		url( "/exchangeRate" ){
			queryParameters {
					def CURRENCY_CODE = '[A-Z]{3}'
					parameter 'baseCurrency': value(consumer(matching(CURRENCY_CODE)), producer('EUR'))
					parameter 'targetCurrency': value(consumer(matching(CURRENCY_CODE)), producer('GBP'))
				}
		}
		headers {
			contentType(applicationJson())
		}
	}
	response {
		status 200
		headers {
			contentType(applicationJson())
		}
		body([
			value      : 0.90872
		])
		bodyMatchers {
			jsonPath('value', byRegex(aDouble()).asDouble())
		}
	}
}
