package settings.service;

import settings.entity.DicValue;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

public interface DicService {
    Map<String, List<DicValue>> getAll(ServletContext application);
}
