package workbench.service;

import exception.SaveActivityException;
import workbench.entity.Activity;

public interface ActivityService {
    boolean saveActivity(Activity activity) throws SaveActivityException;
}
