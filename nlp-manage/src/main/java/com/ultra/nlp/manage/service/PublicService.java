package com.ultra.nlp.manage.service;

import com.ultra.nlp.manage.model.Page;

import java.util.Map;
import java.util.function.Predicate;

public interface PublicService {
    Map<String,Object> checkIfExist(Map<String,Object> map) throws Exception;

}
