package cart.config.admin;

import cart.config.auth.Base64AuthInterceptor;
import cart.service.AuthService;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Base64AdminAccessInterceptor implements HandlerInterceptor {

    public static final String ADMIN_EMAIL = "admin@wootech.com";
    public static final String ADMIN_NAME = "admin";

    private final AuthService authService;

    public Base64AdminAccessInterceptor(final AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final String authValue = request.getHeader(Base64AuthInterceptor.AUTHORIZATION_HEADER);
        final String authValueWithBase64Encoding = authValue.substring(Base64AuthInterceptor.BASIC.length()).trim();
        final String auth = new String(Base64Utils.decodeFromString(authValueWithBase64Encoding));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        final String[] emailAndPasswordWithDecryption = auth.split(":");
        final String email = emailAndPasswordWithDecryption[0];
        final String password = emailAndPasswordWithDecryption[1];

        authService.validateAdmin(email, password);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
