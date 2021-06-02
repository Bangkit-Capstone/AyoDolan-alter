@file:Suppress("DEPRECATION")

package com.dicoding.capstone.ayodolan.tflite

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.databinding.ActivityTryImplementModelBinding
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.channels.FileChannel
import java.util.*

@Suppress("DEPRECATION")
class TryImplementModelActivity : AppCompatActivity() {
    private val MODEL_ASSETS_PATH = "modelku.tflite"
    private val INPUT_MAXLEN = 120
    private var tfLiteInterpreter : Interpreter? = null

    private lateinit var binding: ActivityTryImplementModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTryImplementModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.title_ml)

        val classifier = Classifier( this , "dict.json" , INPUT_MAXLEN )

        val assetFileDescriptor = assets.openFd(MODEL_ASSETS_PATH)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        tfLiteInterpreter = Interpreter( fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength) )

        val progressDialog = ProgressDialog( this )
        progressDialog.setMessage( "Loading parsing word_dict.json ...")
        progressDialog.setCancelable( false )
        progressDialog.show()
        classifier.processVocab( object: Classifier.VocabCallback {
            override fun onVocabProcessed() {
                progressDialog.dismiss()
            }
        })
            binding.btnGo.setOnClickListener {
                val message = binding.edText.text.toString().lowercase(Locale.getDefault()).trim()
                if ( !TextUtils.isEmpty( message ) ){
                    val tokenizedMessage = classifier.tokenize( message )
                    val paddedMessage = classifier.padSequence( tokenizedMessage )

                    val results = classifySequence( paddedMessage )

                    if(results[0]>0.5){
                        binding.result.text = resources.getString(R.string.positif)
                    }
                    else{
                        binding.result.text = resources.getString(R.string.negatif)
                    }
                }
                else{
                    Toast.makeText( this@TryImplementModelActivity, resources.getString(R.string.error), Toast.LENGTH_LONG).show()
                    binding.edText.error = resources.getString(R.string.error)
                }

            }

    }
    private fun classifySequence (sequence : IntArray ): FloatArray {
        val inputs : Array<FloatArray> = arrayOf( sequence.map { it.toFloat() }.toFloatArray() )
        val outputs : Array<FloatArray> = arrayOf( FloatArray( 1 ) )
        tfLiteInterpreter?.run( inputs , outputs )
        return outputs[0]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}