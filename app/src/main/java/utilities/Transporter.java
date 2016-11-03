package utilities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rypon on 11/2/2016.
 */

public class Transporter implements Parcelable {

    Object object;

    public Transporter(Object ac ){
        object =ac;
    }

    public Transporter(Parcel in){
//        in.readValue(this.object.getClass().getClassLoader());
    }

    public Object getData() {
        return object;
    }

    @Override
    public int describeContents() {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// TODO Auto-generated method stub

        dest.writeValue(this.object);
    }

    public static final Parcelable.Creator<Transporter> CREATOR= new Parcelable.Creator<Transporter>() {

        @Override
        public Transporter createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new Transporter(source);  //using parcelable constructor
        }

        @Override
        public Transporter[] newArray(int size) {
// TODO Auto-generated method stub
            return new Transporter[size];
        }
    };
}