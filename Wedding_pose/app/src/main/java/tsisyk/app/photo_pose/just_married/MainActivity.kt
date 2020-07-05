package tsisyk.app.photo_pose.just_married

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

private const val REQUEST_CODE_ASK_PERMISSIONS = 123

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            shareScreenshot()
        }
    }

    private fun rollDice() {
        val drawableResource = (Random.nextInt(24) + 1)
        val diceImage: ImageView = findViewById(R.id.dice_image)
        diceImage.setImageResource(R.drawable.pose + drawableResource)
    }

    private fun shareScreenshot() {

        if (ContextCompat.checkSelfPermission(
                this, WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, WRITE_EXTERNAL_STORAGE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this, arrayOf(WRITE_EXTERNAL_STORAGE), REQUEST_CODE_ASK_PERMISSIONS
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

            val rootView = window.decorView.findViewById<View>(android.R.id.content)
            shareFile(getScreenShot(rootView), "scr_cropped.jpg")
        }

    }

    @Suppress("DEPRECATION")
    fun getScreenShot(view: View): Bitmap {
        val screenView = view.rootView
        screenView.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(screenView.drawingCache)
        screenView.isDrawingCacheEnabled = false
        return bitmap
    }

    private fun shareFile(bm: Bitmap, fileName: String) {

        val dirPath = Environment.getExternalStorageDirectory().absolutePath /*+ "/just_married"*/
        val dir = File(dirPath)
        if (!dir.exists())
            dir.mkdirs()
        val file = File(dirPath, fileName)
        file.createNewFile()
        try {
            val fOut = FileOutputStream(file)
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut)
            fOut.flush()
            fOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val uri = Uri.fromFile(file)
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "image/*"

        intent.putExtra(Intent.EXTRA_SUBJECT, "")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    val rootView = window.decorView.findViewById<View>(android.R.id.content)
                    shareFile(getScreenShot(rootView), "scr_cropped.jpg")
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
}
