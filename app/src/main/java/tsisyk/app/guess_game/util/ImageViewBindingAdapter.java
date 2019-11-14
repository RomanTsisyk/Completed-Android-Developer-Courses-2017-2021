//package tsisyk.app.guess_game.util;
//
//
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.widget.ImageView;
//
//import androidx.databinding.BindingAdapter;
//import androidx.databinding.BindingMethod;
//import androidx.databinding.BindingMethods;
//
//@BindingMethods({
//        @BindingMethod(type = android.widget.ImageView.class, attribute = "android:tint", method = "setImageTintList"),
//        @BindingMethod(type = android.widget.ImageView.class, attribute = "android:tintMode", method = "setImageTintMode"),
//})
//public class ImageViewBindingAdapter {
//    @BindingAdapter("android:src")
//    public static void setImageUri(ImageView view, String imageUri) {
//        if (imageUri == null) {
//            view.setImageURI(null);
//        } else {
//            view.setImageURI(Uri.parse(imageUri));
//        }
//    }
//    @BindingAdapter("android:src")
//    public static void setImageUri(ImageView view, Uri imageUri) {
//        view.setImageURI(imageUri);
//    }
//    @BindingAdapter("android:src")
//    public static void setImageDrawable(ImageView view, Drawable drawable) {
//        view.setImageDrawable(drawable);
//    }
//}