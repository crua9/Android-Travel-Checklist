package util;

import java.util.ArrayList;

import com.techreviewsandhelp.travelchecklist.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterHome extends BaseAdapter {

	private Context context;
	private final ArrayList<HomeItem> items;
	TextView textView;

	ImageView imageView;

	public AdapterHome(Context context, ArrayList<HomeItem> items) {
		this.context = context;
		this.items = items;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		HomeItem myItem = items.get(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridView;

		gridView = new View(context);
		gridView = inflater.inflate(R.layout.item_home, null);
		textView = (TextView) gridView.findViewById(R.id.TVhomeItemTitle);
		imageView = (ImageView) gridView.findViewById(R.id.IVhomeItemIcon);

		textView.setText(myItem.getTitle());
		imageView.setImageBitmap(myItem.getIcon());

		return gridView;
	}
}
