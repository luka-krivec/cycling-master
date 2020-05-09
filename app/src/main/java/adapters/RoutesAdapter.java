package adapters;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import gpslogger.CyclingRoute;
import si.krivec.tracker.MapActivity;
import si.krivec.tracker.R;
import utils.DateUtilities;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.ViewHolder> {

    private Context context;
    private int rowLayout;
    CyclingRoute[] objects;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RoutesAdapter(Context context, int rowLayout, CyclingRoute[] objects) {
        this.context = context;
        this.rowLayout = rowLayout;
        this.objects = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CyclingRoute route = objects[position];

        holder.textViewDate.setText(DateUtilities.formatShortDate2(route.getStartTime()));
        holder.textViewName.setText(route.getName());
        holder.textViewDistance.setText(String.format("%.2f km", route.getDistance() / 1000));
        holder.textViewTime.setText(DateUtilities.timeToString((route.getTime())));
        holder.textViewAvgSpeed.setText(String.format("%.2f km/h", route.getAverageSpeed()));
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewDate;
        public TextView textViewName;
        public TextView textViewDistance;
        public TextView textViewTime;
        public TextView textViewAvgSpeed;
        public ImageButton btnMap;

        public ViewHolder(View v) {
            super(v);
            textViewDate = (TextView) v.findViewById(R.id.txtRowRouteDate);
            textViewName = (TextView) v.findViewById(R.id.txtRowRouteName);
            textViewDistance = (TextView) v.findViewById(R.id.txtRowRouteDistance);
            textViewTime = (TextView) v.findViewById(R.id.txtRowRouteTime);
            textViewAvgSpeed = (TextView) v.findViewById(R.id.txtRowRouteAvgSpeed);
            btnMap = (ImageButton) v.findViewById(R.id.btnRowRouteMap);
            btnMap.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            String gpxFile = context.getExternalFilesDir(null).getAbsolutePath() + "/gpx/" + textViewDate.getText() + "/" + textViewName.getText() + ".gpx";

            Intent mapActivity = new Intent(context, MapActivity.class);
            mapActivity.putExtra("gpxFile", gpxFile);

            context.startActivity(mapActivity);
        }
    }

}
