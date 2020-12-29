//package com.lsy.wisdombuid.widget;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Movie;
//import android.util.AttributeSet;
//import android.widget.ImageView;
//
///**
// * Created by lsy on 2020/4/30
// * todo :
// */
//public class GifView extends ImageView {
//
//    /**
//     * Movie对象用来解析gif图片
//     */
//    private Movie gifMovie;
//
//    /**
//     * 开始时间，用来和当前时间比较，得出什么时间播放gif
//     */
//    private long mMovieStart;
//
//    /**
//     * gif图片的宽
//     */
//    private int gifImageWidth;
//
//    /**
//     * gif图片的高
//     */
//    private int gifImageHeight;
//
//
//    /**
//     * 访问attrs
//     */
//    private AttributeSet attrs;
//
//    /**
//     * 复写GifView的构造方法
//     *
//     * @param context
//     */
//    public GifView(Context context) {
//        super(context);
//
//    }
//
//    /**
//     * 复写的GifView的构造方法
//     *
//     * @param context
//     * @param attrs
//     */
//    public GifView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.attrs = attrs;
//        loadGifImage();
//    }
//
//    private void loadGifImage(){
//        AttributeSet attrs = getContext().getResources().getXml(R.id.gifView);
//
//        //生成一个TypedArray对象
//        TypedArray array = getContext().obtainStyledAttributes(attrs,
//                R.styleable.weatherView);
//
//        //得到资源id
//        int resourceId = getResourceId(array);
//
////检查资源id
//        if(resourceId == -1){
//            System.out.println("没有获取到图片Id，请检查是否在xml文件里设置了src属性");
//        }
//
//        // 以流的方式得到gif文件
//        InputStream is = getResources().openRawResource(resourceId);
//
//        // 用Movie的decodeStream方法解码文件
//        gifMovie = Movie.decodeStream(is);
//        if (gifMovie != null) {
//            Bitmap bitmap = BitmapFactory.decodeStream(is);
//            gifImageWidth = bitmap.getWidth();
//            gifImageHeight = bitmap.getHeight();
//            bitmap.recycle();
//        }
//
//
//    }
//
//    /**
//     *  复写onDraw方法用于绘制View
//     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        if (gifMovie == null) {
//            // 如果Movie为空说明不是一个gif图像，不是则调用父类方法，此时该控件等同于ImageView
//            super.onDraw(canvas);
//        } else {
//            // 如果Movie不为空则说明是一个gif图像则展示gif图像
//            showGifImage(canvas);
//            // 刷新
//            invalidate();
//        }
//    }
//
//    /**
//     *  复写onMeasure方法，用来设置Gif宽高
//     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (gifMovie != null) {
//            // 设置尺寸
//            setMeasuredDimension(gifImageWidth, gifImageHeight);
//        }
//    }
//
//    // 封装的展示gif的方法
//    private boolean showGifImage(Canvas canvas) {
//        //得到系统时间
//        long now = SystemClock.uptimeMillis();
//        if (mMovieStart == 0) {
//            // 把开始时间设置为当前时间
//            mMovieStart = now;
//        }
//        int duration = gifMovie.duration();
//        if (duration == 0) {
//            // 如果没有持续时间就设置为100
//            duration = 100;
//        }
//
//        // 设置间隔时间
//        int relTime = (int) ((now - mMovieStart) % duration);
//        gifMovie.setTime(relTime);
//
//        //在指定的位置进行绘制，这里是左上角
//        gifMovie.draw(canvas, 0, 0);
//        if ((now - mMovieStart) >= duration) {
//            mMovieStart = 0;
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 解析xml文件里的src传来的参数，需要传入一个TypeArray，Context，和参数数组AttributeSet
//     *
//     * @param array
//     * @param context
//     * @param attrs
//     * @return
//     */
//    private int getResourceId(TypedArray array
//    ) {
//        try {
//
//            // 得到一个TypedArray里的域
//            Field field = TypedArray.class.getDeclaredField("mValue");
//
//            // 设置可访问性为true
//            field.setAccessible(true);
//
//            // 从类中取得域值
//            TypedValue typeValueObject = (TypedValue) field.get(array);
//
//            return typeValueObject.resourceId;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            array.recycle();
//        }
//        return -1;
//    }
//
//}