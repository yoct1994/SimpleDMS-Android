package simple.dms.myapplication.ui.late;

import java.util.List;

import simple.dms.myapplication.model.data.LateSeat;
import simple.dms.myapplication.model.data.RequestLate;

public class LateContract {
    public interface View{
        void setLateInfo(List<LateSeat> lateInfo);
        void wrongName();
        void setMyLateInfo(boolean isMyInfo);
    }
    public interface Presenter{
        void getLateInfo(String classNum);
        void cancelLate(String name);
        void postLate(RequestLate requestLate);
        void getMyInfo(String name);
    }
}
