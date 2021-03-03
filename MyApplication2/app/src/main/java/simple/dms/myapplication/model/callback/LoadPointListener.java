package simple.dms.myapplication.model.callback;

import simple.dms.myapplication.model.data.PointList;

public interface LoadPointListener {
    void loadPoint(PointList points);
    void wrongToken();
}
