package com.williamxyshi.njsandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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


    private lateinit var passwordConfirmText: TextView
    private lateinit var usernameText: TextView

    /**
     *
     * SHOW AND HIDE these containers based on wether or not we're logging in or signing up
     */
    private lateinit var usernameContainer: ConstraintLayout
    private lateinit var confirmPasswordContainer: ConstraintLayout


    /**
     * determines if the user is signing in or signing up
     */
    private var mode = SIGN_IN


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false) as ViewGroup

        loginButton = rootView.findViewById(R.id.loginButton)
        emailText = rootView.findViewById(R.id.emailField)
        passwordText = rootView.findViewById(R.id.passwordField)


        passwordConfirmText = rootView.findViewById(R.id.passwordConfirmField)
        usernameText = rootView.findViewById(R.id.usernameField)
        usernameContainer = rootView.findViewById(R.id.usernameContainer)
        confirmPasswordContainer = rootView.findViewById(R.id.passwordConfirmContainer)

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
        vm.currentPage.value = MainActivityViewModel.USER_PAGE
    }

    companion object{
        const val TAG = "LoginFragment"

        private const val SIGN_IN = 0
        private const val SIGN_UP = 1
    }
}