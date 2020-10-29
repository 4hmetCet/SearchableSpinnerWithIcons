package com.ahmetcet.searchablespinnerwithicons.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.ahmetcet.searchablespinnerwithicons.Model.ListItem;
import com.ahmetcet.searchablespinnerwithicons.R;

import java.util.ArrayList;
import java.util.Locale;

import static com.ahmetcet.searchablespinnerwithicons.Tools.ImageStrToDrawable.GetGroupIconImage;


public class CustomListAdapter extends BaseAdapter implements Filterable {
    Context context;
    private ArrayList<ListItem> values;
    private ArrayList<ListItem> filteredValues;
    LayoutInflater inflter;

    public CustomListAdapter(Context applicationContext, ArrayList<ListItem> values) {
        this.context = applicationContext;
        this.values = values;
        this.filteredValues = values;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return filteredValues.size();
    }

    @Override
    public ListItem getItem(int pos) {
        return filteredValues.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_list_item_with_image, null);
        ImageView icon = (ImageView) view.findViewById(R.id.iconImage);
        TextView names = (TextView) view.findViewById(R.id.itemName);
        names.setText(filteredValues.get(i).getValue());

        try {
            String imgName = filteredValues.get(i).getValue();
            imgName = imgName.split(" - ")[0];

            icon.setImageDrawable(context.getDrawable(GetGroupIconImage(imgName.toLowerCase(Locale.ENGLISH))));

        } catch (Exception e) {
            return view;
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                FilterResults results = null;
                try {
                    results = new FilterResults();

                    //If there's nothing to filter on, return the original data for your list
                    if(charSequence == null || charSequence.length() == 0)
                    {
                        results.values = values;
                        results.count = values.size();
                    }
                    else
                    {

                        ArrayList<ListItem> filterResultsData = new ArrayList<ListItem>();

                        if(charSequence.toString().contains(" ")){
                            ArrayList<ListItem> temp_filteredOriginalData = values;
                            ArrayList<ListItem> temp_filteredData = new ArrayList<>();
                            String[] search_words = charSequence.toString().split(" ");
                            for (String word:
                                 search_words) {
                                if(!word.isEmpty()){
                                    for (ListItem data : temp_filteredOriginalData){
                                        if (data.getValue().toLowerCase().contains(word.toLowerCase())) {
                                            temp_filteredData.add(data);
                                        }
                                    }
                                }
                                temp_filteredOriginalData = new ArrayList<>(temp_filteredData);
                                temp_filteredData = new ArrayList<>();
                            }
                            filterResultsData = new ArrayList<>(temp_filteredOriginalData);
                        }
                        else {
                            for (ListItem data : values) {
                                //In this loop, you'll filter through originalData and compare each item to charSequence.
                                //If you find a match, add it to your new ArrayList
                                //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional

                                if (data.getValue().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                                    filterResultsData.add(data);
                                }
                            }
                        }

                        results.values = filterResultsData;
                        results.count = filterResultsData.size();
                    }
                } catch (Exception e) {
                    results.values = values;
                    results.count = values.size();
                }finally {
                    return results;
                }
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredValues = ( ArrayList<ListItem>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
