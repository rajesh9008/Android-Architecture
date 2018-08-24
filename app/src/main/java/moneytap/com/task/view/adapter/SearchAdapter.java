package moneytap.com.task.view.adapter;

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
    List<SearchedList.QueryBean.PagesBean> mSearchList;
    private SearchItemListener mItemListener;

    public SearchAdapter(ArrayList<SearchedList.QueryBean.PagesBean> searchList, SearchItemListener itemListener) {
        this.mSearchList = searchList;
        this.mItemListener = itemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_search, viewGroup, false);
        SearchViewHolder viewHolder = new SearchViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final SearchViewHolder holder = (SearchViewHolder) viewHolder;
        final SearchedList.QueryBean.PagesBean pagesBean = mSearchList.get(i);
        if (mSearchList.get(i).getThumbnail() != null && pagesBean.getThumbnail().getSource() != null && mSearchList.get(i).getThumbnail().getSource().length() > 0)
            Picasso.get().load(mSearchList.get(i).getThumbnail().getSource()).into(holder.ivThumbnail);

        if (pagesBean.getTitle() != null)
            holder.tvTitle.setText(mSearchList.get(i).getTitle());
        StringBuilder stringBuilder = new StringBuilder();
        if (pagesBean.getTerms() != null && pagesBean.getTerms().getDescription() != null)
            for (String s : mSearchList.get(i).getTerms().getDescription()) {
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        holder.tvDesc.setText(stringBuilder.toString());
        holder.ivThumbnail.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair participants = new Pair<>(holder.ivThumbnail, ViewCompat.getTransitionName(holder.ivThumbnail));
                pagesBean.setPair(participants);
                mItemListener.onSearchedItemClick(pagesBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSearchList.size();
    }

    public void replaceData(List<SearchedList.QueryBean.PagesBean> mSearchList) {
        this.mSearchList = mSearchList;
        notifyDataSetChanged();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        private TextView tvTitle;
        private TextView tvDesc;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }
}