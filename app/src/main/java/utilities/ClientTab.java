package utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truytimkhachno.ttkn.R;

import java.util.concurrent.ExecutionException;

import entities.Client;
import entities.clientdetails.Name;
import entities.clientdetails.Note;

/**
 * Created by Rypon on 10/31/2016.
 */

public class ClientTab {

    private LinearLayout root;
    private ImageView iv;
    private String notes="";
    private String names="";
    private Client client;
    private Context context;
    private Bitmap ava;
    private ImageLoader imgLoader;

    public ClientTab(Client client, Context context) {
        this.client = client;
        this.context = context;
        for(Name name:client.getNames()){
            names+=name.getName()+", ";
        }
        for(Note note:client.getNote()){
            notes+=note.getNote()+", ";
        }
        initView();
    }

    public LinearLayout getRoot() {
        return root;
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = (LinearLayout) inflater.from(context).inflate(R.layout.client_tab,null);
        ((TextView)root.findViewById(R.id.clientId)).setText(client.getId());
        ((TextView)root.findViewById(R.id.clientName)).setText(names.substring(0,names.length()-2));
        ((TextView)root.findViewById(R.id.clientNote)).setText(notes.substring(0,notes.length()-2));
        iv=(ImageView)root.findViewById(R.id.avaView);
//        iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.25f));
    }

    private void initViewOld(){
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
        imgLoader = new ImageLoader(iv);
        imgLoader.execute(client);
    }
    public void cancelAvaUpdate(){
        imgLoader.cancel(true);
    }
}

