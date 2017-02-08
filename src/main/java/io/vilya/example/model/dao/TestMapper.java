/**
 * spring-example
 * io.vilya.example.model.dao
 * 2017年2月8日 下午11:49:08
 */
package io.vilya.example.model.dao;

import org.springframework.stereotype.Repository;

/**
 * @author iamaprin
 * 2017年2月8日 下午11:49:08
 */
@Repository
public interface TestMapper {
    
    int insert(String value);
    
}
