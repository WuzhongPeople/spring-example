/**
 * spring-example
 * io.vilya.example.service.test.impl
 * 2017年2月8日 下午11:51:15
 */
package io.vilya.example.service.test.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.vilya.example.model.dao.TestMapper;
import io.vilya.example.service.test.TestService;

/**
 * @author iamaprin
 * 2017年2月8日 下午11:51:15
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {
    
    @Autowired
    private TestMapper testMapper;
    
    @Override
    public boolean insert(String value) {
	return testMapper.insert(value) > 0;
    }
    
    public boolean _insert(String value) {
	int rows = testMapper.insert(value);
	int t = 2 / 0;
	return rows > 0;
    }
}



