<%-- 
    Document   : PopUpSignInRegister
    Created on : Jun 7, 2021, 7:44:13 PM
    Author     : ADMIN
--%>

<div class="overlay"></div>
    <div class="main-popup">
      <div class="popup-header">
        <div id="popup-close-button"><a href="#"></a></div>
        <ul>
          <li><a href="#" id="sign-in-tab">Sign In</a></li>
          <li><a href="#" id="register-tab">Register</a></li>
        </ul>
      </div>
      <!--.popup-header-->
      <div class="popup-content">
        <form action="Login" class="sign-in-form">
          <label for="email">Email:</label>
          <input type="text" id="email" name="txtEmail"/>
          <label for="password">Password:</label>
          <input type="password" id="password" name="txtPassword"/>
          <p class="check-mark">
            <input type="checkbox" id="remember-me" />
            <label for="remember-me">Remember me</label>
          </p>
          <input type="submit" id="submit" value="Login" />
        </form>

        <form action="Register" class="register-form">
          <label for="email-register">Email:</label>
          <input type="text" id="email-register" name="txtEmail" />
          <label for="firstname-register" style="padding-left: 100px; width:40%;">Full-name:</label>
          <input type="text" id="lastname-register" name="txtFullName" style="width: 40%;"/>
          <label for="password-register">Password:</label>
          <input type="password" id="password-register" name="txtPassword"/>
          <label for="password-confirmation">Confirm Password:</label>
          <input type="password-confirmation" name="txtConfirmPassword" id="password-confirmation" />
          <p class="check-mark">
            <input type="checkbox" id="accept-terms" />
            <label for="accept-terms"
              >I agree to the <a href="#">Terms</a></label
            >
          </p>
          <input type="submit" id="submit" value="Create Account" />
        </form>
      </div>
      <!--.popup-content-->
    </div>

