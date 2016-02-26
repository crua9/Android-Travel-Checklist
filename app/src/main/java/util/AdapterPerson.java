package util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techreviewsandhelp.travelchecklist.R;

public class AdapterPerson extends ArrayAdapter<Persons> {

	private int resource;
	private List<Persons> items;
	private Context context;

	TextView TVPersonName;
	Button BTNview;

	LinearLayout LLview;

	String myString;

	public AdapterPerson(Context context, int resource, ArrayList<Persons> items) {
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
		myString = items.get(position).getPersonName();
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