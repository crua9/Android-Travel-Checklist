package util;

/**
 * Created by crua9 on 2/26/2016.
 */
import java.util.ArrayList;
import java.util.List;

import com.techreviewsandhelp.travelchecklist.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterAddPerson extends ArrayAdapter<String> {

    private int resource;
    private List<String> items;
    private Context context;

    TextView TVPersonName;
    Button BTNview;

    LinearLayout LLview;

    String myString;

    public AdapterAddPerson(Context context, int resource,
                            ArrayList<String> items) {
        super(context, resource, items);
        // TODO Auto-generated constructor stub
        this.resource = resource;
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        LLview = null;
        myString = items.get(position);
        if (convertView == null) {
            LLview = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater LI;
            LI = (LayoutInflater) getContext().getSystemService(inflater);
            LI.inflate(resource, LLview, true);
            init();
        } else {
            LLview = (LinearLayout) convertView;
            init();
        }

        TVPersonName.setText(myString);
        return LLview;
    }

    public void init() {
        TVPersonName = (TextView) LLview.findViewById(R.id.ET_personName);
    }

}
