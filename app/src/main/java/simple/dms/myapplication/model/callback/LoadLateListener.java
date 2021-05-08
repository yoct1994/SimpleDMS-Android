package simple.dms.myapplication.model.callback;

import java.util.List;

import simple.dms.myapplication.model.data.LateSeat;

public interface LoadLateListener {
    void lateInfo(List<LateSeat> lateSeats);
}
