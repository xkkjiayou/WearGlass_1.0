#include <jni.h>
#include <string>
//#include <opencv2/opencv.hpp>

extern "C" JNIEXPORT jstring JNICALL
Java_com_findai_xkk_wearglass_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C+撒大声地+";

    return env->NewStringUTF(hello.c_str());
}
//
//
//#include <jni.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <iostream>
//#include <opencv2/opencv.hpp>
//using namespace cv;
//using namespace std;
//IplImage * change4channelTo3InIplImage(IplImage * src);
//
//extern "C" {
//JNIEXPORT jstring JNICALL Java_com_findai_xkk_wearglass_MainActivity_stringFromJNI(
//        JNIEnv* env, jobject obj, jintArray buf, int w, int h);
//JNIEXPORT jstring JNICALL Java_com_findai_xkk_wearglass_MainActivity_stringFromJNI(
//        JNIEnv* env, jobject obj, jintArray buf, int w, int h) {
//
//    jint *cbuf;
//    cbuf = env->GetIntArrayElements(buf, false);
//    if (cbuf == NULL) {
//        return 0;
//    }
//
//    Mat myimg(h, w, CV_8UC4, (unsigned char*) cbuf);
//    Mat dst;
//    int width = static_cast<float>(myimg.cols*0.5);
//    //定义想要扩大或者缩小后的宽度，src.cols为原图像的宽度，乘以80%则得到想要的大小，并强制转换成float型
//    int height = static_cast<float>(myimg.rows*0.5);
//    //定义想要扩大或者缩小后的高度，src.cols为原图像的高度，乘以80%则得到想要的大小，并强制转换成float型
//    cout<<dst;
//    resize(myimg, dst, cv::Size(width, height));
////    for(int i=0;i<dst.cols;i++){
////        for(int j=0;j<dst.rows;j++){
////            printf(dst[j][i])
////        }
////    }
////    IplImage image=IplImage(myimg);
////    IplImage* image3channel = change4channelTo3InIplImage(&image);
////
////    IplImage* pCannyImage=cvCreateImage(cvGetSize(image3channel),IPL_DEPTH_8U,1);
////
////    cvCanny(image3channel,pCannyImage,50,150,3);
////
////    int* outImage=new int[w*h];
////    for(int i=0;i<w*h;i++)
////    {
////        outImage[i]=(int)pCannyImage->imageData[i];
////    }
////
////    int size = w * h;
////    jintArray result = env->NewIntArray(size);
////    env->SetIntArrayRegion(result, 0, size, outImage);
////    env->ReleaseIntArrayElements(buf, cbuf, 0);
//    std::string hello = "Hello from C+撒大声地+";
////
//    return env->NewStringUTF(hello.c_str());
//}
//}
//
//IplImage * change4channelTo3InIplImage(IplImage * src) {
//    if (src->nChannels != 4) {
//        return NULL;
//    }
//
//    IplImage * destImg = cvCreateImage(cvGetSize(src), IPL_DEPTH_8U, 3);
//    for (int row = 0; row < src->height; row++) {
//        for (int col = 0; col < src->width; col++) {
//            CvScalar s = cvGet2D(src, row, col);
//            cvSet2D(destImg, row, col, s);
//        }
//    }
//
//    return destImg;
//}