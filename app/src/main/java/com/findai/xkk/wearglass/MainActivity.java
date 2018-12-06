package com.findai.xkk.wearglass;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    ImageView qian;
    ImageView hou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.eye)).getBitmap();

//        int w = bitmap.getWidth();
//        int h=bitmap.getHeight();
//        int[] pixels = new int[w*h];
//        bitmap.getPixels(pixels,0,w,0,0,w,h);
        qian = findViewById(R.id.qian);
        hou = findViewById(R.id.hou);
        qian.setImageBitmap(bitmap);
        long s = System.currentTimeMillis();

        Bitmap bitmap1 = process_img(bitmap);
        System.out.println(System.currentTimeMillis() - s);
        hou.setImageBitmap(bitmap1);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("反而");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public Bitmap process_img(Bitmap bitmap) {
        Mat mat = new Mat();
        Utils.bitmapToMat(bitmap, mat);
//        System.out.println("1111111111111");
//        System.out.println(mat);
        Mat resize_mat = new Mat();
        long s = System.currentTimeMillis();
        Imgproc.resize(mat, resize_mat, new Size(mat.cols() / 6, mat.rows() / 6), 0, 0, Imgproc.INTER_NEAREST);

        System.out.println(System.currentTimeMillis() - s);
//        String proc_pixels = stringFromJNI(pixels,w,h);
//        bitmap.getPixels(   )
        Bitmap bmp = Bitmap.createBitmap(resize_mat.cols(), resize_mat.rows(), Bitmap.Config.RGB_565);
        Mat gray_mat = new Mat();
        Imgproc.cvtColor(resize_mat, gray_mat, Imgproc.COLOR_BGRA2GRAY, 1);
//        Bitmap bsize = new Bitmap();
//        Utils.matToBitmap(graymat,bmp);
        // Example of a call to a native method

        double global_max = 0;
        double[][] sumimg = new double[mat.rows() / 6][mat.cols() / 6];

        for (int i = 10; i < gray_mat.rows() - 10; i = i + 3) {
            for (int j = 10; j < gray_mat.cols() - 10; j = j + 3) {
                double sum = 0;
                int ci = i;
                for (int k = 0; k < 5; k++) {
//                    System.out.println(graymat.get(k,j).length);
                    sum = sum + (gray_mat.get(ci + k, j)[0] - gray_mat.get(ci - k, j)[0]);
//                    System.out.println(sum);
                }
//                new_mat.put(i,j,sum);
                sumimg[i][j] = sum;
                if (global_max < sum) {
                    global_max = sum;
                }
//                System.out.println();
            }
        }

        for (int j = 10; j < gray_mat.cols() - 10; j = j + 3) {
            double max = global_max * 0.5;
            int max_i = 0;
            int max_j = 0;
//                System.out.println();
            for (int i = 10; i < gray_mat.rows() - 10; i = i + 3) {
                if (max < sumimg[i][j]) {
                    max = sumimg[i][j];
//                    System.out.println(max);
                    max_i = i;
                    max_j = j;
                }
            }
//            System.out.println("I:"+max_i+"----J:"+max_j+"----max:"+max);
            double[] sz = new double[]{255};
            gray_mat.put(max_i, max_j, sz);
        }
//        System.out.println(gray_mat.cols()+"  "+gray_mat.rows());
//        System.out.println(System.currentTimeMillis()-s);
        Utils.matToBitmap(gray_mat, bmp);
        return bmp;
    }
}
