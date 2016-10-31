package utilities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truytimkhachno.ttkn.R;

import entities.Client;

/**
 * Created by Rypon on 10/31/2016.
 */

public class ClientTab {

    private LinearLayout root;
    private ImageView iv;
    private Client client;
    private Context context;

    public ClientTab(Client client, Context context) {
        this.client = client;
        this.context = context;
        initView();
    }

    public LinearLayout getRoot() {
        return root;
    }

    private void initView(){
        root = new LinearLayout(context);
        LinearLayout.LayoutParams parentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(parentParams);
        root.setOrientation(LinearLayout.HORIZONTAL);


//children of parent linearlayout
        LinearLayout childDetails = new LinearLayout(context);
        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        childParams.weight = 2;

        childDetails.setLayoutParams(childParams);
        childDetails.setOrientation(LinearLayout.VERTICAL);

        iv = new ImageView(context);
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ivParams.weight = 1;
//        if (c.getImages().size() > 0) {
//            new ImageLoader(iv).execute(c).get();
//
//        }
        iv.setLayoutParams(ivParams);
        iv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher));


//children of layout2 LinearLayout

        TextView tv1 = new TextView(context);
        tv1.setText(client.getId());
        TextView tv2 = new TextView(context);
        tv2.setText(client.toString());

        // add all views to their parents
        childDetails.addView(tv1);
        childDetails.addView(tv2);

        root.addView(iv);
        root.addView(childDetails);
    }

    public void updateAva(){
        new ImageLoader(iv).execute(client);
    }
}

