package com.cookiegames.smartcookie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {

    // معرف الإحالة الخاص بك
    private val affiliateId = "4093313497"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val urlInput = findViewById<EditText>(R.id.urlInput)
        val searchButton = findViewById<Button>(R.id.searchButton)

        searchButton.setOnClickListener {
            val inputUrl = urlInput.text.toString().trim()

            if (inputUrl.isEmpty()) {
                Toast.makeText(this, "الرجاء إدخال رابط أولاً!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                // بناء الرابط وإضافة affid الخاص بك
                val finalUrl = buildAffiliateUrl(inputUrl)

                // فتح الرابط في المتصفح
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(this, "الرابط غير صالح!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildAffiliateUrl(url: String): String {
        // إضافة https إذا كان الرابط يفتقر إليها
        val formattedUrl = if (!url.startsWith("http")) "https://$url" else url
        val finalUri = Uri.parse(formattedUrl)

        // دمج معرف الإحالة مع الرابط
        return finalUri.buildUpon()
            .appendQueryParameter("affid", affiliateId)
            .build()
            .toString()
    }
}
