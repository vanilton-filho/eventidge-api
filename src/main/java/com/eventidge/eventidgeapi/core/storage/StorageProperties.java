package com.eventidge.eventidgeapi.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Builder;
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
    private S3 s3 = new S3();
    private TypeStorage typeStorage = TypeStorage.LOCAL;

    public enum TypeStorage {
        LOCAL, S3
    }

    @Getter
    @Setter
    public class Local {
        private Path fileDirectory;
    }

    @Getter
    @Setter
    public class S3 {
        private String accessKeyId;
        private String secretAccessKey;
        private String bucket;
        private Regions region;
        private String pathFiles;
    }

}
