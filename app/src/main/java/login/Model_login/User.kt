package Model_login

import android.text.TextUtils
import android.util.Patterns

class User(user : String,password: String ) {
    var User : String
    var Password :String
    init {
        this.User = user
        this.Password = password
    }


}