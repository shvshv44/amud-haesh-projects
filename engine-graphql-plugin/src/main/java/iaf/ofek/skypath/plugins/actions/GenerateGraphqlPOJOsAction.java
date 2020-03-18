package iaf.ofek.skypath.plugins.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import iaf.ofek.skypath.plugins.generation.GenerationInputManager;
import iaf.ofek.skypath.plugins.models.ProjectModel;
import iaf.ofek.skypath.plugins.utils.EnvironmentDataFetcher;
import org.jetbrains.annotations.NotNull;

public class GenerateGraphqlPOJOsAction extends AnAction {

    private GenerationInputManager manager;
    private EnvironmentDataFetcher dataFetcher;

    public GenerateGraphqlPOJOsAction() {
        this.manager = new GenerationInputManager();
        this.dataFetcher = new EnvironmentDataFetcher();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        ProjectModel projectModel = dataFetcher.obtainProjectModel(anActionEvent);
        manager.startGenerationInput();

    }
}
