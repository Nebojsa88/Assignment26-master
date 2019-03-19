package rs.aleph.android.example26.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

import rs.aleph.android.example26.R;
import rs.aleph.android.example26.db.DatabaseHelper;
import rs.aleph.android.example26.db.model.Product;

public class ListFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    // Container Activity must implement this interface
    public interface OnProductSelectedListener {
        void onProductSelected(int id);
    }

    OnProductSelectedListener listener;
    ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            List<Product> list = getDatabaseHelper().getProductDao().queryForAll();

            adapter = new ArrayAdapter<Product>(getActivity(), R.layout.list_item, list);

            final ListView listView = (ListView)getActivity().findViewById(R.id.products);

            // Assign adapter to ListView
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Posto radimo sa bazom podataka, svaki element ima jedinstven id
                    // pa je potrebno da vidimo na koji tacno element smo kliknuli.
                    // To mozemo uraditi tako sto izvucemo proizvod iz liste i dobijemo njegov id
                    Product p = (Product) listView.getItemAtPosition(position);

                    listener.onProductSelected(p.getmId());
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
           listener = (OnProductSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnProductSelectedListener");
        }
    }

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
