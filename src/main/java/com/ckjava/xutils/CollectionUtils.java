package com.ckjava.xutils;

import java.util.*;

public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    /**
     * 将 {@code List<String>} 转换成   {@code List<Integer>}
     *
     * @param srcList  源集合
     * @param destList 目标集合
     */
    public static void formatListELement(List<String> srcList, List<Integer> destList) {
        if (srcList == null || destList == null) {
            return;
        }
        for (String str : srcList) {
            if (str == null) {
                destList.add(null);
            } else {
                destList.add(Integer.parseInt(str));
            }
        }
    }

    /**
     * 获取 集合 对象的大小，如果集合为空，返回0
     *
     * @param cols 目标集合对象
     * @return 集合大小
     */
    public static int getSize(Collection<?> cols) {
        return cols != null ? cols.size() : 0;
    }

    /**
     * 获取Map里面的值，如果Map为空，返回null
     *
     * @param <K>  Map 对象中的 key
     * @param <V>  Map 对象中的 Value
     * @param data Map 对象
     * @param key  Map 中的key
     * @return Map 中的key对应的Value
     */
    public static <K, V> V getVal(Map<K, V> data, K key) {
        return data != null && !data.isEmpty() ? data.get(key) : null;
    }

    /**
     * 将指定的键值对集合放入到 HashMap中
     *
     * @param <K>    Map 对象中的 key
     * @param <V>    Map 对象中的 Value
     * @param keys   键数组
     * @param values 值数组
     * @return keys 键数组 和 values 值数组 合并成的 HashMap 对象
     */
    public static <K, V> HashMap<K, V> asHashMap(K[] keys, V[] values) {
        if (keys == null || values == null) {
            return null;
        }
        HashMap<K, V> hashMap = new HashMap<K, V>();
        for (int i = 0, c = keys.length; i < c; i++) {
            hashMap.put(keys[i], values[i]);
        }
        return hashMap;
    }

    /**
     * 将指定的键值对集合放入到指定类型的Map对象中
     *
     * @param <K>    键数组
     * @param <V>    值数组
     * @param <M>    Map 的子类
     * @param keys   键数组
     * @param values 值数组
     * @param map    Map 的子类的实例, 比如 HashMap 或者 LinkedHashMap
     * @return M 类型的Map对象
     */
    public static <K, V, M extends Map<K, V>> M asMap(K[] keys, V[] values, M map) {
        if (keys == null || values == null) {
            return null;
        }
        if (map == null) {
            return null;
        }
        for (int i = 0, c = keys.length; i < c; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    /**
     * 这里的 targetList 相当于数据库中的一张表, 通过 key 将这张表分隔成若干张子表
     *
     * @param key        表中的某个字段
     * @param targetList 数据表
     * @return Map<String   ,       List   <   Map   <   String   ,       Object>>> 以 key 的值为键, 以
     * List<Map<String, Object>> 为值
     */
    public static Map<String, List<Map<String, Object>>> groupList(final String key, List<Map<String, Object>> targetList) {
        // 首先根据 key 将目标集合排序
        Collections.sort(targetList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String keyValue1 = StringUtils.getStr(o1.get(key));
                String keyValue2 = StringUtils.getStr(o2.get(key));
                return keyValue1.compareTo(keyValue2);
            }
        });

        Map<String, List<Map<String, Object>>> groupMap = new LinkedHashMap<>();
        String tempKeyValue = "";
        List<Map<String, Object>> tempDataList = new ArrayList<>();
        for (int i = 0, c = CollectionUtils.getSize(targetList); i < c; i++) {
            Map<String, Object> data = targetList.get(i);
            String keyValue = StringUtils.getStr(data.get(key));
            // 遍历开始, 初始化临时key的值
            if (i == 0) {
                tempKeyValue = keyValue;
            }

            // 关键Key的值不同的时候开始分组
            if (!tempKeyValue.equals(keyValue)) {
                // 将分组数据存入返回的Map中
                groupMap.put(tempKeyValue, tempDataList);

                // 初始化临时Map
                tempDataList = new ArrayList<>();
                tempDataList.add(data);

                // 初始化临时key的值
                tempKeyValue = keyValue;
            } else {
                tempDataList.add(data);
            }

            // 遍历结束, 将最后一个分组存入返回的Map中
            if (i == c - 1) {
                groupMap.put(tempKeyValue, tempDataList);
            }
        }

        return groupMap;
    }

}
