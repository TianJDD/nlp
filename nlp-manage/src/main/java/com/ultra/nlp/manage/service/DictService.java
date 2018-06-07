package com.ultra.nlp.manage.service;

import com.ultra.nlp.manage.model.NlpDict;
import com.ultra.nlp.manage.model.Page;

import java.util.List;
import java.util.Map;

public interface DictService {

    Page getDictList(Page page);

    int getDictToAdd(NlpDict nlpDict)throws Exception;

    int updateDict(NlpDict nlpDict);

    Page getDictTreeList(Page page);

    int delDict(String dictCode);
}
