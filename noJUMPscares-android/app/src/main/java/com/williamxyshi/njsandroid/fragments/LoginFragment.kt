package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.williamxyshi.njsandroid.R
import com.williamxyshi.njsandroid.utils.WebServerAccessObject
import com.williamxyshi.njsandroid.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.w3c.dom.Text

class LoginFragment : Fragment() {

    private lateinit var vm: MainActivityViewModel

    var email = ""
    var password = ""

    private lateinit var loginButton: Button
    private lateinit var emailText: TextView
    private lateinit var passwordText: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false) as ViewGroup

        loginButton = rootView.findViewById(R.id.loginButton)
        emailText = rootView.findViewById(R.id.emailField)
        passwordText = rootView.findViewById(R.id.passwordField)

        initialize()
        setUpListeners()

        return rootView
    }

    private fun initialize(){
        vm = ViewModelProviders.of(activity?:return).get(MainActivityViewModel::class.java)



    }

    private fun setUpListeners() {

        loginButton.setOnClickListener{
            email = emailText.text.toString()
            password = passwordText.text.toString()
            Log.d(TAG, "email: ${email}, password: ${password}")


            vm.setEmailPassword(email, password)

            WebServerAccessObject.loginCall(vm)


        }

    }

    override fun onResume() {
        super.onResume()
    }

    companion object{
        const val TAG = "LoginFragment"
    }
}