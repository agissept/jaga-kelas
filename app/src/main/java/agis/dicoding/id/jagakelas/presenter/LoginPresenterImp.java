package agis.dicoding.id.jagakelas.presenter;

import android.text.TextUtils;

public class LoginPresenterImp implements LoginPresenter{

    private LoginView loginView;

    public LoginPresenterImp(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            loginView.showValidationError();
        }else {
            if (username.equals("admin") && password.equals("admin")){
                loginView.loginSuccess();
            }else {
                loginView.loginError();
            }
        }
    }
}
