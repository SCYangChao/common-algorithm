package com.yqkj.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * @ClassName CollectionTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 10:05
 * @Version 1.0
 **/
public class CollectionTool {
    /**
     * @param listData
     * @param key
     * @param c
     * @param <I>
     * @return
     */
    private static <I> List<I> convertData(List<Map<String, Object>> listData, String key , Class<I> c) {
        List<I> result = new ArrayList<>(CollectionTool.listSize(listData));
        for (Map<String , Object> map : listData){
            if(map.containsKey(key)) {
                I i = (I) map.get(key);
                if(!Objects.isNull(i)) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    /**
     * 转换数组数据
     * @param arrray
     * @return
     */
    public  static  List<String> convertToArray(String[] arrray){
        List<String> result = new ArrayList<>(arrray.length);
        for (int index =0;index <arrray.length; index++) {
            if(index == 0){
                arrray[0]=arrray[0];
            }else {
                arrray[index]=arrray[index-1]+"|"+arrray[index];
            }
            result.add(arrray[index]);
        }
        return  result;
    }

    /**
     * 集合长度
     * @param list
     * @return
     */
    public  static  Integer listSize(List list ){
        if(CollectionTool.isNull(list)){
            return  0;
        }
        return  list.size();
    }

    /**
     * 数组转换为List集合
     * @param array
     * @return
     */
    public  static  List<Long>  convertToArray(Long[] array){
        List<Long> idList = Arrays.asList(array);
        return  idList;
    }

    /**
     *
     * @param list
     * @param fn
     * @param <O>
     * @return
     */
    public  static <O> String[] parseIdsArray(List<O> list , Function<O,String> fn) {
        if (!isNull(list)) {

            String[] ar = new String[list.size()];

            for (int index =0 ,count=list.size();index < count;index++){
                ar[index]=fn.apply(list.get(index));
            }

            return  ar;
        }
        return null;

    }
    /**
     * 切割
     * @param source
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n;
        //(先计算出余数)
        int number = source.size() / n;
        //然后是商
        int offset = 0;
        //偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
    /**
     * @Description:
     * @param list
     * @param group
     * @return Map<K,List<O>>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月13日 上午9:31:30
     */
    public static <K, O >  Map<K , List<O>> groupList(List<O> list , Function<O , K> group){
        if (null == list) {
            return  new HashMap<>();
        }
        return  list.stream().collect(Collectors.groupingBy(group));
    }
    /**
     * 根据Int
     * @param list
     * @param fn
     * @param <T>
     * @return
     */
    public static <T> Double avgLong(List<T> list , ToLongFunction<T> fn) {

        if (isNull(list)) {

            return 0D;

        }
        return list.stream().mapToLong(fn).average().getAsDouble();
    }
    /**
     * 根据Int
     * @param list
     * @param fn
     * @param <T>
     * @return
     */
    public static <T> Double avgInt(List<T> list , ToIntFunction<T> fn) {

        if (isNull(list)) {

            return 0D;

        }
        return list.stream().mapToInt(fn).average().getAsDouble();
    }
    /**
     * 求平均值
     *
     * @Description:
     * @param list
     * @param fn
     * @return Double
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月11日 下午4:55:09
     */
    public static <T , R> Double avgDouble(List<T> list , ToDoubleFunction<T> fn) {

        if (isNull(list)) {

            return 0D;

        }

        return list.stream().mapToDouble(fn).average().getAsDouble();
    }

    /**
     *
     * @param ids
     * @return
     */
    public  static  String convertToStringStr(List<String> ids) {
        StringBuffer stringBuffer = new StringBuffer();
        if(!CollectionTool.isNull(ids)) {
            ids.forEach(id->{
                stringBuffer.append(id).append(",");
            });
            return  stringBuffer.substring(0,stringBuffer.length()-1);
        }
        return stringBuffer.toString();
    }

    /**
     *
     * @ClassName: CollectionToole
     * @Description:
     * @author yangchao.coo@gmail.com
     * @date 2020/1/8
     *
     */
    public  static  String convertToString(List<Long> ids) {
        StringBuffer stringBuffer = new StringBuffer();
        if(!CollectionTool.isNull(ids)) {
            ids.forEach(id->{
                stringBuffer.append(id).append(",");
            });
            return  stringBuffer.substring(0,stringBuffer.length()-1);
        }
        return stringBuffer.toString();
    }
    /**
     *
     *
     * @Description:
     * @param ids
     * @return List<Long>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月11日 上午10:51:15
     */
    public static List<Long> parseIds(String ids) {
        if (StringUtils.isNotBlank(ids)) {

            String[] ar = ids.split(",");

            List<Long> result = new ArrayList<>(ar.length);

            for (String n : ar) {

                try {
                    if (StringUtils.isNotBlank(n)) {

                        result.add(Long.valueOf(n));

                    }

                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

            return result;

        }
        return null;

    }
    /**
     *
     *
     * @Description:
     * @param ids
     * @return List<Long>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月11日 上午10:51:15
     */
    public static List<String> parseStrIds(String ids) {
        if (StringUtils.isNotBlank(ids)) {

            String[] ar = ids.split(",");

            List<String> result = new ArrayList<>(ar.length);

            for (String n : ar) {

                try {
                    if (StringUtils.isNotBlank(n)) {

                        result.add(n);

                    }

                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

            return result;

        }
        return null;

    }
    /**
     * @Description:
     * @param list
     * @param fn
     *  @return
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月10日 上午10:09:39
     */
    public static <T , K, V , R>  void excuteMethod(List<T> list ,Function<T, R> fn) {

        if (isNull(list)) {

            return ;

        }

        list.forEach(ob -> {

            fn.apply(ob);

        });

    }

    /**
     * 跟一个值另一个值
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public  static <T,K,V> List<T> initOneInfo(List<T> list , Function<T , Consumer<V>> consumer, V v) {

        if (null == list) {
            return list;
        }
        for (T t : list) {

            consumer.apply(t).accept(v);
        }
        return list;

    }
    /**
     * 跟一个值另一个值
     * @param list
     * @param fn
     * @param consumer
     * @param map
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public  static <T,K,V> List<T> initOneInfo(List<T> list , Function<T , K> fn , Function<T , Consumer<V>> consumer, Map<K , V> map) {

        if (null == list || null == map) {
            return list;
        }
        for (T t : list) {

            K key = fn.apply(t);

            if (map.containsKey(key)) {

                V value =  map.get(key);

                consumer.apply(t).accept(value);

            } else {

                V value =  null;

                consumer.apply(t).accept(value);


            }

        }
        return list;

    }
    /**
     * 过滤数据
     *
     * @Description:
     * @param list
     * @param pre
     * @return List<T>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月4日 上午10:21:24
     */
    public  static <T> List<T> filterList(List<T> list , Predicate<T> pre) {
        if (isNull(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(pre).collect(Collectors.toList());
    }


    /**
     * @Description:检查数组是否为空 如果为空则返回空集合
     * @param list
     * @return List<T>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月28日 下午5:00:34
     */
    public  static <T> List<T> checkListAndReturnEmptyList(List<T> list) {
        if (isNull(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     *
     * @Description:
     * @param list
     * @param fn
     * @return Double
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月27日 上午10:35:12
     */
    public static <T> Double sumDouble(List<T> list , Function<T, Double> fn) {

        if (isNull(list)) {

            return Double.valueOf(0);

        }

        try {
            return list.stream().map(fn).reduce(Double::sum).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Double.valueOf(0);

    }
    /**
     *
     *
     * @Description: list集合求和
     * @param list
     * @param fn
     * @return Integer
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月27日 上午10:33:21
     */
    public static <T> Double sumBigDecimal(List<T> list , Function<T, BigDecimal> fn) {

        if (isNull(list)) {

            return Double.valueOf(0);

        }

        List<BigDecimal> longList = list.stream().map(fn).filter(num -> null != num).collect(Collectors.toList());

        return longList.stream().mapToDouble(BigDecimal::doubleValue).reduce(Double::sum).getAsDouble();

    }
    /**
     *
     *
     * @Description: list集合求和
     * @param list
     * @param fn
     * @return Integer
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月27日 上午10:33:21
     */
    public static <T> Integer sumInteger(List<T> list , Function<T, Integer> fn) {

        if (isNull(list)) {

            return Integer.valueOf(0);

        }

        List<Integer> longList = list.stream().map(fn).filter(num -> null != num).collect(Collectors.toList());

        return longList.stream().reduce(Integer::sum).get();

    }
    /**
     *
     *
     * @Description:
     * @param list
     * @return List<T>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月27日 上午10:28:46
     */
    public static <T> List<T> listRever(List<T> list) {

        if (isNull(list)) {

            return new ArrayList<>();

        }
        List<T> result = new ArrayList<>(list.size());

        for (int index = result.size() ;index>0 ; ++ index) {
            result.add(list.get(index));
        }

        return result;
    }

    /**
     * @Description:对象数据直接转换为Map数据结构
     * @param list
     * @return List<Map<String,Object>>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月23日 上午10:43:59
     */
	/*public static <T> List<Map<String , Object>> listConvertTopMap(List<T> list) {

		if (Objects.isNull(list) || list.isEmpty()) {

			return EMPTY_LIST;

		}

		List<Map<String , Object>> result = new ArrayList<>();

		Map<String , Object> tempMap = null;

		for (T t : list) {

			tempMap = BeanTool.transformTopMap(t);

			if (!Objects.isNull(tempMap)) {

				result.add(tempMap);

			}
		}


		return result;

	}*/

    /**
     * 降序
     * @Description:
     * @param list
     * @param cm void
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月22日 上午9:28:33
     */
    public static <O> List<O> sortDesc(List<O> list ,  Comparator<O> cm) {

      return sort(list , cm.reversed());
    }
    /**
     * 降序
     * @Description:
     * @param list
     * @param cm void
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月22日 上午9:28:33
     */
    public static <O> List<O> sort(List<O> list ,  Comparator<O> cm) {

        if (Objects.isNull(list) || list.isEmpty()) {
            return list;
        }
        return list.stream().sorted(cm).collect(Collectors.toList());

    }
    /**
     *  利用方法<T>
     * List<Object> 转换成Map<String , Object>
     * @param list
     * @param <K>
     * @param <T>
     * @return
     */
    public static  <K , T , V> Map<K , V> convertMap( Set<T> list , Function<T , K> keyFn , Function<T , V> valueFn) {

        if (null == list || list.isEmpty()) {
            return  new HashMap<>();
        }

        Map<K , V> result = new HashMap<>(list.size());

        for (T ob : list ) {

            result.put(keyFn.apply(ob) ,valueFn.apply(ob));

        }

        return  result;

    }
    /**
     *  利用方法<T>
     * List<Object> 转换成Map<String , Object>
     * @param list
     * @param <K>
     * @param <T>
     * @return
     */
    public static  <K , T , V> Map<K , V> convertMap( List<T> list , Function<T , K> keyFn , Function<T , V> valueFn) {

        if (null == list || list.isEmpty()) {
            return  new HashMap<>();
        }

        Map<K , V> result = new HashMap<>(list.size());

        for (T ob : list ) {

            result.put(keyFn.apply(ob) ,valueFn.apply(ob));

        }

        return  result;

    }
    /**
     * 列表转为另一个列表
     * @param list
     * @param fn
     * @param <T>
     * @param <R>
     * @return
     */
    public  static  <T , R> Set<R> convertToSet(List<T> list , Function<T , R> fn) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new HashSet<>();
        }

        Set<R> resutlt = new HashSet<>(list.size());

        for (T t : list ) {

            resutlt.add(fn.apply(t));

        }

        return  resutlt;
    }
    /**
     * 列表转为另一个列表
     * @param list
     * @param fn
     * @param <T>
     * @param <R>
     * @return
     */
    public  static  <T , R> List<R> convertToList(Set<T> list , Function<T , R> fn) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new ArrayList<>();
        }
        return  list.stream().map(fn).collect(Collectors.toList());
    }
    /**
     * 列表转为另一个列表
     * @param list
     * @param fn
     * @param <T>
     * @param <R>
     * @return
     */
    public  static  <T , R> List<R> convertToList(Collection<T> list , Function<T , R> fn) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new ArrayList<>();
        }
        return  list.stream().map(fn).collect(Collectors.toList());
    }
    /**
     * 列表转为另一个列表
     * @param list
     * @param fn
     * @param <T>
     * @param <R>
     * @return
     */
    public  static  <T , R> List<R> convertToList(List<T> list , Function<T , R> fn) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new ArrayList<>();
        }
        return  list.stream().map(fn).filter(o->{return !Objects.isNull(o);}).collect(Collectors.toList());
    }

    /**
     * 对象拷贝
     *
     * @Description:
     * @param list
     * @param r
     * @return List<R>
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月16日 下午6:16:17
     */
    public static <R , O> List<R> convertToObjectList(List<O> list , Class< R> r) {

        if (isNull(list)) {

            return (List<R>) new ArrayList<>();

        }

        List<R> result = new ArrayList<>(list.size());

        for (O dto : list) {

            result.add(convertToObject(dto, r));

        }

        return result;
    }
    /**
     * 对象拷贝
     *
     * @Description:
     * @param o
     * @param r
     * @return R
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月16日 下午6:18:43
     */
    public static <O , R> R convertToObject( O o, Class< R> r) {

//        R temp = BeanUtils.instantiateClass(r);
//        R temp = null;

//        copyObject(o, temp);

        return null;

    }
    /**
     *
     *
     * @Description:
     * @param source
     * @param target void
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月22日 上午9:15:09
     */
    public static <R , O> void copyObject(R source , O target) {

        if (Objects.isNull(source)) {

            return ;

        }
//        BeanUtils.copyProperties(source , target);
//        BeanTool.copyObject(source, target, null, (String[]) null);
    }
    public  static<T>  Boolean isNull(T[] t) {

        if (Objects.isNull(t) || t.length<=0) {
            return  Boolean.TRUE;
        }

        return Boolean.FALSE;

    }
    public  static<T , v>  Boolean isNull(Map<T , v> t) {

        if (Objects.isNull(t) || t.isEmpty()) {
            return  Boolean.TRUE;
        }

        return Boolean.FALSE;

    }
    public  static <T> Boolean isNotNull(List<T> list){
        return  !isNull(list);
    }
    public  static<T>  Boolean isNull(Set<T> t) {

        if (null == t || t.isEmpty()) {
            return  Boolean.TRUE;
        }

        return Boolean.FALSE;

    }

    public  static<T>  Boolean isNull(List<T> t) {

        if (null == t || t.isEmpty()) {
            return  Boolean.TRUE;
        }

        return Boolean.FALSE;

    }

    /**
     * 解析Long 集合
     * @param list
     * @return
     */
    public  static  List<Long> requirOneDataList(List<Long> list ) {
        if(CollectionTool.isNull(list)) {
            List<Long> ins = new ArrayList<>();
            ins.add(-1L);
            return  ins;
        }
        return list;
    }


    /**
     * 对象转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> convertToMap(Object obj)
            throws Exception {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return  map;
    }

}
