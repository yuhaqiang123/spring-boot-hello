package com.muppet.spring.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * 没有生效
 */
@Component
public aspect ModelGenerate {
    pointcut hello(Object entity): target(entity) && execution(com.muppet.spring.model.*.new(..));


    Logger logger = LogManager.getLogger(this.getClass());

    public ModelGenerate() {
        logger.error("aspect  初始化了");
    }
    after(Object entity) returning : hello(entity) {
        ReflectionUtils.doWithFields(entity.getClass(), (field) -> {
            logger.error("AOP  成功");
            String randomString = getRandomString(field.getType());
            if (randomString == null) {
                return;
            }

            RandomValuePropertySource source = new RandomValuePropertySource(randomString);
            Object value = source.getProperty(randomString);
            field.set(entity, value);
        });
        logger.error("hello after111");
    }

    private String getRandomString(Class clazz) {
        String random = "random.";
        if (clazz.isAssignableFrom(int.class) || clazz.isAssignableFrom(Integer.class)) {
            return random + "int";
        } else if (clazz.isAssignableFrom(long.class) || clazz.isAssignableFrom(Long.class)) {
            return random + "long";
        } else if (clazz.isAssignableFrom(String.class)) {
            return random + "uuid";
        }
        return null;
    }
}
