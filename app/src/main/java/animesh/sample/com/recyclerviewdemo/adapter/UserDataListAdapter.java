package animesh.sample.com.recyclerviewdemo.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import animesh.sample.com.recyclerviewdemo.R;
import animesh.sample.com.recyclerviewdemo.fragment.UserDataFragment;
import animesh.sample.com.recyclerviewdemo.interactor.RecyclerClickListener;
import animesh.sample.com.recyclerviewdemo.model.DataList;
import animesh.sample.com.recyclerviewdemo.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDataListAdapter extends RecyclerView.Adapter<UserDataListAdapter.MyViewHolder> {

    private List<DataList> mDataLists;
    private Context mContext;
    private RecyclerClickListener mRecyclerClickListener;

    public UserDataListAdapter(Context context, List<DataList> dataLists, RecyclerClickListener recyclerClickListener) {
        this.mContext = context;
        this.mDataLists = dataLists;
        this.mRecyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mDataLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataLists.size();
    }

    public void addItem(List<DataList> dataLists) {
        mDataLists.addAll(mDataLists.size(), dataLists);
        notifyItemInserted(mDataLists.size() - dataLists.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.row)
        CardView row;

        @BindView(R.id.first_name)
        TextView firstName;

        @BindView(R.id.last_name)
        TextView lastName;

        @BindView(R.id.user_image)
        ImageView userImage;

        private MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            row.setOnClickListener(this);
        }

        private void bind(DataList dataList) {
            if (dataList != null) {
                firstName.setText(dataList.getFirstName());
                lastName.setText(dataList.getLastName());
                Glide.with(mContext).load(dataList.getAvatar()).
                        apply(new RequestOptions().placeholder(R.mipmap.ic_launcher_round)).into(userImage);
            }
        }

        @Override
        public void onClick(View view) {
            mRecyclerClickListener.onClickItem(mDataLists.get(getAdapterPosition()));
        }
    }


}
