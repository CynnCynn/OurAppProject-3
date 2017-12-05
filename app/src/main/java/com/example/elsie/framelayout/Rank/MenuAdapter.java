package com.example.elsie.framelayout.Rank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

import java.util.List;

/**
 * Created by Elsie on 2017/12/3.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

//    建立一个数据源
    private List<Product> DishList;


//    默认构造函数
    public MenuAdapter(List<Product> dishList) {
        DishList = dishList;
    }

    //        加载列表项布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //        加载列表项布局
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dish_item,parent,false);

        //        控件示例缓存
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    //	  对RecycleView子项进行操作
//    对RecyleView子项的数据进行赋值，在每个子项被滚动到屏幕内的时候执行
    @Override
    public void onBindViewHolder(final MenuAdapter.ViewHolder holder, int position) {
        final Product dish = DishList.get(position);

        final boolean[] isClick = {false,false};
        holder.dishPrice.setText("￥"+(int) dish.getFoodPrice());
        holder.dishName.setText(dish.getFoodName());
        holder.dishCommend.setText(dish.getSalesCount()+"人推荐");
//        holder.dishImageView.setImageResource(Integer.parseInt(dish.getImageUrl()));
        holder.dishImageView.setImageResource(R.drawable.dish);

        holder.likeDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int flag = 0;
//                实现只能点击一次
//                点击赞
                if (!isClick[0]) {
                    int num = 0;
                    num = dish.getSalesCount();
                    num ++;
                    dish.setSalesCount(num);
                    holder.dishCommend.setText(dish.getSalesCount()+"人推荐");

                    Toast.makeText(v.getContext(),"click like",Toast.LENGTH_LONG).show();
//                换图片
                    holder.likeDish.setImageResource(R.drawable.dish_like_filling);

                    isClick[0] = true;
                    flag = 1;
                }

//                取消赞
                if (isClick[0] && flag == 0) {
                    int num = 0;
                    num = dish.getSalesCount();
                    num --;
                    dish.setSalesCount(num);
                    holder.dishCommend.setText(dish.getSalesCount()+"人推荐");
                    Toast.makeText(v.getContext(),"cancle click like",Toast.LENGTH_LONG).show();
//                换图片
                    holder.likeDish.setImageResource(R.drawable.dish_like);

                    isClick[0] = false;
                }



            }
        });
        holder.dislikeDish.setOnClickListener(new View.OnClickListener() {
//            点击了赞就要先点击取消赞然后才能点击贬
            @Override
            public void onClick(View v) {
                if (!isClick[0] && !isClick[1]) {
                    Toast.makeText(v.getContext(),"click dislike",Toast.LENGTH_LONG).show();
                    isClick[1] = true;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return DishList.size();
    }

//     ViewHolder构造函数，用来获得组件
    public class ViewHolder extends RecyclerView.ViewHolder {
//    相应的变量
        ImageView dishImageView,likeDish,dislikeDish;
        TextView dishCommend,dishName,dishPrice;

        public ViewHolder(View itemView) {
            super(itemView);
//            从id获得对应的控件
            dishImageView = (ImageView) itemView.findViewById(R.id.dish_imageView);
            likeDish      = (ImageView) itemView.findViewById(R.id.like_dish_view);
            dislikeDish   = (ImageView) itemView.findViewById(R.id.dislike_dish_view);

            dishCommend   = (TextView) itemView.findViewById(R.id.dish_commend_people);
            dishName      = (TextView) itemView.findViewById(R.id.dish_name_view);
            dishPrice     = (TextView) itemView.findViewById(R.id.dish_price_view);

        }
    }
}
