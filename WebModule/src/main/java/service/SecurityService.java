package service;

/**
 * Created by Александр on 05.12.2016.
 */
public interface SecurityService {
    String findLoggedUsername();

    void autoLogin(String username, String password);
}
