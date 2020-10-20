package com.lzlk.nosql.redis.annotation.lock;
import com.lzlk.base.exception.BaseException;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.lock.RedisLockKey;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.nosql.redis.service.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 邻座旅客
 * @Description 分布式锁实现防止重复提交实现，注解实现，高并发下防止出现重复点击提交的事件
 * @Date 2019/8/1 10:49
 * @Created by 湖南达联
 */
@Component
@Aspect
public class SubmitLockAspect {

    private final Logger logger = LoggerFactory.getLogger(SubmitLockAspect.class);

    private static final String PAG_NAME = "SUBMIT";

    private static HashMap<String, Class> TYPES = new HashMap<String, Class>(){
        {
            put("java.lang.Integer",Integer.class);
            put("java.lang.Double",Double.class);
            put("java.lang.Float",Float.class);
            put("java.lang.Long",Long.class);
            put("java.lang.Short",Short.class);
            put("java.lang.Byte",Byte.class);
            put("java.lang.Boolean",Boolean.class);
            put("java.lang.Character",Character.class);
            put("java.lang.String",String.class);
            put("int",int.class);
            put("double",double.class);
            put("float",float.class);
            put("long",long.class);
            put("short",short.class);
            put("byte",byte.class);
            put("boolean",boolean.class);
            put("char",char.class);

        }
    };

    @Resource
    private RedisService redisService;

    //环绕通知实现方式
    @Around("@annotation(com.lzlk.nosql.redis.annotation.lock.SubmitLock) && @annotation(submitLock)")
    public Object doAround(ProceedingJoinPoint joinPoint, SubmitLock submitLock) throws Throwable {

        String[] keys = submitLock.keys();

        if(keys == null || keys.length == 0){
            throw new NullPointerException(" SubmitLock keys is not null , keys:" + keys);
        }
        ExceptionSysMarkEnum exceptionSysMarkEnum = submitLock.sysMarkEnum();

        if(exceptionSysMarkEnum == null){
            throw new NullPointerException(" SubmitLock exceptionSysMarkEnum is not null , exceptionSysMarkEnum:" + exceptionSysMarkEnum);
        }

        Map<String, Object> methodAndVal = this.getMethodAndVal(joinPoint);

        if(methodAndVal == null || methodAndVal.size() == 0){
            throw new NullPointerException(" SubmitLock methodAndVal  args is not null , methodAndVal:" + methodAndVal);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(String key : keys){
            if(StringUtils.isEmpty(key)){
                 continue;
            }
            //如果是对象,取值方式不一样
            if(key.contains("#")){
                String[] split = key.split("#");

                if(split == null || split.length < 2){
                    throw new NullPointerException(" SubmitLock key  is not null , key:" + key);
                }

                //取出嵌套对象里的值
                Object object = methodAndVal.get(split[0]);

                if(object == null){
                    throw new NullPointerException(" object is not null  , key : " + key );
                }

                Object fieldsValue = null;
                for(int i = 1 ; i < split.length  ; i ++){
                    fieldsValue = this.getFieldsValue(object, split[i]);
                }

                this.checkTypes(fieldsValue,key);

                stringBuilder.append(fieldsValue);

            }else {
                Object object = methodAndVal.get(key);
                this.checkTypes(object,key);
                stringBuilder.append(object);
            }
        }

        String lockKey = RedisLockKey.builderRedisKey(PAG_NAME,stringBuilder.toString());

        boolean setNX = redisService.setNX(lockKey, RedisLockKey.DEFAULT_VALUE, submitLock.timeOut());

        if(!setNX){
            throw new BaseException(PublicExceptionCodeEnum.EX_EXCESSIVE_ATTEMPTS.getCode(),PublicExceptionCodeEnum.EX_EXCESSIVE_ATTEMPTS.getMsg(), exceptionSysMarkEnum);
        }

        try {
            return joinPoint.proceed();
        }finally {
            redisService.delNX(lockKey);
        }
    }

    private Map<String,Object> getMethodAndVal(ProceedingJoinPoint joinPoint) throws Exception{
        Class classType = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        String[] parameterNames = this.getParameterNameJava8(classType, methodName);

        Object[] args = joinPoint.getArgs();
        Map<String,Object> map = new HashMap<>();
        for(int i = 0 ; i < args.length ; i++){
            map.put(parameterNames[i],args[i]);
        }
        return map;
    }

    public  String[] getParameterNameJava8(Class clazz, String methodName){
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return localVariableTableParameterNameDiscoverer.getParameterNames(method);
            }
        }
        return null;
    }



    private Object getFieldsValue(Object object,String fieldName) throws Exception{
       Field[] fields = object.getClass().getDeclaredFields();
       Object result = null;
       for(Field field : fields){
           field.setAccessible(true);
           if(field.getName().equals(fieldName)){
               result = field.get(object);
               break;
           }

       }
       return result;
    }

    private void checkTypes(Object object,String key){
        if(object == null ){
            throw new NullPointerException(" types of errors , object is not null key : " + key );
        }
        if(!this.checkClassType(object)){
            throw new NullPointerException(" types of errors , key : " + key +"  object : " + object.getClass() );
        }

    }


    private boolean checkClassType(Object object){
        Class<?> clazz = object.getClass();
        if(clazz == null){
            return false;
        }
        return TYPES.get(clazz.getName()) != null;
    }

//    public static void main(String[] args) {
//        String str = "sys.id";
//        String[] split = str.split("\\.");
//        System.out.println(split[1]);
//    }
}
