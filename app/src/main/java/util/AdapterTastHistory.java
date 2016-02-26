package util;

import java.util.ArrayList;
import java.util.List;

import com.techreviewsandhelp.travelchecklist.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterTastHistory extends ArrayAdapter<Tasks> {

	private int resource;
	private List<Tasks> items;
	Context _context;
	DB db;
	TextView TV_CheckName;
	ImageView delete;

	LinearLayout LLview;

	Tasks myString;

	public AdapterTastHistory(Context context, int resource,
			ArrayList<Tasks> items) {
		super(context, resource, items);
		// TODO Auto-generated constructor stub
		this.resource = resource;
		this.items = items;
		_context = context;
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
		final int pos = position;

		db = new DB(_context);
		TV_CheckName.setText(myString.getName());

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int Id = 0;
				Id = items.get(pos).id;
				items.remove(pos );
				db.DeleteTask(Id);
				notifyDataSetChanged();
			}
		});
		return LLview;
	}

	public void init() {
		TV_CheckName = (TextView) LLview.findViewById(R.id.TV_TASK_HISTORY);
		delete = (ImageView) LLview.findViewById(R.id.IV_delete);
	}

}
