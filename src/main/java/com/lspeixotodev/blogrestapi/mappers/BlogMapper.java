package com.lspeixotodev.blogrestapi.mappers;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


public class BlogMapper {

    public static <O, D> D parseObject(
            O origin,
            Class<D> destination,
            ModelMapper modelMapper
    ) {
        return modelMapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(
            List<O> origin,
            Class<D> destination,
            ModelMapper modelMapper
    ) {

        List<D> destinationObjects = new ArrayList<D>();

        for (O o : origin) {
            destinationObjects.add(modelMapper.map(o, destination));
        }

        return destinationObjects;
    }
}
