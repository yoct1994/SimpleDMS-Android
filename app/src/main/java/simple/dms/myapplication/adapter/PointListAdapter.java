package simple.dms.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import simple.dms.myapplication.R;
import simple.dms.myapplication.model.data.Point;
import simple.dms.myapplication.model.data.PointList;

public class PointListAdapter extends RecyclerView.Adapter<PointListAdapter.ViewHolder> {

    private List<Point> points = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String pointReason = points.get(position).getReason();
        String pointDate = points.get(position).getDate();
        Integer pointNum = points.get(position).getPoint();

        holder.point_reason.setText(pointReason);
        holder.point_date.setText(pointDate);
        holder.point_num.setText(pointNum.toString());
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    public void setGoodPoints(PointList points) {
        List<Point> response = new ArrayList<>();
        for(Point point : points.getPoints()) {
            if(point.isPointType()) {
                response.add(point);
            }
        }
        this.points = response;
        notifyDataSetChanged();
    }

    public void setBadPoints(PointList points) {
        List<Point> response = new ArrayList<>();
        for(Point point : points.getPoints()) {
            if(!point.isPointType()) {
                response.add(point);
            }
        }
        this.points = response;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView point_reason;
        TextView point_date;
        TextView point_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            point_reason = itemView.findViewById(R.id.point_reason);
            point_date = itemView.findViewById(R.id.point_date);
            point_num = itemView.findViewById(R.id.point_num);
        }
    }
}
