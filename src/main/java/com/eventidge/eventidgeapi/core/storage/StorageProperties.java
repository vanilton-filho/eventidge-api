package com.eventidge.eventidgeapi.core.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("eventidge.storage")
public class StorageProperties {

    private Local local = new Local();
    private TypeStorage typeStorage = TypeStorage.LOCAL;

    public enum TypeStorage {
        LOCAL
    }

    @Getter
    @Setter
    public class Local {
        private Path fileDirectory;
    }

}
