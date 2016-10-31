package utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truytimkhachno.ttkn.R;

import java.util.concurrent.ExecutionException;

import entities.Client;

/**
 * Created by Rypon on 10/31/2016.
 */

public class ClientTab {

    private LinearLayout root;
    private ImageView iv;
    private Client client;
    private Context context;
    private Bitmap ava;

    public ClientTab(Client client, Context context) {
        this.client = client;
        this.context = context;
        initView();
    }

    public LinearLayout getRoot() {
        return root;
    }

    private void initView() {
        root = new LinearLayout(context);
        LinearLayout.LayoutParams parentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.setBackgroundColor(Color.rgb(63,5,9));
        root.setPadding(10,10,10,10);
        parentParams.setMargins(5,5,5,5);
        root.setLayoutParams(parentParams);
        root.setOrientation(LinearLayout.VERTICAL);


//children of parent linearlayout
        LinearLayout childDetails = new LinearLayout(context);
        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        childDetails.setLayoutParams(childParams);
        childDetails.setOrientation(LinearLayout.VERTICAL);

        iv = new ImageView(context);
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(ivParams);
        iv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));


//children of layout2 LinearLayout

        TextView tv1 = new TextView(context);
        tv1.setTextColor(Color.WHITE);
        tv1.setText(client.getId());
        TextView tv2 = new TextView(context);
        tv2.setTextColor(Color.WHITE);
        tv2.setText(client.toString());

        // add all views to their parents
        childDetails.addView(tv1);
        childDetails.addView(tv2);

        root.addView(iv);
        root.addView(childDetails);
    }

    public void updateAva() throws ExecutionException, InterruptedException {
//        if (ava == null) {
//            ava = new ImageLoader().execute(client).get();
//            if(ava==null){
//                ava = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
//            }
//        }
//        iv.setImageBitmap(ava);
        new ImageLoader(iv).execute(client);
    }
}

