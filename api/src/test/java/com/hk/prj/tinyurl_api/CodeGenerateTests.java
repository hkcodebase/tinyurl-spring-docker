package com.hk.prj.tinyurl_api;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class CodeGenerateTests {

    @Test
    void generateCodeTest(){
        RandomStringUtils randomUtils = RandomStringUtils.secure();
        String code =  randomUtils.nextAlphanumeric(6).toLowerCase();
        assert code.length() == 6;
    }

    @Test
    void generate1000CodeTest(){
        Set<String> codeSet = new HashSet<>();
        RandomStringUtils randomUtils = RandomStringUtils.secure();
        for (int i = 0; i < 1000000; i++) {
            codeSet.add(randomUtils.nextAlphanumeric(6).toLowerCase());
        }
        assert codeSet.size() > 900000;
    }

}
