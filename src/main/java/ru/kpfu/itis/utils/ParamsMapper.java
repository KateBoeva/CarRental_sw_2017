package ru.kpfu.itis.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by katemrrr on 17.05.17.
 */
public interface ParamsMapper {
    Map<String, Object> asMap(List<String> keys, List<?> values);
}
