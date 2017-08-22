package pkp.faisal.fintech.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pkp.faisal.fintech.R;
import pkp.faisal.fintech.fragment.BisnisUnitFragment.OnListFragmentInteractionListener;
import pkp.faisal.fintech.fragment.dummy.BisnisUnitContent.BisnisUnit;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BisnisUnit} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBisnisUnitRecyclerViewAdapter extends RecyclerView.Adapter<MyBisnisUnitRecyclerViewAdapter.ViewHolder> {

    private final List<BisnisUnit> mValues;
    private final OnListFragmentInteractionListener mListener;
    private OnItemSelected mItemSelected;

    public MyBisnisUnitRecyclerViewAdapter(List<BisnisUnit> items, OnListFragmentInteractionListener listener, OnItemSelected itemSelected) {
        mValues = items;
        mListener = listener;
        mItemSelected = itemSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bisnis_unit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mDetailView.setText(mValues.get(position).details);

        if (holder.mItem.selected)
            holder.mChecklist.setVisibility(View.VISIBLE);
        else
            holder.mChecklist.setVisibility(View.INVISIBLE);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    mItemSelected.onSelect(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mDetailView;
        public final ImageView mChecklist;
        public BisnisUnit mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mDetailView = (TextView) view.findViewById(R.id.detail);
            mChecklist = (ImageView) view.findViewById(R.id.img_checklist);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDetailView.getText() + "'";
        }
    }

    public interface OnItemSelected {
        void onSelect(BisnisUnit mItem);
    }
}
