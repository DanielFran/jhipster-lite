package tech.jhipster.lite.module.infrastructure.primary;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.projectfolder.domain.ProjectFolder;

class RestJHipsterModuleProperties {

  private final String projectFolder;
  private final Map<String, Object> properties;

  RestJHipsterModuleProperties(
    @JsonProperty("projectFolder") String projectFolder,
    @JsonProperty("properties") Map<String, Object> properties
  ) {
    this.projectFolder = projectFolder;
    this.properties = properties;
  }

  public JHipsterModuleProperties toDomain(ProjectFolder jHipsterProjectFolderFactory) {
    Assert.notNull("jHipsterProjectFolderFactory", jHipsterProjectFolderFactory);

    assertValidProjectFolder(jHipsterProjectFolderFactory);

    return new JHipsterModuleProperties(projectFolder, properties);
  }

  private void assertValidProjectFolder(ProjectFolder jHipsterProjectFolderFactory) {
    if (jHipsterProjectFolderFactory.isInvalid(projectFolder)) {
      throw new InvalidProjectFolderException();
    }
  }
}
