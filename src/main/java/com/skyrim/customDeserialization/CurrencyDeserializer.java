package com.skyrim.customDeserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.util.Currency;

public class CurrencyDeserializer extends StdDeserializer<Currency> {

    public CurrencyDeserializer() {
        this(null);
    }

    public CurrencyDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Currency deserialize(final JsonParser jsonParser, final DeserializationContext ctxt) {
        try {
            return Currency.getInstance(jsonParser.getText());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
