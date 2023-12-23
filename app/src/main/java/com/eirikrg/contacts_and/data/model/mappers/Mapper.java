package com.eirikrg.contacts_and.data.model.mappers;


public interface Mapper<T, A> {
    A mapToDomain(T source);
}