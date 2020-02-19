package com.murilobatista.square
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = FirebaseFirestore.getInstance()
        btnMudar.setOnClickListener { mudarQuadrado() }
    }

    private fun mudarQuadrado() {
        val largura = editTextLargura.text.toString()
        val altura = editTextAltura.text.toString()
        if(largura.isNotEmpty() && altura.isNotEmpty()) {
            progressBar.visibility = View.VISIBLE
            val quadrado = hashMapOf("width" to largura, "height" to altura)
            db.collection("formas").document("quadrado").update(quadrado as Map<String, Any>)
                .addOnSuccessListener { progressBar.visibility = View.GONE; Toast.makeText(this, "Quadrado alterado!", Toast.LENGTH_SHORT).show()}
        } else {
            Toast.makeText(this, "Preencha os campos!", Toast.LENGTH_SHORT).show()
        }
    }
}