package za.co.entuit.medium.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import za.co.entuit.medium.R;

/**
 * Created by RVukela on 2017/04/13.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<Article> articles;
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View articleView = inflater.inflate(R.layout.article_list_item_row, parent,false);
        return  new ViewHolder(articleView );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.titleTextView.setText(article.getTitle());
        Picasso.with(context).load("https://upload.wikimedia.org/wikipedia/commons/8/87/Jacob_Zuma_2014_%28cropped%29.jpg")
                .error(R.drawable.ic_my_location_black_24dp)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(articles == null){
            return 0;
        }
        return articles.size();
    }

    public void replaceData(List<Article> articles){
        this.articles = articles;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.article_list_item_title);
            imageView = (ImageView) itemView.findViewById(R.id.article_list_item_image);
        }


    }
}
