package moneytap.com.task.adapter;

import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import moneytap.com.task.R;
import moneytap.com.task.model.SearchedList;
import moneytap.com.task.view.search.SearchItemListener;

public class SearchAdapter extends RecyclerView.Adapter {
    List<SearchedList.QueryBean.PagesBean> userList;
    private SearchItemListener mItemListener;

    public SearchAdapter(ArrayList<SearchedList.QueryBean.PagesBean> userList, SearchItemListener itemListener) {
        this.userList = userList;
        this.mItemListener = itemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_search, viewGroup, false);
        UserViewHolder viewHolder = new UserViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final UserViewHolder holder = (UserViewHolder) viewHolder;
        final SearchedList.QueryBean.PagesBean pagesBean = userList.get(i);
        if (userList.get(i).getThumbnail() != null && pagesBean.getThumbnail().getSource() != null && userList.get(i).getThumbnail().getSource().length() > 0)
            Picasso.get().load(userList.get(i).getThumbnail().getSource()).into(holder.ivThumbnail);

        if (pagesBean.getTitle() != null)
            holder.tvTitle.setText(userList.get(i).getTitle());
        StringBuilder stringBuilder = new StringBuilder();
        if (pagesBean.getTerms() != null && pagesBean.getTerms().getDescription() != null)
            for (String s : userList.get(i).getTerms().getDescription()) {
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        holder.tvDesc.setText(stringBuilder.toString());
        holder.ivThumbnail.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair participants = new Pair<>(holder.ivThumbnail, ViewCompat.getTransitionName(holder.ivThumbnail));
                pagesBean.setPair(participants);
                mItemListener.onTaskClick(pagesBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void replaceData(List<SearchedList.QueryBean.PagesBean> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        private TextView tvTitle;
        private TextView tvDesc;

        public UserViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }
}