package vn.tranthaingocdo.tranthaingocdo_63133716;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BaiTapAdapter extends BaseAdapter {
    private List<BaiTap> baiTapList;
    private LayoutInflater inflater;
    private Context context;

    public BaiTapAdapter(List<BaiTap> baiTapList, Context context) {
        this.baiTapList = baiTapList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    public class PokemonItemViewHolde{
        TextView BaiTapView,MotaView;
        ImageView avatarView;
    }
    @Override
    public int getCount() {
        return baiTapList.size();
    }

    @Override
    public Object getItem(int position) {
        return baiTapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PokemonItemViewHolde pokemonItemViewHolde;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.layout_item,null);
            pokemonItemViewHolde = new PokemonItemViewHolde();
            pokemonItemViewHolde.BaiTapView = convertView.findViewById(R.id.txtBaiTap);
            pokemonItemViewHolde.MotaView = convertView.findViewById(R.id.txtMota);
            pokemonItemViewHolde.avatarView = convertView.findViewById(R.id.imgBaiTap);
            convertView.setTag(pokemonItemViewHolde);
        }else{
            pokemonItemViewHolde = (PokemonItemViewHolde)convertView.getTag();
        }
        BaiTap pokemonitem = baiTapList.get(position);
        pokemonItemViewHolde.BaiTapView.setText(pokemonitem.getNameBT());
        pokemonItemViewHolde.MotaView.setText(pokemonitem.getMoTa());

        int avtID = getimgID(pokemonitem.getImageBT());
        pokemonItemViewHolde.avatarView.setImageResource(avtID);
        return convertView;
    }
    public int getimgID(String resName)
    {
        String packageName = context.getPackageName();
        int imgID = context.getResources().getIdentifier(resName,"mipmap",packageName);
        return imgID;
    }
}
