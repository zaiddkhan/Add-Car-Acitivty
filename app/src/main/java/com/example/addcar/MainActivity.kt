package com.example.addcar

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.addcar.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

class MainActivity : AppCompatActivity(),View.OnClickListener{

    private var binding:ActivityMainBinding? = null
    private var CAMERA_REQUEST_CODE = 0
    private var GALLERY_REQUEST_CODE= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpActionBar()

        binding?.btn1?.setOnClickListener(this)
        binding?.btn2?.setOnClickListener(this)
        binding?.btn3?.setOnClickListener(this)
        binding?.btn4?.setOnClickListener(this)
        binding?.btn5?.setOnClickListener(this)
        binding?.btn6?.setOnClickListener(this)
    }
    private fun setUpActionBar(){
          setSupportActionBar(binding?.toolbarAddCar)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back)
        }
        binding?.toolbarAddCar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_select_image_from)

        dialog.findViewById<TextView>(R.id.select_from_camera).setOnClickListener {
            cameraCheckPermission()
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.select_from_gallery).setOnClickListener {
            galleryCheckPermission()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun galleryCheckPermission(){
        Dexter.withContext(this).withPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(
            object : PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    gallery()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    Toast.makeText(this@MainActivity,
                        "You denied the permission",Toast.LENGTH_LONG).
                    show()
                    showRationaleDialog()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    showRationaleDialog()
                }

            }
        ).onSameThread().check()
    }

    private fun cameraCheckPermission(){
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA).withListener(
                object : MultiplePermissionsListener{
                    override fun onPermissionsChecked(report : MultiplePermissionsReport?) {
                        report?.let {
                            if(report.areAllPermissionsGranted()){

                                camera()
                            }
                        }
                    }
                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {
                       showRationaleDialog()
                    }

                }
            ).onSameThread().check()

    }
    private fun camera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CAMERA_REQUEST_CODE)
    }

    private fun gallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK ){
            when(requestCode){

                1 -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding?.image1?.visibility = View.VISIBLE
                    binding?.btn1?.visibility = View.INVISIBLE
                    binding?.image1?.setImageBitmap(bitmap)

                    if(binding?.cv2?.visibility == View.INVISIBLE){
                        binding?.cv2?.visibility = View.VISIBLE
                    }
                }
               2 -> {
                    binding?.image1?.visibility = View.VISIBLE
                    binding?.btn1?.visibility = View.INVISIBLE

                    binding?.image1?.setImageURI(data?.data)
                    if(binding?.cv2?.visibility == View.INVISIBLE){
                        binding?.cv2?.visibility = View.VISIBLE
                    }
                }

                3 -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding?.image2?.visibility = View.VISIBLE
                    binding?.btn2?.visibility = View.INVISIBLE
                    binding?.image2?.setImageBitmap(bitmap)
                    if(binding?.cv3?.visibility == View.INVISIBLE){
                        binding?.cv3?.visibility = View.VISIBLE
                    }
                }
                4 ->{
                    binding?.image2?.visibility = View.VISIBLE
                    binding?.btn2?.visibility = View.INVISIBLE

                    binding?.image2?.setImageURI(data?.data)
                    if(binding?.cv3?.visibility == View.INVISIBLE){
                        binding?.cv3?.visibility = View.VISIBLE
                    }
                }
                5 ->{
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding?.image3?.visibility = View.VISIBLE
                    binding?.btn3?.visibility = View.INVISIBLE
                    binding?.image3?.setImageBitmap(bitmap)
                    if(binding?.cv4?.visibility == View.INVISIBLE){
                        binding?.cv4?.visibility = View.VISIBLE
                    }
                }
                6 -> {
                    binding?.image3?.visibility = View.VISIBLE
                    binding?.btn3?.visibility = View.INVISIBLE

                    binding?.image3?.setImageURI(data?.data)
                    if(binding?.cv4?.visibility == View.INVISIBLE){
                        binding?.cv4?.visibility = View.VISIBLE
                    }
                }
                7 ->{
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding?.image4?.visibility = View.VISIBLE
                    binding?.btn4?.visibility = View.INVISIBLE
                    binding?.image4?.setImageBitmap(bitmap)
                    if(binding?.cv5?.visibility == View.INVISIBLE){
                        binding?.cv5?.visibility = View.VISIBLE
                    }
                }
                8 -> {
                    binding?.image4?.visibility = View.VISIBLE
                    binding?.btn4?.visibility = View.INVISIBLE

                    binding?.image4?.setImageURI(data?.data)
                    if(binding?.cv5?.visibility == View.INVISIBLE){
                        binding?.cv5?.visibility = View.VISIBLE
                    }
                }
                9 ->{
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding?.image5?.visibility = View.VISIBLE
                    binding?.btn5?.visibility = View.INVISIBLE
                    binding?.image5?.setImageBitmap(bitmap)
                    if(binding?.cv6?.visibility == View.INVISIBLE){
                        binding?.cv6?.visibility = View.VISIBLE
                    }
                }
                10 -> {
                    binding?.image5?.visibility = View.VISIBLE
                    binding?.btn5?.visibility = View.INVISIBLE

                    binding?.image5?.setImageURI(data?.data)
                    if(binding?.cv6?.visibility == View.INVISIBLE){
                        binding?.cv6?.visibility = View.VISIBLE
                    }
                }
                11 ->{
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding?.image6?.visibility = View.VISIBLE
                    binding?.btn6?.visibility = View.INVISIBLE
                    binding?.image6?.setImageBitmap(bitmap)
                }
                12 -> {
                    binding?.image6?.visibility = View.VISIBLE
                    binding?.btn6?.visibility = View.INVISIBLE
                    binding?.image6?.setImageURI(data?.data)
                }
            }
        }
    }
    private fun showRationaleDialog(){
        AlertDialog.Builder(this)
            .setMessage("It looks like you have turned off permissions" +
                    " required for this feature, please enable it from the App settings")

            .setPositiveButton("Go to Settings"){_,_ ->
                try{
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package",packageName,null)
                    intent.data = uri
                    startActivity(intent)
                }catch (e:ActivityNotFoundException){
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel"){dialog,_ ->
                dialog.dismiss()
            }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn1 -> {
                CAMERA_REQUEST_CODE =1
                GALLERY_REQUEST_CODE = 2
                showDialog()
            }
            R.id.btn2 -> {
                CAMERA_REQUEST_CODE = 3
                GALLERY_REQUEST_CODE = 4
                showDialog()
            }
            R.id.btn3 -> {
                CAMERA_REQUEST_CODE = 5
                GALLERY_REQUEST_CODE = 6
                showDialog()
            }
            R.id.btn4 -> {
                CAMERA_REQUEST_CODE = 7
                GALLERY_REQUEST_CODE = 8
                showDialog()
            }
            R.id.btn5 -> {
                CAMERA_REQUEST_CODE = 9
                GALLERY_REQUEST_CODE = 10
                showDialog()
            }
            R.id.btn6 -> {
                CAMERA_REQUEST_CODE = 11
                GALLERY_REQUEST_CODE = 12
                showDialog()
            }

        }
    }
}