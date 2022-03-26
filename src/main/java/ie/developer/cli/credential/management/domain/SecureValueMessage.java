package ie.developer.cli.credential.management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SecureValueMessage {

    private final String value;
    private final String password;

}
