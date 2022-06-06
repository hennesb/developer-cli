package ie.developer.cli.credential.management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UUIDOutputMessage {

    private final String uuid;
    private final String encryptedUUID;



    @Override
    public String toString(){
        return String.format("UUID: %sEncrypted UUID: %s", uuid + System.lineSeparator(),encryptedUUID);
    }

}
