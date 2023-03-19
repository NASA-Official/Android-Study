package com.nassafy.aro.hilt.oauthpractice

import android.accounts.AccountManager
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.nassafy.aro.hilt.oauthpractice.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class MainActivity() : AppCompatActivity() {

    val am: AccountManager get() = AccountManager.get(this)
    val options = Bundle()
    val GITHUB_CLIENT_ID = BuildConfig.GITHUB_CLIENT_ID
    val GITHUB_CLIENT_SECRET = BuildConfig.GITHUB_CLIENT_SECRET

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private fun setResultSignUp(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            Log.d("ssafy", result.data.toString())
            if (result.resultCode == Activity.RESULT_OK){
                Log.d("ssafy", result.data.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setResultSignUp()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            login(this)
        }

    }

    fun login(context: Context) {
        val loginUri = Uri.Builder().scheme("https").authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", GITHUB_CLIENT_ID)
            .build()

        startActivity(Intent(Intent.ACTION_VIEW, loginUri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = intent?.data
        Log.d("ssafy/auth_code", "$uri")

        val code = uri?.getQueryParameter("code")
        val state = uri?.getQueryParameter("state")
        Log.d("ssafy/auth_code", "$code")
        Log.d("ssafy/auth_state", "$state")
    }

}