package simple.dms.myapplication.Methods;

import android.content.Context;
import android.widget.Toast;

public class MaskToast {
    public void setMaskToast(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
