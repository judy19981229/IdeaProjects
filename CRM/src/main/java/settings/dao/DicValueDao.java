package settings.dao;

import settings.entity.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getDicValue(String typeCode);
}
