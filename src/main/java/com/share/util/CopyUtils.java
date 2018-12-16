package com.share.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

/**
 * 属性的copy
 *
 * @author 博博大人
 * @time 2018/12/13 9:05
 */
public class CopyUtils {

    /**
     * 进行实体属性进行copy
     *
     * @param source 需要copy的属性
     * @param dest   赋值的属性
     * @throws Exception 异常
     */
    public static void Copy(Object source, Object dest) throws Exception {
        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
                Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean
                .getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(),
                Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for (int i = 0; i < sourceProperty.length; i++) {

                for (int j = 0; j < destProperty.length; j++) {

                    if (sourceProperty[i].getName().equals(
                            destProperty[j].getName())) {
                        // 调用source的getter方法和dest的setter方法
                        destProperty[j].getWriteMethod().invoke(
                                dest,
                                sourceProperty[i].getReadMethod()
                                        .invoke(source));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
    }


    /**
     *  使用spring的bean属性copy
     * @param bean1 被copy属性的类
     * @param bean2 获取copy属性的类
     */
	public static void BeanCopy(Objects bean1, Objects bean2) throws Exception {
        BeanUtils.copyProperties(bean1,bean2);
    }

}
