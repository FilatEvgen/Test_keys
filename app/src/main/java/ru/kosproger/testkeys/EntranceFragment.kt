package ru.kosproger.testkeys

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment

class EntranceFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var forgotPasswordButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.entrance, container, false)

        emailEditText = view.findViewById(R.id.email_text)
        passwordEditText = view.findViewById(R.id.password_text)
        loginButton = view.findViewById(R.id.button2)
        registerButton = view.findViewById(R.id.button3)
        forgotPasswordButton = view.findViewById(R.id.button4)

        registerButton.isEnabled = false
        forgotPasswordButton.isEnabled = false

        emailEditText.doAfterTextChanged { validateFields() }
        passwordEditText.doAfterTextChanged { validateFields() }

        // Обработчик для кнопки "Вход"
        loginButton.setOnClickListener {
            // val mainFragment = MainFragment()
            // requireActivity().supportFragmentManager.beginTransaction()
            //     .replace(R.id.main, mainFragment)
            //     .addToBackStack(null)
            //     .commit()
        }

        view.findViewById<ImageButton>(R.id.button5).setOnClickListener {
            openBrowser("https://vk.com/")
        }

        view.findViewById<ImageButton>(R.id.button6).setOnClickListener {
            openBrowser("https://ok.ru/")
        }

        return view
    }

    private fun validateFields() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val isEmailValid = email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
        val isPasswordNotEmpty = password.isNotEmpty()

        loginButton.isEnabled = isEmailValid && isPasswordNotEmpty
    }

    private fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}