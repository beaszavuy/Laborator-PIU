package com.example.piu

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.piu.APIs.ChatAPI
import com.example.piu.model.DTO.AuthRequest
import com.example.piu.model.DTO.AuthResponse
import com.example.piu.model.DTO.ResponseError
import com.example.piu.model.Userinfo
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener
{
    lateinit var signInButton:  Button
    lateinit var usernameErrors: TextView
    lateinit var passwordErrors: TextView
    lateinit var generalMessage: TextView
    lateinit var usernameField: EditText
    lateinit var passwordField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInButton = findViewById(R.id.signin)
        usernameField = findViewById(R.id.nameField)
        passwordField = findViewById(R.id.passwordField)
        generalMessage = findViewById(R.id.generalMessage)
        usernameErrors = findViewById(R.id.nameError)
        passwordErrors = findViewById(R.id.passwordError)

        usernameErrors.text=""
        passwordErrors.text=""
        generalMessage.isVisible = false
        signInButton.setOnClickListener(this)

    }
    override fun onClick(p0: View?)
    {

        signInButton = findViewById(R.id.signin)
        usernameField = findViewById(R.id.nameField)
        passwordField = findViewById(R.id.passwordField)
        generalMessage = findViewById(R.id.generalMessage)
        usernameErrors = findViewById(R.id.nameError)
        passwordErrors = findViewById(R.id.passwordError)

        usernameErrors.text=""
        passwordErrors.text=""

        if(usernameField.text.equals("") && usernameField.text.length<3) {
            usernameErrors.text="Username must have at least 3 characters"
        }
        if(passwordField.text.equals("") && passwordField.text.length<6)  {
            passwordErrors.text="Password must have at least 3 characters"
        }

        if(usernameErrors.text.equals("") && passwordErrors.text.equals("")) {
            generalMessage.isVisible = true
            val chatAPI =ChatAPI.create()
            chatAPI.Authenticate ( AuthRequest(usernameField.text.toString(), passwordField.text.toString())).enqueue ( object: Callback<AuthResponse> {
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    // let the user know a connection problem occurred
                    generalMessage.setTextColor(Color.RED)
                    generalMessage.text = "Failiure"
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (!response.isSuccessful) {
                        // verify if the server has sent more details about the error
                        var errorDetails: ResponseError?
                        try {
                            // in case of error, the data received is not converted to a Kotlin object
                            // => we need to deal with the raw, plain text answer
                            val errorInfo = response.errorBody()!!.string()
                            // after retrieving the information in string format, we will try to convert it from
                            // JSON to a Kotlin object – specifc to ChatAPI
                            val parser = Gson()
                            errorDetails = parser.fromJson(errorInfo, ResponseError::class.java)
                            generalMessage.setTextColor(Color.RED)
                            generalMessage.text = errorDetails.message
                        } catch (e: Exception) {
                            errorDetails =
                                ResponseError("Nu pot fi obținute detalii despre eroare!")
                        }
                    } else {
                        // received data is available in as a Kotlin object of type User

                        val userInfo: AuthResponse = response.body()!!
                        generalMessage.setTextColor(Color.GREEN)
                        generalMessage.text = "Authentication successfull"

                        Userinfo.username = userInfo.display
                        Userinfo.token = userInfo.token
                        val intent = Intent(this@LoginActivity, OffersActivity::class.java)
                        startActivity(intent)
                    }
                }
            })
            /*if (usernameField.text.toString().equals("admin") && passwordField.text.toString()
                    .equals("pass")
            ) {
                generalMessage.text = "Authentication successfull"

                Userinfo.username = usernameField.text.toString()

                val intent = Intent(this, OffersActivity::class.java)
                startActivity(intent)

            } else {
                generalMessage.setTextColor(Color.RED)
                generalMessage.text = "Wrong username or password"
            }*/

        }else {generalMessage.isVisible = false}
    }


}