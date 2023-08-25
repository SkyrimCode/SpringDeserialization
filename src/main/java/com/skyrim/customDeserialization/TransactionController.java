package com.skyrim.customDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.HashMap;

@RestController
public class TransactionController {

    private ObjectMapper objectMapper;
    private <T> T parse(ResponseEntity<String> response, Class<T> valueType) throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Currency.class, new CurrencyDeserializer());
        objectMapper.registerModules(module,new JavaTimeModule());
        return objectMapper.readValue(response.getBody(), valueType);
    }

    private ResponseEntity<String> getResponse() throws JsonProcessingException {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("amount","1000");
        map.put("currency","INR");
        map.put("date", String.valueOf(LocalDateTime.now()));
        String response =new ObjectMapper().writeValueAsString(map);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/getTransaction")
    public Transaction getTransaction() throws IOException {
        ResponseEntity<String> response = getResponse();
        return parse(response, Transaction.class);
    }
}
