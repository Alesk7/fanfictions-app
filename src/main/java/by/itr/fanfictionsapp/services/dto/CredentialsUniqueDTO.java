package by.itr.fanfictionsapp.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsUniqueDTO {
    private boolean emailUnique;
    private boolean usernameUnique;
    private boolean credentialsUnique;
}
