
package com.caisl.ap.core;


import com.caisl.ap.core.annotation.ActivitySubTypeMapper;
import com.caisl.ap.core.annotation.ActivityTypeMapper;
import com.caisl.ap.core.annotation.FunctionMapper;
import com.caisl.ap.core.base.IActivityDTOParser;
import com.caisl.ap.core.base.IActivityHandler;
import com.caisl.ap.core.base.IActivityResponseParser;
import com.caisl.ap.core.domain.ActivitySubTypeEnum;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * CommonFactory
 *
 * @author caisl
 * @since 2019-01-03
 */
@Component
public class CommonFactory implements ApplicationContextAware {

    private Container container = new Container();

    /**
     * 获取活动处理器
     *
     * @param function
     * @param activityType
     * @param activitySubType
     * @return
     */
    public IActivityHandler getActivityHandler(FunctionCodeEnum function, Integer activityType, Integer activitySubType) {
        IActivityHandler functionHandler = (IActivityHandler) container.get(IActivityHandler.class, function, generateKey(activityType, activitySubType));
        if (functionHandler == null) {
            String err = "functionCode :" + function + " - FunctionHandler mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return functionHandler;
        }
    }

    /**
     * 获取活动数据传输对象解析器
     *
     * @param function
     * @param activityType
     * @param activitySubType
     * @return
     */
    public IActivityDTOParser getActivityDTOParser(FunctionCodeEnum function, Integer activityType, Integer activitySubType) {
        IActivityDTOParser activityDTOParser = (IActivityDTOParser) container.get(IActivityDTOParser.class, function, generateKey(activityType, activitySubType));
        if (activityDTOParser == null) {
            String err = "functionCode :" + function + " - activityDTOParser mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return activityDTOParser;
        }
    }

    /**
     * 获取活动响应对象解析器
     *
     * @param function
     * @param activityType
     * @param activitySubType
     * @return
     */
    public IActivityResponseParser getActivityResponseParser(FunctionCodeEnum function, Integer activityType, Integer activitySubType) {
        IActivityResponseParser activityResponseParser = (IActivityResponseParser) container.get(IActivityResponseParser.class, function, generateKey(activityType, activitySubType));
        if (activityResponseParser == null) {
            String err = "functionCode :" + function + " - activityResponseParser mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return activityResponseParser;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Class[] types = new Class[]{IActivityHandler.class, IActivityDTOParser.class, IActivityResponseParser.class};
        for (Class type : types) {
            Map map = applicationContext.getBeansOfType(type);
            for (Object bean : map.values()) {
                FunctionMapper fMap = bean.getClass().getAnnotation(FunctionMapper.class);
                if (fMap == null) {
                    throw new RuntimeException(bean.getClass() + " has no Annotation @FunctionMapper");
                }
                if (fMap.value().length == 0) {
                    throw new RuntimeException(bean.getClass() + " @FunctionMapper value is empty");
                }
                ActivityTypeMapper typeMapper = bean.getClass().getAnnotation(ActivityTypeMapper.class);
                if (typeMapper == null) {
                    throw new RuntimeException(bean.getClass() + " has no Annotation @ActivityTypeMapper");
                }
                ActivityTypeEnum activityTypeEnum = typeMapper.value();
                ActivitySubTypeMapper subTypeMapper = bean.getClass().getAnnotation(ActivitySubTypeMapper.class);
                ActivitySubTypeEnum[] activitySubTypeEnums = subTypeMapper == null
                        // set default ActivitySubTypeEnum values
                        ? new ActivitySubTypeEnum[]{ActivitySubTypeEnum.DEFAULT}
                        : subTypeMapper.value();
                Arrays.stream(fMap.value()).forEach(functionCodeEnum ->
                        Arrays.stream(activitySubTypeEnums).forEach(activitySubTypeEnum ->
                                container.put(type, functionCodeEnum, generateKey(activityTypeEnum.getType(), activitySubTypeEnum.getType()), bean)));
            }
        }
    }

    /**
     * 容器类
     */
    private static class Container {
        private final Map<Class, Map<FunctionCodeEnum, Map<String, Object>>> beanMap;

        public Container() {
            this.beanMap = new HashMap<>();
        }

        /**
         * 往容器中添加对象
         *
         * @param clazz
         * @param function
         * @param bean
         */
        public void put(Class clazz, FunctionCodeEnum function, String key, Object bean) {
            Map<FunctionCodeEnum, Map<String, Object>> functionMap = beanMap.computeIfAbsent(clazz, k -> new HashMap<>(128));
            Map<String, Object> activityMap = functionMap.computeIfAbsent(function, k -> new HashMap<>(128));
            Object o = activityMap.put(key, bean);
            if (o != null) {
                throw new RuntimeException("duplicate bean,Class=" + clazz.getName() + "FunctionCodeEnum="
                        + function.getCode() + ",key=" + key + ",beans=[" + bean
                        + "|" + o + "]");
            }
        }

        public Object get(Class clazz, FunctionCodeEnum function, String key) {
            Map<String, Object> activityMap = getActivityMap(clazz, function);
            Object bean = activityMap.getOrDefault(key, null);
            if (bean == null) {
                throw new IllegalArgumentException(function + ",key:" + key + " " + clazz.getSimpleName() + " mismatch");
            }
            return bean;
        }

        private Map<String, Object> getActivityMap(Class clazz, FunctionCodeEnum function) {
            Map<FunctionCodeEnum, Map<String, Object>> functionMap = beanMap.get(clazz);
            if (functionMap == null) {
                String err = clazz.getSimpleName() + " mismatch";
                throw new IllegalArgumentException(err);
            }
            Map<String, Object> sellerMap = functionMap.get(function);
            if (sellerMap == null) {
                String err = function + " " + clazz.getSimpleName() + " mismatch";
                throw new IllegalArgumentException(err);
            }
            return sellerMap;
        }
    }

    private String generateKey(Integer activityType, Integer activitySubType) {
        KeyGenerator<Integer, Integer, String> keyGenerator = (arg1, arg2) -> (null == arg1 ? "-1" : arg1) + "_" + (null == arg2 ? "-1" : arg2);
        return keyGenerator.getKey(activityType, activitySubType);
    }

    interface KeyGenerator<K1, K2, R> {
        /**
         * generate map-key
         *
         * @param arg1 arg1
         * @param arg2 arg2
         * @return key
         */
        R getKey(K1 arg1, K2 arg2);
    }
}
