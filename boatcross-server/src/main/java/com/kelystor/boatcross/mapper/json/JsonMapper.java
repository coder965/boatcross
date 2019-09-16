package com.kelystor.boatcross.mapper.json;

public interface JsonMapper {
    <T> T toObject(String content, Class<T> valueType) throws JsonParseException;
}
