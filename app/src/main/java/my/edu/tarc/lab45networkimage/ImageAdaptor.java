package my.edu.tarc.lab45networkimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;



/**
 * Created by KweeTeck on 11/1/2017.
 */

public class ImageAdaptor extends ArrayAdapter<ImageFile> {
    private final List<ImageFile> list;
    Activity context;

    public ImageAdaptor(Activity context, int resource,  List<ImageFile> list) {
        super(context, resource, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater  = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.image_layout, parent, false);

        //in image layout have taxtView and ImageView
        TextView textViewID = (TextView) rowView.findViewById(R.id.textViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewPresent);

        ImageFile imageFile;
        imageFile = getItem(position);

        textViewID.setText("ID:"+ imageFile.getId());

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //get file as binary array
        //decode base64 string to image
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(imageFile.getImage(), Base64.DEFAULT);

        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        //decode the image into bitmap from the beginning of the imageBytes to the last length

        imageView.setImageBitmap(decodedImage);
        return rowView;
    }

}
