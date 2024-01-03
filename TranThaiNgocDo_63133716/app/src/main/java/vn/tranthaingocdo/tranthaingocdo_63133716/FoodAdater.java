package vn.tranthaingocdo.tranthaingocdo_63133716;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodAdater extends BaseAdapter {
    private List<Food> lsFood;
    private LayoutInflater inflater;
    private Context context;
    public FoodAdater(List<Food> lsPokemon, Context context) {
        this.lsFood = lsPokemon;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }public class FoodItemViewHolde {
        TextView FoodView,MotaView;

    }
    @Override
    public int getCount() {
        return lsFood.size();
    }

    @Override
    public Object getItem(int position) {
        return lsFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodItemViewHolde foodItemViewHolde;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.layout_item,null);
            foodItemViewHolde = new FoodItemViewHolde();
            foodItemViewHolde.FoodView = convertView.findViewById(R.id.txtPokemon);
            foodItemViewHolde.MotaView = convertView.findViewById(R.id.txtMota);
            convertView.setTag(foodItemViewHolde);
        }else{
            foodItemViewHolde = (FoodItemViewHolde)convertView.getTag();
        }
        Food food = lsFood.get(position);
        foodItemViewHolde.FoodView.setText(food.getName());
        foodItemViewHolde.MotaView.setText(String.valueOf(food.getCalo()));
        return convertView;
    }
}
