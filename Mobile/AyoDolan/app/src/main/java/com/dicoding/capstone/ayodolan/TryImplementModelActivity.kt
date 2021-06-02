package com.dicoding.capstone.ayodolan

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.dicoding.capstone.ayodolan.databinding.ActivityTryImplementModelBinding
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.channels.FileChannel

class TryImplementModelActivity : AppCompatActivity() {
    private val MODEL_ASSETS_PATH = "modelku.tflite"
    private val INPUT_MAXLEN = 120
    private var tfLiteInterpreter : Interpreter? = null

    private lateinit var classifier: Classifier
    private lateinit var binding: ActivityTryImplementModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTryImplementModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val classifier = Classifier( this , "dict.json" , INPUT_MAXLEN )
        // Init TFLiteInterpreter
        val assetFileDescriptor = assets.openFd(MODEL_ASSETS_PATH)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        tfLiteInterpreter = Interpreter( fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength) )

        val progressDialog = ProgressDialog( this )
        progressDialog.setMessage( "Parsing word_dict.json ..." )
        progressDialog.setCancelable( false )
        progressDialog.show()
        classifier.processVocab( object: Classifier.VocabCallback {
            override fun onVocabProcessed() {
                // Processing done, dismiss the progressDialog.
                progressDialog.dismiss()
            }
        })

//        var text = binding.edText.text.toString()

        binding.btnGo.setOnClickListener {
            val message = binding.edText.text.toString().toLowerCase().trim()
            if ( !TextUtils.isEmpty( message ) ){
                // Tokenize and pad the given input text.
                val tokenizedMessage = classifier.tokenize( message )
                val paddedMessage = classifier.padSequence( tokenizedMessage )

                val results = classifySequence( paddedMessage )

//                Toast.makeText( this@TryImplementModelActivity, "Hasil :"+ results[0], Toast.LENGTH_LONG).show();
                if(results[0]>0.5){
                    binding.result.text = "Positif"
                }
                else{
                    binding.result.text = "Negatif"
                }
//                Log.i("Info", "MyClass.getView() — get item number $paddedMessage[0]")

            }
            else{
                Toast.makeText( this@TryImplementModelActivity, "Please enter a message.", Toast.LENGTH_LONG).show();
            }

        }


    }
    private fun classifySequence (sequence : IntArray ): FloatArray {
        val inputs : Array<FloatArray> = arrayOf( sequence.map { it.toFloat() }.toFloatArray() )
        val outputs : Array<FloatArray> = arrayOf( FloatArray( 1 ) )
        tfLiteInterpreter?.run( inputs , outputs )
        return outputs[0]
    }

    private fun getInput (sequence : IntArray ): Array<FloatArray> {
        return arrayOf( sequence.map { it.toFloat() }.toFloatArray() )

    }
}