package k25.arviointikirja.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import k25.arviointikirja.domain.AppUser;
import k25.arviointikirja.domain.AppUserRepository;
import k25.arviointikirja.domain.Pupil;

@Service
public class UserService {

    /*
     * @Autowired
     * private AppUserRepository appUserRepository;
     */

    private final AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // Luokka käyttäjän tunnistamiseen, jotta pupillist voidaan näyttää oikein

    public Pupil getAuthenticatedPupil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser appUser = appUserRepository.findByUsername(username);
        return appUser.getPupil();
    }
}
