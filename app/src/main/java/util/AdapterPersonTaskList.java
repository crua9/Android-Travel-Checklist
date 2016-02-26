package util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techreviewsandhelp.travelchecklist.R;

public class AdapterPersonTaskList extends ArrayAdapter<PersonTasks> {

	private int resource;
	private List<PersonTasks> items;
	Context _context;
	DB db;
	TextView TV_CheckName;
	ImageView delete;
	LinearLayout LLview;
	int personTaskListID;
	PersonTasks myString;

	public AdapterPersonTaskList(Context context, int resource,
			ArrayList<PersonTasks> items) {
		super(context, resource, items);
		// TODO Auto-generated constructor stub
		this.resource = resource;
		this.items = items;
		_context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LLview = null;
		personTaskListID = items.get(position).id;
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
		db = new DB(_context);
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int Id = 0;
				Id = items.get(position).id;
				items.remove(position);
				db.DeletePersonTaskItem(Id);
				notifyDataSetChanged();
			}
		});
		TV_CheckName.setText(items.get(position).getTaskDetail());
		return LLview;
	}

	public void init() {
		TV_CheckName = (TextView) LLview.findViewById(R.id.ET_ListItemName);
		delete = (ImageView) LLview.findViewById(R.id.IV_delete);
	}

}