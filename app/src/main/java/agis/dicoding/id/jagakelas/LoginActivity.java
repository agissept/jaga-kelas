package agis.dicoding.id.jagakelas;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import agis.dicoding.id.jagakelas.presenter.LoginPresenter;
import agis.dicoding.id.jagakelas.presenter.LoginPresenterImp;
import agis.dicoding.id.jagakelas.presenter.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter presenter;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_pass);
        btLogin = findViewById(R.id.bt_login);

        presenter = new LoginPresenterImp(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    @Override
    public void showValidationError() {
        Toast.makeText(this, "Please enter valid username and password!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "You are successfully logged in!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError() {
        Toast.makeText(this, "Invalid login credentials!", Toast.LENGTH_SHORT).show();
    }
}
