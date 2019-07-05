package employy.boss.ahmadaghber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private ArrayList<String> tasks;
    private Context mContext;
    private ArrayList<String> filteredTasks;
    private ValueFilter valueFilter;


    //constructor to set filtered task and tasks to compare between then in search
    public RecyclerViewAdapter(ArrayList<String> tasks, Context mContext) {
        this.tasks = tasks;
        this.mContext = mContext;
        this.filteredTasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.taskTextView.setText(tasks.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if someone selected on text or checkbox
                if(holder.checkBox.isChecked()) {
                    holder.checkBox.setChecked(false);
                }
                else {
                    holder.checkBox.setChecked(true);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public Filter getFilter() {
        if(valueFilter==null){
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView taskTextView;
        CheckBox checkBox;
        RelativeLayout parentLayout;
        public ViewHolder(View view){
            super(view);
            checkBox = view.findViewById(R.id.checkBox);
            taskTextView = view.findViewById(R.id.taskTextView);
            parentLayout = view.findViewById(R.id.parentLayout);
        }
    }

    private class ValueFilter extends  Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if (charSequence != null && charSequence.length() > 0) {
                ArrayList<String> filterList = new ArrayList<>();
                for (int i = 0; i < filteredTasks.size(); i++) {
                    if ((filteredTasks.get(i).toUpperCase()).contains(charSequence.toString().toUpperCase())) {
                        filterList.add(filteredTasks.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filteredTasks.size();
                results.values = filteredTasks;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            tasks = (ArrayList<String>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
