package tech.jhipster.lite.module.application;

import java.time.Instant;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.JHipsterModuleApplied;
import tech.jhipster.lite.module.domain.JHipsterModuleEvents;
import tech.jhipster.lite.module.domain.JHipsterModuleToApply;
import tech.jhipster.lite.module.domain.JHipsterModulesDomainService;
import tech.jhipster.lite.module.domain.JHipsterModulesRepository;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesCurrentVersionsRepository;
import tech.jhipster.lite.module.domain.javadependency.ProjectJavaDependenciesRepository;

@Service
public class JHipsterModulesApplicationService {

  private final JHipsterModulesDomainService modules;
  private final JHipsterModuleEvents events;

  public JHipsterModulesApplicationService(
    JHipsterModulesRepository modulesRepository,
    JHipsterModuleEvents events,
    JavaDependenciesCurrentVersionsRepository currentVersions,
    ProjectJavaDependenciesRepository projectDependencies
  ) {
    modules = new JHipsterModulesDomainService(modulesRepository, currentVersions, projectDependencies);
    this.events = events;
  }

  public void apply(JHipsterModuleToApply toApply) {
    modules.apply(toApply.module());

    events.dispatch(new JHipsterModuleApplied(toApply.properties(), toApply.slug(), Instant.now()));
  }
}
